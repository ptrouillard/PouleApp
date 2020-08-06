package com.pedro.raspberry.poule.ui.supervision.mock;

import com.pedro.raspberry.poule.adapter.supervision.Insight;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("default")
public class MockSupervisionAdapter implements SupervisionAdapter {

    @Override
    public Insight getInsights() throws IOException, InterruptedException {
        return new Insight("NC (mock)", "NC (mock)", "NC (mock)");
    }
}
