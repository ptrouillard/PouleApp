package com.pedro.raspberry.poule.ui.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String indexToDashboard(Model model, HttpSession session) {
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }
}
