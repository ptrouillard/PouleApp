package com.pedro.raspberry.poule.api.door;

import com.pedro.raspberry.poule.adapter.door.DoorAdapter;
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

@Service("doorAdapterImpl")
@Profile("prod")
public class DoorAdapterImpl implements DoorAdapter {

    private static Logger logger = LoggerFactory.getLogger(DoorAdapterImpl.class.getName());

    private Optional<Pins> physical = Optional.empty();

    @Autowired
    private DoorAdapter doorAdapter;

    private class Pins {
        public GpioController gpio;
        public GpioPinDigitalOutput M1En;
        public GpioPinDigitalOutput M1_In1;
        public GpioPinDigitalOutput M1_In2;

        public Pins(GpioController gpio, GpioPinDigitalOutput m1En, GpioPinDigitalOutput m1_In1, GpioPinDigitalOutput m1_In2) {
            this.gpio = gpio;
            M1En = m1En;
            M1_In1 = m1_In1;
            M1_In2 = m1_In2;
        }
    }

    @PostConstruct
    private void initGPIO() {

        GpioController gpio;
        GpioPinDigitalOutput M1En;
        GpioPinDigitalOutput M1_In1;
        GpioPinDigitalOutput M1_In2;

        try {
            // create gpio controller instance
            gpio = GpioFactory.getInstance();

            // provision gpio pins #04 as an output pin and make sure is is set to LOW at startup
            M1En = gpio.provisionDigitalOutputPin(DoorUsedPins.PIN_ENABLE.getPin(),
                    "M1_En",
                    PinState.LOW);

            M1_In1 = gpio.provisionDigitalOutputPin(DoorUsedPins.PIN1.getPin(),
                    "M1_In1",
                    PinState.LOW);

            M1_In2 = gpio.provisionDigitalOutputPin(DoorUsedPins.PIN2.getPin(),
                    "M1_In2",
                    PinState.LOW);

            logger.info("Init GPIO OK");
            physical = Optional.of(new Pins(gpio, M1En, M1_In1, M1_In2));

        } catch (UnsatisfiedLinkError error) {
            logger.error("Cannot invoke this method without Raspberry. You must deploy the application on the Rasp or use a mock instance of this service (doorServiceMock)");
        }
    }

    @Override
    public void stepUp(final long ms) {

        logger.info("stepUp invoked with {} ms", ms);
        physical.ifPresent( p -> {
            try {
                p.M1_In1.high();
                p.M1_In2.low();
                start(p);
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                logger.error("thread interrupted while stepping up", e);
            } finally {
                stop(p);
            }
        });
    }

    @Override
    public void stepDown(long ms) {

        logger.info("stepDown invoked with {} ms", ms);
        physical.ifPresent( p -> {
            try {
                p.M1_In2.high();
                p.M1_In1.low();
                start(p);
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                logger.error("thread interrupted while stepping down", e);
            } finally {
                stop(p);
            }
        });
    }

    public void start(Pins p) {
        p.M1En.high();
/*        SoftPwm.softPwmCreate(DoorUsedPins.PIN1.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmWrite(DoorUsedPins.PIN1.getPin().getAddress(), 100); // 100% de la vitesse
        SoftPwm.softPwmCreate(DoorUsedPins.PIN2.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmWrite(DoorUsedPins.PIN2.getPin().getAddress(), 100); // 100% de la vitesse
  */  }

    public void stop(Pins p) {
        try {
            logger.info("stop motor invoked");
            p.M1_In1.low();
            p.M1_In2.low();
            p.M1En.low();
        } finally {
            //p.gpio.shutdown();
        }
    }
}
