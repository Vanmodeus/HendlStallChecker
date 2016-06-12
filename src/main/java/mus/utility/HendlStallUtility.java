package mus.utility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class HendlStallUtility {
	private static final String PROP_FILENAME = "config.properties";
	
	public static String getWebcamIP() throws Exception {
		Properties prop = new Properties();
		String propFileName = PROP_FILENAME;
		InputStream inputStream = new FileInputStream(propFileName);
		prop.load(inputStream);

		String ipcamAddress = prop.getProperty("ipcamAddress");
		return ipcamAddress;
	}
	
	public static String getDbIP() throws Exception {
		Properties prop = new Properties();
		String propFileName = PROP_FILENAME;
		InputStream inputStream = new FileInputStream(propFileName);
		prop.load(inputStream);

		String ipcamAddress = prop.getProperty("dbAddress");
		return ipcamAddress;
	}
	public static int getCloseDoorTimeoutSeconds() throws Exception {
		Properties prop = new Properties();
		String propFileName = PROP_FILENAME;
		InputStream inputStream = new FileInputStream(propFileName);
		prop.load(inputStream);

		String timeoutSecsString = prop.getProperty("closeDoorTimeoutSecs");
		int timeoutSecs = Integer.parseInt(timeoutSecsString);
		return timeoutSecs;
	}
	public static int getFoxPictureTimeoutSeconds() throws Exception {
		Properties prop = new Properties();
		String propFileName = PROP_FILENAME;
		InputStream inputStream = new FileInputStream(propFileName);
		prop.load(inputStream);

		String timeoutSecsString = prop.getProperty("foxPictureTimeoutSecs");
		int timeoutSecs = Integer.parseInt(timeoutSecsString);
		return timeoutSecs;
	}
	public static String getIntrusionLogLevel() throws Exception {
		Properties prop = new Properties();
		String propFileName = PROP_FILENAME;
		InputStream inputStream = new FileInputStream(propFileName);
		prop.load(inputStream);

		String logLevelString = prop.getProperty("intrusionLogLevel");
		return logLevelString;
	}
}
