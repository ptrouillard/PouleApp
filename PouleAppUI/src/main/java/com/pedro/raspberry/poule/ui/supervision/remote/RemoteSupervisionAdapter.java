package com.pedro.raspberry.poule.ui.supervision.remote;

import com.pedro.raspberry.poule.adapter.supervision.Insight;
import com.pedro.raspberry.poule.adapter.supervision.InsightResult;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionInsight;
import com.pedro.raspberry.poule.ui.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
@Profile("prod")
public class RemoteSupervisionAdapter implements SupervisionAdapter {

    @Autowired
    private ConfigService service;

    @Override
    public Insight getInsights() throws IOException, InterruptedException {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();
        ResponseEntity<InsightResult> insights = restTemplate.getForEntity(service.getSupervisionUrl(), InsightResult.class);
        return insights.getBody().getInsight();
    }
}
