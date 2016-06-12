package mus.HendlStallChecker.nfc;

import mus.HendlStallChecker.nfc.factory.DoorFactory;
import mus.utility.HendlStallUtility;

public class CloseDoorThread implements Runnable{

	public void run() {
		try {
			Thread.sleep(HendlStallUtility.getCloseDoorTimeoutSeconds()*1000);
			DoorFactory.CreateDoorHandler().closeDoor();
		} catch (InterruptedException e) {
			System.err.println("we should not be interrupted");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Couldn't close the door as some unfortunate sun-wind hit our memory and caused the fault-free program to interrupt! Really!");
			e.printStackTrace();
		}
	}
}
