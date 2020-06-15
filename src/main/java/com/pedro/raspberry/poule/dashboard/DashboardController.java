package com.pedro.raspberry.poule.dashboard;

import com.pedro.raspberry.poule.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/")
    public String indexToDashboard(Model model) {
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("url", configService.getWebcamUrl());
        return "dashboard";
    }
}
