package com.pedro.raspberry.poule.ui.supervision.mock;

import com.pedro.raspberry.poule.adapter.supervision.Insight;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("default")
public class MockSupervisionAdapter implements SupervisionAdapter {

    @Override
    public Optional<Insight> getInsights() {
        return Optional.of(new Insight("NC (mock)", "NC (mock)", "NC (mock)"));
    }
}
