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
        model.addAttribute("config", prepareCommand());
        prepareAttributes(model);
        return "config";
    }

    @PostMapping("/config/save")
    public String save(Model model, @ModelAttribute ConfigCommand command) {
        try {
            service.save(command.getUrl());
            model.addAttribute("config", prepareCommand());
            model.addAttribute("info", "config.info.saved");
        } catch (IOException e) {
            logger.error("Error occured while saving configuration", e);
            model.addAttribute("error", "config.error.saved");
        }
        prepareAttributes(model);
        return "config";
    }

    private void prepareAttributes(Model model) {
        model.addAttribute("url", service.getWebcamUrl());
    }

    private ConfigCommand prepareCommand() {
        ConfigCommand command = new ConfigCommand();
        command.setUrl(service.getWebcamUrl());
        return command;
    }
}
