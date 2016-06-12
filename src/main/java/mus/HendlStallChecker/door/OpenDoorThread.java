package mus.HendlStallChecker.door;

import java.util.Calendar;
import java.util.Date;

import mus.HendlStallChecker.Repository.DbChickenRepo;
import mus.HendlStallChecker.Repository.DbFactory;
import mus.HendlStallChecker.Repository.Log;
import mus.HendlStallChecker.door.factory.DoorFactory;
import mus.periphery.NFCController;

public class OpenDoorThread implements Runnable {

	private Thread closeDoorThread;
	
	public void run() {
		System.out.println("OPEN DOOR thread started...");
		NFCController controller = new NFCController();
		while(true){
			try {
				String nfcid = controller.waitForNfcCard();
				System.out.println(nfcid);
				
				//interrupt close door thread
				if(closeDoorThread != null && closeDoorThread.isAlive()){
					closeDoorThread.interrupt();
					closeDoorThread.join();
				}
				
				//update status of chicken in DB
				DbChickenRepo repo = DbFactory.Instance().CreateDbChickenRepo();
				repo.changeAvailability(nfcid);
				
				//insert log entry
				long chickId = repo.get(nfcid).getId();
				Date time = Calendar.getInstance().getTime();
				DbFactory.Instance().CreateDbLogRepo().insert(new Log(chickId, time));
				
				//open door
				DoorFactory.CreateDoorHandler().openDoor();
				
				//start close door thread
				closeDoorThread = new Thread(new CloseDoorThread());
				closeDoorThread.start();
			}
			catch(InterruptedException e){
				System.out.println("stopping door thread...");
			}
			catch (Exception e) {
				System.err.println("exception while waiting for nfc card - stopping service!");
				e.printStackTrace();
				break;
			}
		}
	}
}
