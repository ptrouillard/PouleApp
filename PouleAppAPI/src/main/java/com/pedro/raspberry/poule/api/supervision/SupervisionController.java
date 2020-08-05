package com.pedro.raspberry.poule.api.supervision;

import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionInsight;
import com.pedro.raspberry.poule.api.door.DoorActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SupervisionController {

    @Autowired
    public SupervisionAdapter adapter;

    /**
     * calling /insight will collect system insights
     * @return
     */
    @GetMapping("/insight")
    public InsightResult insight()
    {
        InsightResult result = null;
        try {
            result = InsightResult.success()
                    .withInsight(SupervisionInsight.CpuTemperature.name(), adapter.getInsight(SupervisionInsight.CpuTemperature))
                    .withInsight(SupervisionInsight.CpuCoreVoltage.name(), adapter.getInsight(SupervisionInsight.CpuCoreVoltage))
                    .withInsight(SupervisionInsight.MemoryFree.name(), adapter.getInsight(SupervisionInsight.MemoryFree));
        } catch (Exception e) {
            result = InsightResult.error(e.getMessage());
        }

        return result;
    }

}
