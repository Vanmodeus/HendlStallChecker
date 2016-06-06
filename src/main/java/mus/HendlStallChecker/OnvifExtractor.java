package mus.HendlStallChecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class OnvifExtractor {

	public void extractPicture() {
		try {
			String tmpDir = System.getProperty("java.io.tmpdir");
			String file = Paths.get(tmpDir, "onvif.jpg").toAbsolutePath().toString();
			String command = "avconv -i rtsp://10.0.0.69:554/onvif1 -r 1 " + file;

			// Prepend OS-Code for Command-Prompt / Shell / Console / etc., you know what i mean
			String os = System.getProperty("os.name");
			System.out.println("Detected OS: " + os);
			if (os.toLowerCase().contains("windows")) {
				command = "cmd /c " + command;
			}

			System.out.println("Start avconv");
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
			System.out.println("avconv finished (ExitValue: " + exitVal + ")");

			System.out.println("Check for File... (" + file + ")");
			if (!new File(file).exists()) {
				System.out.println("Error writing file (Exit Code: " + exitVal + ")");
				System.out.println(errorMsg.toString());
			} else {
				System.out.println("OK");
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
