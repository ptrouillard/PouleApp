package com.pedro.raspberry.poule.door;

public interface DoorService {
    void initGPIO();
    void stepUp(long ms);
    void stepDown(long ms);
    void stop();
}
