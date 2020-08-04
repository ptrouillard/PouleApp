package com.pedro.raspberry.poule.api;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * The pin used are 29,28,27 according to Pi4j (wiringPi lib)
 */
public enum DoorUsedPins {
    PIN1(RaspiPin.GPIO_28), PIN2(RaspiPin.GPIO_27), PIN_ENABLE(RaspiPin.GPIO_29);

    private Pin pin;

    DoorUsedPins(Pin pin) {
        this.pin = pin;
    }

    public Pin getPin() {
        return pin;
    }
}
