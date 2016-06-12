package mus.HendlStallChecker;

import mus.HendlStallChecker.door.OpenDoorThread;
import mus.HendlStallChecker.intrusion.detection.IntrusionDetectorThread;

public class HendlStallVerwalter {
	
	private Thread openDoorThread;
	private Thread intrusionThread;
	private IntrusionDetectorThread intrusionRunnable = new IntrusionDetectorThread(); 
	
	public void startOpenDoorThread(){
		openDoorThread = new Thread(new OpenDoorThread());
		openDoorThread.start();
	}
	
	public void startIntrusionDetection(){
		intrusionThread = new Thread(intrusionRunnable);
		intrusionThread.start();
	}
	
	public void stopAllThread(){
		System.out.println("stopping all threads...");
		try {
			openDoorThread.stop(); //actually doesn't stop as synchronous method is blocking
			
			intrusionRunnable.end();
			intrusionThread.join();
		} catch (InterruptedException e) {
			System.err.println("Error interrupting threads!");
			e.printStackTrace();
		}
	}
}
