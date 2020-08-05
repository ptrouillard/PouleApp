package com.pedro.raspberry.poule.ui.door.remote;

import com.pedro.raspberry.poule.adapter.door.DoorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component("doorAdapterRemote")
@Profile("prod")
public class RemoteDoorAdapter implements DoorAdapter {

    private static Logger logger = LoggerFactory.getLogger(RemoteDoorAdapter.class.getName());

    @Override
    public void stepUp(final long ms) {

        logger.info("stepUp invoked with {} ms", ms);
    }

    @Override
    public void stepDown(long ms) {

        logger.info("stepDown invoked with {} ms", ms);
    }

}
