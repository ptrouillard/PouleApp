package com.pedro.raspberry.poule.api.supervision;

import com.pedro.raspberry.poule.adapter.supervision.Insight;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionInsight;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("default")
public class JDKSupervisionAdapter implements SupervisionAdapter {

    @Override
    public Insight getInsights() throws IOException, InterruptedException {
        return new Insight("NC", "NC", Float.toString(Runtime.getRuntime().freeMemory()));
    }
}
