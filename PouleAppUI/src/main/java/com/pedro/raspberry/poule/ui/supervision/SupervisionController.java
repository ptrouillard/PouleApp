package com.pedro.raspberry.poule.ui.supervision;

import com.pedro.raspberry.poule.adapter.supervision.Insight;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class SupervisionController {

    @Autowired
    private SupervisionAdapter supervisionAdapter;

    @GetMapping("/supervision")
    public String supervision(Model model) throws IOException, InterruptedException {

        supervisionAdapter.getInsights().ifPresentOrElse( insight -> {
            model.addAttribute("insights", insight);
        }, () -> {
            model.addAttribute("error", "supervision.error");
            model.addAttribute("insights", Insight.error());
        });
        return "supervision";
    }
}
