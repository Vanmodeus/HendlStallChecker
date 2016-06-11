package mus.HendlStallChecker;

import mus.HendlStallChecker.nfc.OpenDoorThread;

public class HendlStallVerwalter {

	public void startOpenDoorThread(){
		(new OpenDoorThread()).run();
	}
}
