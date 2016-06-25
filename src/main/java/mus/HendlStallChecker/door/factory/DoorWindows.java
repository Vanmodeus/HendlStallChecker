package mus.HendlStallChecker.door.factory;

/**
 * Concrete implementation of IDoor for Windows (just System.out.println for
 * testing)
 * 
 * @author Vanmodeus
 *
 */
public class DoorWindows implements IDoor {

	public void closeDoor() {
		System.out.println("closing door...");
	}

	public void openDoor() {
		System.out.println("opening door...");
	}

}
