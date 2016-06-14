package mus.HendlStallChecker.door.factory;

import com.pi4j.io.gpio.RaspiPin;

import mus.utility.PlatformHelper;

public class DoorRaspberryLed implements IDoor {
	public void openDoor() {
		System.out.println("opening door...");
		
		PlatformHelper.touchLed(RaspiPin.GPIO_00, true);
	}

	public void closeDoor() {
		System.out.println("closing door...");

		PlatformHelper.touchLed(RaspiPin.GPIO_00, false);
	}
}
