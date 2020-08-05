package com.pedro.raspberry.poule.ui.cron;

import com.pedro.raspberry.poule.ui.audit.AuditService;
import com.pedro.raspberry.poule.ui.cron.job.CloseDoorJob;
import com.pedro.raspberry.poule.ui.cron.job.OpenDoorJob;
import com.pedro.raspberry.poule.ui.door.DoorService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class CronService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private String currentOpenExpression = "0 0 7 * * ?";
    private String currentCloseExpression = "0 30 22 * * ?";

    private SchedulerFactory sf = null;
    private Scheduler sched;
    private JobDetail closeJob;
    private JobDetail openJob;

    @Autowired
    private DoorService doorService;

    @Autowired
    private AuditService auditService;

    @PostConstruct
    public void initScheduler() throws SchedulerException {
        sf = new StdSchedulerFactory();
        sched = sf.getScheduler();

        openJob = newJob(OpenDoorJob.class)
                .withIdentity("OpenDoorJob", "DoorJobs")
                .build();
        openJob.getJobDataMap().put("doorService", doorService);

        CronTrigger openTrigger = newTrigger()
                .withIdentity("OpenTrigger", "DoorJobs")
                .withSchedule(cronSchedule(currentOpenExpression))
                .build();

        sched.scheduleJob(openJob, openTrigger);

        closeJob = newJob(CloseDoorJob.class)
                .withIdentity("CloseDoorJob", "DoorJobs")
                .build();
        closeJob.getJobDataMap().put("doorService", doorService);

        CronTrigger closeTrigger = newTrigger()
                .withIdentity("CloseTrigger", "DoorJobs")
                .withSchedule(cronSchedule(currentCloseExpression))
                .build();

        sched.scheduleJob(closeJob, closeTrigger);
        sched.start();
    }

    public String getCurrentOpenExpression() {
        return currentOpenExpression;
    }

    public String getCurrentCloseExpression() {
        return currentCloseExpression;
    }

    public void scheduleOpening(String expression) throws SchedulerException {

        auditService.audit("audit.open.scheduling.invoked", currentOpenExpression, expression);

        if (currentOpenExpression.equals(expression)) {
            logger.warn("expression is the same, nothing to be done for open trigger.");
            return;
        }
        else {
            currentOpenExpression = expression;
            sched.deleteJob(openJob.getKey());
            CronTrigger openTrigger = newTrigger()
                    .withIdentity("OpenTrigger", "DoorJobs")
                    .withSchedule(cronSchedule(currentOpenExpression))
                    .build();

            sched.scheduleJob(openJob, openTrigger);
            logger.info("open job scheduled again with new expression {}", expression);
        }

        auditService.audit("audit.open.scheduling.finished");

    }

    public void scheduleClosing(String expression) throws SchedulerException {

        auditService.audit("audit.close.scheduling.invoked", currentCloseExpression, expression);

        if (currentCloseExpression.equals(expression)) {
            logger.warn("expression is the same, nothing to be done for close trigger.");
            return;
        }
        else {
            currentCloseExpression = expression;
            sched.deleteJob(closeJob.getKey());
            CronTrigger closeTrigger = newTrigger()
                    .withIdentity("CloseTrigger", "DoorJobs")
                    .withSchedule(cronSchedule(currentCloseExpression))
                    .build();

            sched.scheduleJob(closeJob, closeTrigger);
            logger.info("close job scheduled again with new expression {}", expression);
        }

        auditService.audit("audit.close.scheduling.finished");
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
