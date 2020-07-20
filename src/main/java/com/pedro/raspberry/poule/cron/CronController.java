package com.pedro.raspberry.poule.cron;

import com.pedro.raspberry.poule.audit.AuditService;
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

    @Autowired
    private AuditService auditService;

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
            auditService.audit("Open scheduling is modified to " + command.getOpenExpression());
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
            auditService.audit("Close scheduling is modified to " + command.getCloseExpression());
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
    public String shutdown(Model model) throws SchedulerException {
        auditService.audit("Scheduling is paused");
        service.pauseScheduler();
        model.addAttribute("cron", prepareCommand());
        model.addAttribute("info", "cron.info.scheduler.paused");
        prepareAttributes(model);
        return "cron";
    }

    @PostMapping("/cron/start")
    public String start(Model model) throws SchedulerException {
        auditService.audit("Scheduling is resumed");
        service.resumeScheduler();
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
