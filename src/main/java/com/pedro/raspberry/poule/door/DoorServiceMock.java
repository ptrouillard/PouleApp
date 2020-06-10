package com.pedro.raspberry.poule.door;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("doorServiceMock")
public class DoorServiceMock implements DoorService {

    @PostConstruct
    public void initGPIO() {
        System.out.println("InitGPIO inoked");
    }

    public void stepUp(long ms) {
        System.out.println("stepUp invoked with " + ms + " ms");
    }

    public void stepDown(long ms) {
        System.out.println("stepDown invoked with " + ms + " ms");
    }

    public void stop() {
        System.out.println("stop invoked");
    }
}
