package mus.HendlStallChecker.intrusion.detection;

public enum IntrusionAlertLevel {
	WARNING_UNDEFINED(1), SEVERE_CARNIVORE(2), CRITICAL_SPECIFIC(3);
	
	private final int value;
    private IntrusionAlertLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
