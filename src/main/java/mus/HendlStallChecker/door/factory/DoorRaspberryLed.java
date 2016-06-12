package mus.HendlStallChecker.door.factory;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class DoorRaspberryLed implements IDoor {

	public void closeDoor() {
		System.out.println("closing door...");

		final GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
		myLed.setState(PinState.LOW);
		gpio.shutdown();
		gpio.unprovisionPin(myLed);
	}

	public void openDoor() {
		System.out.println("opening door...");

		final GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
		myLed.setState(PinState.LOW);
		myLed.setState(PinState.HIGH);
		gpio.shutdown();
		gpio.unprovisionPin(myLed);
	}

}
