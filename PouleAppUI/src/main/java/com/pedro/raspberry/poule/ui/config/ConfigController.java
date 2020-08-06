package com.pedro.raspberry.poule.ui.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ConfigController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ConfigService service;

    @GetMapping("/config")
    public String config(Model model) {
        model.addAttribute("config", prepareConfig());
        return "config";
    }

    @PostMapping("/config/save")
    public String save(Model model, @ModelAttribute Config command) {
        try {
            service.save(command);
            model.addAttribute("config", prepareConfig());
            model.addAttribute("info", "config.info.saved");
        } catch (IOException e) {
            logger.error("Error occured while saving configuration", e);
            model.addAttribute("error", "config.error.saved");
        }
        return "config";
    }

    private Config prepareConfig() {
        Config command = new Config();
        command.setWebcamUrl(service.getWebcamUrl());
        command.setApiSupervisionUrl(service.getSupervisionUrl());
        command.setApiDoorUrl(service.getDoorUrl());
        return command;
    }
}
