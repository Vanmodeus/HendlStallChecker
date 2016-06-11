package mus.HendlStallChecker;

import mus.HendlStallChecker.nfc.OpenDoorThread;

public class HendlStallVerwalter {

	public void startOpenDoorThread(){
		OpenDoorThread thread = new OpenDoorThread();
		thread.run();
	}
}
