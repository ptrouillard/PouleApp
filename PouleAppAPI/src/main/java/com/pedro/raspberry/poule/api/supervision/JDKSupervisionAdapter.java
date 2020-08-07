package com.pedro.raspberry.poule.api.supervision;

import com.pedro.raspberry.poule.adapter.supervision.Insight;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

import static com.pedro.raspberry.poule.api.supervision.BytesTo.toMB;

@Component
@Profile("default")
public class JDKSupervisionAdapter implements SupervisionAdapter {

    @Override
    public Optional<Insight> getInsights() {
        long freeBytes = Runtime.getRuntime().freeMemory();
        long totalBytes = Runtime.getRuntime().totalMemory();
        return Optional.of(new Insight("62", "1.2", Float.toString(toMB(freeBytes)), Float.toString(toMB(totalBytes))));
    }


}
