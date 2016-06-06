package mus.utility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class HendlStallUtility {
	public static String getWebcamIP() throws Exception {
		Properties prop = new Properties();
		String propFileName = "config.properties";
		InputStream inputStream = new FileInputStream(propFileName);
		prop.load(inputStream);

		String ipcamAddress = prop.getProperty("ipcamAddress");
		return ipcamAddress;
	}
}
