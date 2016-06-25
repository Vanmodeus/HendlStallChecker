package mus.HendlStallChecker.door;

import mus.HendlStallChecker.door.factory.DoorFactory;
import mus.utility.HendlStallUtility;

/**
 * Thread for closing the door. Gets an implementation of IDoor (currently
 * depends on the OS, for raspberry you'll switch on a LED, on Windows you'll
 * get a System.out.println-Message
 *
 */
public class CloseDoorThread implements Runnable {

	public void run() {
		try {
			Thread.sleep(HendlStallUtility.getCloseDoorTimeoutSeconds() * 1000);
			DoorFactory.CreateDoorHandler().closeDoor();
		} catch (InterruptedException e) {
			System.out.println("CloseDoorThread: Interrupted because door is opening again");
		} catch (Exception e) {
			System.err.println(
					"Couldn't close the door as some unfortunate sun-wind hit our memory and caused the fault-free program to interrupt! Really!");
			e.printStackTrace();
		}
	}
}
