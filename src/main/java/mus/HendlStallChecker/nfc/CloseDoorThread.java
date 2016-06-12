package mus.HendlStallChecker.nfc;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import mus.utility.HendlStallUtility;

public class CloseDoorThread implements Runnable{

	public void run() {
		try {
			Thread.sleep(HendlStallUtility.getCloseDoorTimeoutSeconds()*1000);
			closeTheDoor();
		} catch (InterruptedException e) {
			System.err.println("we should not be interrupted");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Couldn't close the door as some unfortunate sun-wind hit our memory and caused the fault-free program to interrupt! Really!");
			e.printStackTrace();
		}
	}

	private void closeTheDoor() {
		if(System.getProperty("os.name").contains("Windows"))
			System.out.println("closing door...");
		else{
			final GpioController gpio = GpioFactory.getInstance();
			GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
			myLed.setState(PinState.LOW);
			gpio.shutdown();
			gpio.unprovisionPin(myLed);
		}
	}

}
