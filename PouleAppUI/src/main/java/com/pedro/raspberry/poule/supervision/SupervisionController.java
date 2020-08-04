package com.pedro.raspberry.poule.supervision;

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

        model.addAttribute(SupervisionInsight.CpuTemperature.name(),
                supervisionAdapter.getInsight(SupervisionInsight.CpuTemperature));
        model.addAttribute(SupervisionInsight.CpuCoreVoltage.name(),
                supervisionAdapter.getInsight(SupervisionInsight.CpuCoreVoltage));
        model.addAttribute(SupervisionInsight.MemoryFree.name(),
                supervisionAdapter.getInsight(SupervisionInsight.MemoryFree));

        return "supervision";
    }
}
