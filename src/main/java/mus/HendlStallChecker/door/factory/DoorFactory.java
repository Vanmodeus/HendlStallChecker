package mus.HendlStallChecker.door.factory;

public class DoorFactory {
	private DoorFactory(){}
	
	
	public static IDoor CreateDoorHandler(){
		if(System.getProperty("os.name").contains("Windows"))
			return new DoorWindows();
		return new DoorRaspberryLed();
	}
}
