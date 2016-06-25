package mus.HendlStallChecker.door.factory;

/**
 * DoorFactory for different behaviour on different OS
 * 
 * @author Vanmodeus
 *
 */
public class DoorFactory {
	private DoorFactory() {
	}

	public static IDoor CreateDoorHandler() {
		if (System.getProperty("os.name").contains("Windows"))
			return new DoorWindows();
		return new DoorRaspberryLed();
	}
}
