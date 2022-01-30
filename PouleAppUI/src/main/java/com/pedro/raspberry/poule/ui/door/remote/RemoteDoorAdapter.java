package com.pedro.raspberry.poule.ui.door.remote;

import com.pedro.raspberry.poule.adapter.door.DoorActionResult;
import com.pedro.raspberry.poule.adapter.door.DoorAdapter;
import com.pedro.raspberry.poule.ui.config.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("doorAdapterRemote")
@Profile("prod")
public class RemoteDoorAdapter implements DoorAdapter {

    private static Logger logger = LoggerFactory.getLogger(RemoteDoorAdapter.class.getName());

    @Autowired
    private ConfigService service;

    @Override
    public void stepUp(final long ms) {
        String url =service.getConfig().getApiDoorUrl() +"/up?ms=" + ms;
        doorCall(url);
    }

    @Override
    public void stepDown(long ms) {
        String url = service.getConfig().getApiDoorUrl()+"/down?ms=" + ms;
        doorCall(url);
    }

    private void doorCall(String url) {

        logger.info("api call '{}' invoked", url);

        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();
        ResponseEntity<DoorActionResult> doorAction = restTemplate.getForEntity(url, DoorActionResult.class);
        if (doorAction.getStatusCode() == HttpStatus.OK) {
            logger.info("api call returned with success");
        } else {
            logger.error("api call returned the error code {} : error description is '{}'", doorAction.getStatusCode(), doorAction.getBody().getMessage());
        }
    }


}
