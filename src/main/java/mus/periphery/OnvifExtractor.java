package mus.periphery;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import mus.utility.HendlStallUtility;

public class OnvifExtractor {

	public static final String IMAGE_NAME = "onvif.jpg";
	public boolean extractPicture() {
		try {
			String tmpDir = HendlStallUtility.getSystemTempDir();
			String file = Paths.get(tmpDir, IMAGE_NAME).toAbsolutePath().toString();
			String command = "avconv -i rtsp://" + HendlStallUtility.getWebcamIP() + ":554/onvif1 -r 1 " + file;

			// Prepend OS-Code for Command-Prompt / Shell / Console / etc., you know what i mean
			String os = System.getProperty("os.name");
//			System.out.println("Detected OS: " + os);
			if (os.toLowerCase().contains("windows")) {
				command = "cmd /c " + command;
			}

//			System.out.println("Start avconv");
			Process pr = Runtime.getRuntime().exec(command);

			// Fancy falls notwendig, aber egal weil immer der Fehler bei der scheiß HiKam kumt -> Also FileCheck
			StringBuilder errorMsg = new StringBuilder();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				errorMsg.append(line);
			}
			reader.close();

			int exitVal = pr.waitFor();
//			System.out.println("avconv finished (ExitValue: " + exitVal + ")");

//			System.out.println("Check for File... (" + file + ")");
			if (!new File(file).exists()) {
				System.err.println("Error writing file (Exit Code: " + exitVal + ")");
				System.err.println(errorMsg.toString());
				throw new IllegalStateException("Camera image could not be written!");
			} else {
//				System.out.println("OK");
				return true;
			}

		} catch (Exception e) {
			System.err.println(e);
		}
		
		return false;
	}
}
