package mus.HendlStallChecker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		//log level
		Properties p = new Properties(System.getProperties());
		p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
		p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "OFF"); // Off or any other level
		System.setProperties(p);
		
		
		HendlStallVerwalter admin = new HendlStallVerwalter();
		
		admin.startOpenDoorThread();
		admin.startIntrusionDetection();
		
		//wait for keypress
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    bufferRead.readLine();
	    
	    admin.stopAllThread();
	}
}
