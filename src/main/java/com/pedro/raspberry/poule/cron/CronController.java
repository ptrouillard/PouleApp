package com.pedro.raspberry.poule.cron;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CronController {

    @Autowired
    private CronService service;

    @GetMapping("/cron")
    public String cron(Model model) {
        try {
            model.addAttribute("cron", prepareCommand());
        } catch (SchedulerException e) {
            model.addAttribute("error", "cron.error.prepare");
        }
        return "cron";
    }

    @PostMapping("/cron/open")
    public String open(Model model, @ModelAttribute CronCommand command) {
        try {
            service.scheduleOpening(command.getOpenExpression());
            model.addAttribute("cron", prepareCommand());
            model.addAttribute("info", "cron.info.open.expression.modified");
        } catch (SchedulerException e) {
            model.addAttribute("error", "cron.error.open.schedule");
        }
        return "cron";
    }

    @PostMapping("/cron/close")
    public String close(Model model, @ModelAttribute CronCommand command) throws SchedulerException {
        try {
            service.scheduleClosing(command.getCloseExpression());
            model.addAttribute("cron", prepareCommand());
            model.addAttribute("info", "cron.info.close.expression.modified");
        } catch (SchedulerException e) {
            model.addAttribute("error", "cron.error.close.schedule");
        }
        return "cron";
    }

    @PostMapping("/cron/shutdown")
    public String shutdown(Model model) throws SchedulerException {
        service.pauseScheduler();
        model.addAttribute("cron", prepareCommand());
        model.addAttribute("info", "cron.info.scheduler.paused");
        return "cron";
    }

    @PostMapping("/cron/start")
    public String start(Model model) throws SchedulerException {
        service.resumeScheduler();
        model.addAttribute("cron", prepareCommand());
        model.addAttribute("info", "cron.info.scheduler.resumed");
        return "cron";
    }

    private CronCommand prepareCommand() throws SchedulerException {
        CronCommand command = new CronCommand();
        command.setOpenExpression(service.getCurrentOpenExpression());
        command.setCloseExpression(service.getCurrentCloseExpression());
        command.setSchedulerStarted(service.isSchedulerStarted());
        return command;
    }
}
