package com.pedro.raspberry.poule.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("doorAdapterMock")
@Profile("default")
public class DoorAdapterMock implements DoorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @PostConstruct
    public void initGPIO() {
        logger.info("<MOCK> InitGPIO inoked");
    }

    @Override
    public void stepUp(long ms) {
        logger.info("<MOCK> stepUp invoked with {} ms", ms);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            logger.error("action interrupted", e);
        }
    }

    @Override
    public void stepDown(long ms) {
        logger.info("<MOCK> stepDown invoked with {} ms", ms);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            logger.error("action interrupted");
        }
    }
}
