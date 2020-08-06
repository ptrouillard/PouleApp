package com.pedro.raspberry.poule.api.supervision;

import com.pedro.raspberry.poule.adapter.supervision.Insight;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionInsight;
import com.pi4j.system.SystemInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("prod")
public class RPISupervisionAdapter implements SupervisionAdapter {

    @Override
    public Insight getInsights() throws IOException, InterruptedException {

        Insight insight = new Insight(Float.toString(SystemInfo.getCpuTemperature()),
                Float.toString(SystemInfo.getCpuVoltage()),
                Float.toString(SystemInfo.getMemoryFree()));

        return insight;
    }
}
