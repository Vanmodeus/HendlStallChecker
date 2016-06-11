package mus.HendlStallChecker.nfc;

import java.util.Date;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import mus.HendlStallChecker.Repository.DbChickenRepo;
import mus.HendlStallChecker.Repository.DbFactory;
import mus.HendlStallChecker.Repository.Log;
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
				
				//activate led
				openDoor();
				
				//start close door thread
				(new CloseDoorThread()).run();
				
			} catch (Exception e) {
				System.err.println("exception while waiting for nfc card");
				e.printStackTrace();
			}
		}
	}

	private void openDoor() {
		if(System.getProperty("os.name").contains("Windows"))
			System.out.println("opening door...");
		else{
			final GpioController gpio = GpioFactory.getInstance();
			GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
			myLed.setState(PinState.HIGH);			
		}
	}

}
