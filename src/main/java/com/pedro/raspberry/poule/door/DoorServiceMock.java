package com.pedro.raspberry.poule.door;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("doorServiceMock")
@Profile("default")
public class DoorServiceMock implements DoorService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @PostConstruct
    public void initGPIO() {
        logger.info("<MOCK> InitGPIO inoked");
    }

    public void stepUp(long ms) {
        logger.info("<MOCK> stepUp invoked with {} ms", ms);
    }

    public void stepDown(long ms) {
        logger.info("<MOCK> stepDown invoked with {} ms", ms);
    }

    public void stop() {
        logger.info("<MOCK> stop invoked");
    }
}
