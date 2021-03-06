package com.pedro.raspberry.poule.ui.cron;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CronController {

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

    @PostMapping("/cron/open")
    public String open(Model model, @ModelAttribute CronCommand command) {
        try {
            service.scheduleOpening(command.getOpenExpression());
            model.addAttribute("cron", prepareCommand());
            model.addAttribute("info", "cron.info.open.expression.modified");
            prepareAttributes(model);
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
            prepareAttributes(model);
        } catch (SchedulerException e) {
            model.addAttribute("error", "cron.error.close.schedule");
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

    private CronCommand prepareCommand() throws SchedulerException {
        CronCommand command = new CronCommand();
        command.setOpenExpression(service.getCurrentOpenExpression());
        command.setCloseExpression(service.getCurrentCloseExpression());
        return command;
    }
}
