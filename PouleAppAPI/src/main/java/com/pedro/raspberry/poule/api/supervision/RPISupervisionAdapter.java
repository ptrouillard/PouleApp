package com.pedro.raspberry.poule.api.supervision;

import com.pedro.raspberry.poule.adapter.supervision.Insight;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionInsight;
import com.pi4j.system.SystemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Profile("prod")
public class RPISupervisionAdapter implements SupervisionAdapter {

    private Logger logger = LoggerFactory.getLogger(RPISupervisionAdapter.class.getName());

    @Override
    public Optional<Insight> getInsights() {

        Insight insight = null;
        try {
            insight = new Insight(Float.toString(SystemInfo.getCpuTemperature()),
                    Float.toString(SystemInfo.getCpuVoltage()),
                    Float.toString(SystemInfo.getMemoryFree()));
            return Optional.of(insight);
        } catch (IOException | InterruptedException e) {
            logger.error("Error while fetching insights", e);
        }
        return Optional.empty();
    }
}
