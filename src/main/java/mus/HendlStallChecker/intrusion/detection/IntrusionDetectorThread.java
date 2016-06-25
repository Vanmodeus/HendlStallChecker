package mus.HendlStallChecker.intrusion.detection;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.List;

import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.common.collect.ImmutableSet;
import com.pi4j.io.gpio.RaspiPin;

import mus.HendlStallChecker.Repository.DbFactory;
import mus.HendlStallChecker.Repository.Intrusion;
import mus.HendlStallChecker.Repository.IntrusionAlertLevel;
import mus.periphery.ImageTagger;
import mus.periphery.OnvifExtractor;
import mus.utility.Helper;
import mus.utility.HendlStallUtility;
import mus.utility.PlatformHelper;

/**
 * The IntrusionDetectorThread-class check our webcam periodically, saves the
 * current snapshot on the temp-dir, sends this image to google for
 * labeling-purposes and checks if any thread is detected (like a fox)
 * 
 * @author Vanmodeus
 *
 */
public class IntrusionDetectorThread implements Runnable {

	private ImageTagger imageTagger;
	private static final int MAX_LABELS = 10;

	private boolean stopped = false;

	public void end() {
		stopped = true;
	}

	public void run() {
		System.out.println("INTRUSION DETECTOR thread started...");

		try {
			imageTagger = new ImageTagger(ImageTagger.getVisionService());
		} catch (IOException e1) {
			System.err.println("error initializing the image tagger: IOException");
			e1.printStackTrace();
		} catch (GeneralSecurityException e1) {
			System.err.println("error initializing the image tagger: SecurityException");
			e1.printStackTrace();
		}

		while (!stopped) {

			try {
				// capture camera image
				if (!new OnvifExtractor().extractPicture()) {
					this.end();
					throw new IllegalStateException("Error taking picture from camera! Stopping now");
				}

				// tag image
				ImmutableSet<String> descriptions = tagImage();

				// detect intruder
				IntrusionAlertLevel level = getThreadDetectionLevel(descriptions);

				// alarm
				if (level == IntrusionAlertLevel.CRITICAL_SPECIFIC) {
					PlatformHelper.touchLed(RaspiPin.GPIO_01, true);
					// PlatformHelper.blink(RaspiPin.GPIO_01, 100, 2000);
					Thread.sleep(1000);
					PlatformHelper.touchLed(RaspiPin.GPIO_01, false);
				} else if (level == IntrusionAlertLevel.SEVERE_CARNIVORE) {
					PlatformHelper.touchLed(RaspiPin.GPIO_02, true);
					Thread.sleep(1000);
					PlatformHelper.touchLed(RaspiPin.GPIO_02, false);
				}

				if (level.getValue() >= IntrusionAlertLevel.valueOf(HendlStallUtility.getIntrusionLogLevel())
						.getValue()) {
					// log
					System.out.println("intruder: " + level + "!");

					// db insert
					byte[] img = Helper.convertCameraImage();
					Intrusion intru = new Intrusion(level.getValue(), Calendar.getInstance().getTime(), img);
					long id = DbFactory.Instance().CreateDbIntrusionRepo().insert(intru);
					if (id == -1)
						throw new IllegalStateException("Error inserting a new intrusion");
				}

				// wait
				Thread.sleep(HendlStallUtility.getFoxPictureTimeoutSeconds() * 1000);

			} catch (InterruptedException e) {
				System.err.println("Fox detector got interrupted");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Oh no! Sun-winds again!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checks the labes of the image for dangerous threats like foxes or
	 * carnivorous animals
	 * 
	 * @param descriptions
	 * @return
	 */
	private IntrusionAlertLevel getThreadDetectionLevel(ImmutableSet<String> descriptions) {
		boolean fox = descriptions.contains("fox");
		boolean marten = descriptions.contains("mustelidae");
		boolean carnivore = descriptions.contains("carnivoran");

		if (fox || marten)
			return IntrusionAlertLevel.CRITICAL_SPECIFIC;
		else if (carnivore)
			return IntrusionAlertLevel.SEVERE_CARNIVORE;
		return IntrusionAlertLevel.WARNING_UNDEFINED;
	}

	private ImmutableSet<String> tagImage() throws IOException {
		String file = getImageFile();

		List<EntityAnnotation> labels = imageTagger.labelImage(Paths.get(file), MAX_LABELS);

		ImmutableSet.Builder<String> builder = ImmutableSet.builder();
		for (EntityAnnotation label : labels) {
			builder.add(label.getDescription().toLowerCase());
		}
		ImmutableSet<String> descriptions = builder.build();
		return descriptions;
	}

	/**
	 * Gets the image from our predefined path at the current Tempdir of the OS
	 */
	private String getImageFile() {
		String tmpDir = System.getProperty("java.io.tmpdir");
		String file = Paths.get(tmpDir, "onvif.jpg").toAbsolutePath().toString();
		return file;
	}

}
