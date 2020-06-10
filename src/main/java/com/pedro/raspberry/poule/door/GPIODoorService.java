package com.pedro.raspberry.poule.door;

import com.pi4j.io.gpio.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("doorService")
@Profile("prod")
public class GPIODoorService implements DoorService {

    private GpioController gpio;
    private GpioPinDigitalOutput M1En;
    private GpioPinDigitalOutput M1_In1;
    private GpioPinDigitalOutput M1_In2;

    @PostConstruct
    public void initGPIO() {

        try {
            // create gpio controller instance
            gpio = GpioFactory.getInstance();

            // provision gpio pins #04 as an output pin and make sure is is set to LOW at startup
            M1En = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21,
                    "M1_En",
                    PinState.LOW);

            M1_In1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_20,
                    "M1_In1",
                    PinState.LOW);

            M1_In2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16,
                    "M1_In2",
                    PinState.LOW);
            // TODO
            //gpio.setMode(PinMode.BCM);
            gpio.setMode(PinMode.PWM_OUTPUT, M1En);

        } catch (UnsatisfiedLinkError error) {
            System.out.println("Cannot invoke this method without Raspberry. You must deploy the application on the Rasp or use a mock instance of this service (doorServiceMock)");
        }
    }

    public void stepUp(long ms) {
        stop();
        M1_In1.pulse(ms);
        stop();
    }

    public void stepDown(long ms) {
        stop();
        M1_In2.pulse(ms);
        stop();
    }

    public void stop() {
        M1_In1.low();
        M1_In2.low();
    }
}
