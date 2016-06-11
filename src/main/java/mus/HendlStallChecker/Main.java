package mus.HendlStallChecker;

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
	}
}
