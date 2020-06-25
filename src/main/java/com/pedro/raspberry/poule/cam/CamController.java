package com.pedro.raspberry.poule.cam;

import com.pedro.raspberry.poule.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CamController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/cam")
    public String dashboard(Model model) {
        model.addAttribute("url", configService.getWebcamUrl());
        return "cam";
    }
}
