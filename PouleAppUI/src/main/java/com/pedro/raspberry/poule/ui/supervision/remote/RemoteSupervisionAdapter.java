package com.pedro.raspberry.poule.ui.supervision.remote;

import com.pedro.raspberry.poule.adapter.supervision.Insight;
import com.pedro.raspberry.poule.adapter.supervision.InsightResult;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import com.pedro.raspberry.poule.ui.config.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.Optional;

@Component
@Profile("prod")
public class RemoteSupervisionAdapter implements SupervisionAdapter {

    private Logger logger = LoggerFactory.getLogger(RemoteSupervisionAdapter.class.getName());

    @Autowired
    private ConfigService service;

    @Override
    public Optional<Insight> getInsights() {
        try {
            RestTemplateBuilder builder = new RestTemplateBuilder();
            RestTemplate restTemplate = builder.build();
            ResponseEntity<InsightResult> insights = restTemplate.getForEntity(service.getSupervisionUrl(), InsightResult.class);
            return Optional.of(insights.getBody().getInsight());
        } catch (RuntimeException e) {
            logger.error("Cannot connect to API", e);
            return Optional.empty();
        }
    }
}
