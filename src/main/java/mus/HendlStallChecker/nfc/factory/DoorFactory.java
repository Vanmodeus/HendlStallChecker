package mus.HendlStallChecker.nfc.factory;

public class DoorFactory {
	private DoorFactory(){}
	
	
	public static IDoor CreateDoorHandler(){
		if(System.getProperty("os.name").contains("Windows"))
			return new DoorWindows();
		return new DoorRaspberryLed();
	}
}
