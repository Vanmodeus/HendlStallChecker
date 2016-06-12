package mus.HendlStallChecker.intrusion.detection;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.common.collect.ImmutableSet;

import mus.periphery.ImageTagger;
import mus.periphery.OnvifExtractor;
import mus.utility.HendlStallUtility;

public class IntrusionDetectorThread implements Runnable {

	private ImageTagger imageTagger;
	private static final int MAX_LABELS = 10;
	
	private boolean stopped = false;
	
	public void end(){
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
		
		while(!stopped){
			
			try {
				//capture camera image
				if(!new OnvifExtractor().extractPicture()){
					this.end();
					throw new IllegalStateException("Error taking picture from camera! Stopping now");
				}
				
				//tag image
				ImmutableSet<String> descriptions = tagImage();

				//detect fox
				IntrusionAlertLevel level = getThreadDetectionLevel(descriptions);
				
				if(level.getValue() >= IntrusionAlertLevel.valueOf(HendlStallUtility.getIntrusionLogLevel()).getValue()){
					//log to db
					System.out.println("damn - detected an intruder");
				}
				
				//wait
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

	private IntrusionAlertLevel getThreadDetectionLevel(ImmutableSet<String> descriptions) {
		boolean fox = descriptions.contains("fox");
		boolean marten = descriptions.contains("mustelidae");
		boolean carivore = descriptions.contains("carnivoran");
		
		if(fox || marten)
			return IntrusionAlertLevel.CRITICAL_SPECIFIC;
		else if(carivore)
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

	private String getImageFile() {
		String tmpDir = System.getProperty("java.io.tmpdir");
		String file = Paths.get(tmpDir, "onvif.jpg").toAbsolutePath().toString();
		return file;
	}

}
