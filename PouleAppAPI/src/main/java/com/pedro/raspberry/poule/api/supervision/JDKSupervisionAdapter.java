package com.pedro.raspberry.poule.api.supervision;

import com.pedro.raspberry.poule.adapter.supervision.Insight;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionInsight;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Profile("default")
public class JDKSupervisionAdapter implements SupervisionAdapter {

    @Override
    public Optional<Insight> getInsights() {
        long freeBytes = Runtime.getRuntime().freeMemory();
        long freeMBytes = freeBytes / (1024 * 1024);
        return Optional.of(new Insight("62", "1.2", Float.toString(freeMBytes)));
    }
}
