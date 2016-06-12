package mus.HendlStallChecker.nfc;

import java.util.Date;

import mus.HendlStallChecker.Repository.DbChickenRepo;
import mus.HendlStallChecker.Repository.DbFactory;
import mus.HendlStallChecker.Repository.Log;
import mus.HendlStallChecker.nfc.factory.DoorFactory;
import mus.periphery.NFCController;

public class OpenDoorThread implements Runnable {

	public void run() {
		System.out.println("OPEN DOOR thread started...");
		NFCController controller = new NFCController();
		while(true){
			try {
				String nfcid = controller.waitForNfcCard();
				System.out.println(nfcid);
				
				//update status of chicken in DB
				DbChickenRepo repo = DbFactory.Instance().CreateDbChickenRepo();
				repo.changeAvailability(nfcid);
				
				//insert log entry
				long chickId = repo.get(nfcid).getId();
				DbFactory.Instance().CreateDbLogRepo().insert(new Log(chickId, new Date()));
				
				//open door
				DoorFactory.CreateDoorHandler().openDoor();
				
				//start close door thread
				(new CloseDoorThread()).run();
				
			} catch (Exception e) {
				System.err.println("exception while waiting for nfc card - stopping service!");
				e.printStackTrace();
				break;
			}
		}
	}
}
