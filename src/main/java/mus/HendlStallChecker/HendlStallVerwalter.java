package mus.HendlStallChecker;

import mus.HendlStallChecker.door.OpenDoorThread;
import mus.HendlStallChecker.intrusion.detection.IntrusionDetectorThread;

/**
 * Main-Class for the Hendlstall Holds two Threads, the openDoorThread for
 * NFC-Detection And the intrusionThread which checks the webcam periodically
 * and closes the door if necessary
 * 
 * @author Vanmodeus
 *
 */
public class HendlStallVerwalter {

	private Thread openDoorThread;
	private Thread intrusionThread;
	private IntrusionDetectorThread intrusionRunnable = new IntrusionDetectorThread();

	public void startOpenDoorThread() {
		openDoorThread = new Thread(new OpenDoorThread());
		openDoorThread.start();
	}

	public void startIntrusionDetection() {
		intrusionThread = new Thread(intrusionRunnable);
		intrusionThread.start();
	}

	public void stopAllThread() {
		System.out.println("stopping all threads...");
		try {
			// actually doesn't stop as synchronous method is blocking
			openDoorThread.stop();

			intrusionRunnable.end();
			intrusionThread.join();
		} catch (InterruptedException e) {
			System.err.println("Error interrupting threads!");
			e.printStackTrace();
		}
	}
}
