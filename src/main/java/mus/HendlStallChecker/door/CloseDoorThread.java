package mus.HendlStallChecker.door;

import mus.HendlStallChecker.door.factory.DoorFactory;
import mus.utility.HendlStallUtility;

public class CloseDoorThread implements Runnable{

	public void run() {
		try {
			Thread.sleep(HendlStallUtility.getCloseDoorTimeoutSeconds()*1000);
			DoorFactory.CreateDoorHandler().closeDoor();
		} catch (InterruptedException e) {
			System.out.println("CloseDoorThread: Interrupted because door is opening again");
		} catch (Exception e) {
			System.err.println("Couldn't close the door as some unfortunate sun-wind hit our memory and caused the fault-free program to interrupt! Really!");
			e.printStackTrace();
		}
	}
}
