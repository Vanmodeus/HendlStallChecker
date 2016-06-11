package mus.HendlStallChecker;

public class Main {
	
	public static void main(String[] args) throws Exception {
		HendlStallVerwalter admin = new HendlStallVerwalter();
		
		admin.startOpenDoorThread();
	}
}
