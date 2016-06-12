package mus.HendlStallChecker.Repository;

public enum IntrusionAlertLevel {
	WARNING_UNDEFINED(0), SEVERE_CARNIVORE(1), CRITICAL_SPECIFIC(2);
	
	private final int value;
    private IntrusionAlertLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
