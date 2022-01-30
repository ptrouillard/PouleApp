package com.pedro.raspberry.poule.ui.cam;

import com.pedro.raspberry.poule.ui.config.ConfigService;
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
        String url = configService.getConfig().getWebcamUrl();
        model.addAttribute("url", url);
        return "redirect:" + url;
    }
}
