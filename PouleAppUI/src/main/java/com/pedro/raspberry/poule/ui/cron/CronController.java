package com.pedro.raspberry.poule.ui.cron;

import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CronController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CronService service;

    @GetMapping("/cron")
    public String cron(Model model) {
        try {
            model.addAttribute("cron", prepareCommand());
            prepareAttributes(model);
        } catch (SchedulerException e) {
            model.addAttribute("error", "cron.error.prepare");
        }
        return "cron";
    }

    @PostMapping("/cron/shutdown")
    public String shutdown(Model model, HttpServletRequest request) throws SchedulerException {
        service.pauseScheduler(request.getRemoteAddr());
        model.addAttribute("cron", prepareCommand());
        model.addAttribute("info", "cron.info.scheduler.paused");
        prepareAttributes(model);
        return "cron";
    }

    @PostMapping("/cron/start")
    public String start(Model model, HttpServletRequest request) throws SchedulerException {
        service.resumeScheduler(request.getRemoteAddr());
        model.addAttribute("cron", prepareCommand());
        model.addAttribute("info", "cron.info.scheduler.resumed");
        prepareAttributes(model);
        return "cron";
    }

    private void prepareAttributes(Model model) throws SchedulerException {
        model.addAttribute("schedulerStarted", service.isSchedulerStarted());
        model.addAttribute("schedulerShutdown", service.isShutdown());
        model.addAttribute("schedulerPaused", service.isPaused());
    }

    private String format2Pad(String raw) {
        return String.format("%02d", Integer.parseInt(raw));
    }

    private CronCommand prepareCommand() throws SchedulerException {
        CronCommand command = new CronCommand();
        command.setCloseHour(format2Pad(service.getCurrentCloseHour()));
        command.setCloseMinutes(format2Pad(service.getCurrentCloseMinutes()));
        command.setOpenHour(format2Pad(service.getCurrentOpenHour()));
        command.setOpenMinutes(format2Pad(service.getCurrentOpenMinutes()));

        try {
            CronExpression closeExpression =
                    new CronExpression(service.getCronExpression(service.getCurrentCloseHour(), service.getCurrentCloseMinutes()));
            CronExpression openExpression =
                    new CronExpression(service.getCronExpression(service.getCurrentOpenHour(), service.getCurrentOpenMinutes()));

            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat();
            command.setNextClosing(format.format(closeExpression.getNextValidTimeAfter(now)));
            command.setNextOpening(format.format(openExpression.getNextValidTimeAfter(now)));

        } catch ( ParseException e) {
            logger.error("Parsing error can be due to invalid date/time, please check configuration of close/open times.", e);
        }
        return command;
    }
}
