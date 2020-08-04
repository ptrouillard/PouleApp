package com.pedro.raspberry.poule.supervision;

import com.pi4j.system.SystemInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("default")
public class JDKSupervisionAdapter implements SupervisionAdapter {

    @Override
    public String getInsight(SupervisionInsight key) throws IOException, InterruptedException {

        float value;
        switch(key) {
            case CpuTemperature : value = -1f; break;
            case CpuCoreVoltage : value = -1f; break;
            case MemoryFree : value = Runtime.getRuntime().freeMemory(); break;
            default: value= -1f;
        }
        return Float.toString(value);
    }
}
