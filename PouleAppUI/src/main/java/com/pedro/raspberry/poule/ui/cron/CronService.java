package com.pedro.raspberry.poule.ui.cron;

import com.pedro.raspberry.poule.ui.audit.AuditService;
import com.pedro.raspberry.poule.ui.config.Config;
import com.pedro.raspberry.poule.ui.config.ConfigService;
import com.pedro.raspberry.poule.ui.cron.job.CloseDoorJob;
import com.pedro.raspberry.poule.ui.cron.job.OpenDoorJob;
import com.pedro.raspberry.poule.ui.door.DoorService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class CronService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private SchedulerFactory sf = null;
    private Scheduler sched;
    private JobDetail closeJob;
    private JobDetail openJob;

    @Autowired
    private ConfigService configService;

    /**
     * Note : service is choosen depending on profile used.
     * "default" profile will select DoorServiceMock impl.
     * "prod" profile will select GPIODoorService which is the real service.
     */
    @Autowired
    private DoorService doorService;

    @Autowired
    private AuditService auditService;

    @Bean
    public JobDetail openJobDetail() {
        return JobBuilder.newJob().ofType(OpenDoorJob.class)
                //.storeDurably()
                .withIdentity("OpenDoorJob2")
                .withDescription("Open door job")
                .build();
    }

    @Bean JobDetail closeJobDetail() {
        return JobBuilder.newJob().ofType(CloseDoorJob.class)
                //.storeDurably()
                .withIdentity("CloseDoorJob2")
                .withDescription("Close door job")
                .build();
    }

    public Trigger trigger(JobDetail job, String expression) {
        return TriggerBuilder.newTrigger().forJob(job)
                //.withIdentity(name)
                .withDescription("trigger for door")
                .withSchedule(cronSchedule(expression))
                .build();
    }

    @PostConstruct
    public void initScheduler() throws SchedulerException {

        sf = new StdSchedulerFactory();
        sched = sf.getScheduler();

        openJob = openJobDetail();
        openJob.getJobDataMap().put("doorService", doorService);

        // get cron expression from config
        String openExpression = getCronExpression(getCurrentOpenHour(), getCurrentOpenMinutes());
        scheduleOpening(openExpression);

        closeJob = closeJobDetail();
        closeJob.getJobDataMap().put("doorService", doorService);

        String closeExpression = getCronExpression(configService.getConfig().getCloseHour(), configService.getConfig().getCloseMinutes());
        scheduleClosing(closeExpression);
        sched.start();
    }

    private void scheduleClosing(String closeExpression) throws SchedulerException {

        Trigger trigger = trigger(closeJob, closeExpression);
        sched.scheduleJob(closeJob, trigger);
    }

    private void scheduleOpening(String openExpression) throws SchedulerException {

        Trigger trigger = trigger(openJob, openExpression);
        sched.scheduleJob(openJob, trigger);
    }

    public String getCurrentOpenHour() {
        return configService.getConfig().getOpenHour();
    }

    public String getCurrentOpenMinutes() {
        return configService.getConfig().getOpenMinutes();
    }

    public String getCurrentCloseHour() {
        return configService.getConfig().getCloseHour();
    }

    public String getCurrentCloseMinutes() {
        return configService.getConfig().getCloseMinutes();
    }

    private void reScheduleOpening(Config old, Config newConfig) throws SchedulerException {

        String currentOpenExpression = getCronExpression(old.getOpenHour(), old.getOpenMinutes());
        String expression = getCronExpression(newConfig.getOpenHour(), newConfig.getOpenMinutes());

        auditService.audit("audit.open.scheduling.invoked", currentOpenExpression, expression);

        if (!currentOpenExpression.equals(expression)) {
            currentOpenExpression = expression;
            sched.deleteJob(openJob.getKey());
            scheduleOpening(currentOpenExpression);
            logger.info("open job scheduled again with new expression {}", expression);
        }

        auditService.audit("audit.open.scheduling.finished");

    }

    public String getCronExpression(String hour, String minutes) {
        return "0 " + (minutes != null ? minutes : "0") + " " + (hour != null ? hour : "0") + " * * ?";
    }

    private void reScheduleClosing(Config old, Config newConfig) throws SchedulerException {

        String currentCloseExpression = getCronExpression(old.getCloseHour(), old.getCloseMinutes());
        String expression = getCronExpression(newConfig.getCloseHour(), newConfig.getCloseMinutes());
        auditService.audit("audit.close.scheduling.invoked", currentCloseExpression, expression);

        if (!currentCloseExpression.equals(expression)) {
            sched.deleteJob(closeJob.getKey());
            scheduleClosing(expression);
            logger.info("close job scheduled again with new expression {}", expression);
        }

        auditService.audit("audit.close.scheduling.finished");
    }

    /**
     * This method is triggered if scheduler configuration changed
     */
    public void updateConfig(Config old, Config newOne) throws SchedulerException {

        if (!old.getOpenHour().equals(newOne.getOpenHour()) || !old.getOpenMinutes().equals(newOne.getOpenMinutes())) {
            // open scheduling is outdated, it must be renewed
            reScheduleOpening(old, newOne);
        }

        if (!old.getCloseHour().equals(newOne.getCloseHour()) || !old.getCloseMinutes().equals(newOne.getCloseMinutes())) {
            // close scheduling is outdated, it must be renewed
            reScheduleClosing(old, newOne);
        }

    }

    public void pauseScheduler(String remoteAddr) throws SchedulerException {
        auditService.audit("audit.scheduling.pause", remoteAddr);
        logger.info("The scheduler has been paused. No job will be executed");
        if (!sched.isInStandbyMode())
            sched.standby();
    }

    public void resumeScheduler(String remoteAddr) throws SchedulerException {
        auditService.audit("audit.scheduling.resume", remoteAddr);
        logger.info("The scheduler has been resumed.");
        if (sched.isInStandbyMode())
            sched.start();
    }

    public boolean isSchedulerStarted() throws SchedulerException {
        return sched.isStarted();
    }

    public boolean isPaused() throws SchedulerException {
        return sched.isInStandbyMode();
    }

    public boolean isShutdown() throws SchedulerException {
        return sched.isShutdown();
    }

}
