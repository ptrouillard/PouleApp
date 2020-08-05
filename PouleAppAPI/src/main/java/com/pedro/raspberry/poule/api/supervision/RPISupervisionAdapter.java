package com.pedro.raspberry.poule.api.supervision;

import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionInsight;
import com.pi4j.system.SystemInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("prod")
public class RPISupervisionAdapter implements SupervisionAdapter {

    public String getInsight(SupervisionInsight key) throws IOException, InterruptedException {

        float value;
        switch(key) {
            case CpuTemperature : value = SystemInfo.getCpuTemperature(); break;
            case CpuCoreVoltage : value = SystemInfo.getCpuVoltage(); break;
            case MemoryFree : value = SystemInfo.getMemoryFree(); break;
            default: value= -1f;
        }
       return Float.toString(value);
    }
}
