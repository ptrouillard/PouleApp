package com.pedro.raspberry.poule.ui.config;

import com.pedro.raspberry.poule.ui.cron.CronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class ConfigController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ConfigService service;

    @Autowired
    private CronService cronService;

    @GetMapping("/config")
    public String config(Model model) {
        model.addAttribute("config", service.getConfig());
        return "config";
    }

    /**
     * Attention : Le binding result est important pour la validation des champs côté client.
     */
    @PostMapping("/config/save")
    public String save(Model model, @Valid Config config, BindingResult result) {
        try {

            Config old = service.getConfig();
            // notify the cron service if scheduling is impacted.
            cronService.updateConfig(old, config);
            service.save(config);
            model.addAttribute("config", service.getConfig());
            model.addAttribute("info", "config.info.saved");
        } catch (IOException e) {
            logger.error("Error occured while saving configuration", e);
            model.addAttribute("error", "config.error.saved");
        } finally {
            return "config";
        }
    }
}
