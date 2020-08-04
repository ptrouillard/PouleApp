package com.pedro.raspberry.poule.door.local;

import com.pedro.raspberry.poule.door.DoorAdapter;
import com.pedro.raspberry.poule.door.DoorUsedPins;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service("doorAdapter")
public class RemoteDoorAdapter {

    private static Logger logger = LoggerFactory.getLogger(.class.getName());

    @Override
    public void stepUp(final long ms) {

        logger.info("stepUp invoked with {} ms", ms);
    }

    @Override
    public void stepDown(long ms) {

        logger.info("stepDown invoked with {} ms", ms);
    }

}
