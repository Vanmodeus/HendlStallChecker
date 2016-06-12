package mus.HendlStallChecker.Repository;

import java.util.Date;

public class Intrusion {
	private long id;
	private IntrusionAlertLevel level;
	private Date timestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IntrusionAlertLevel getLevel() {
		return level;
	}

	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setLevel(IntrusionAlertLevel level) {
		this.level = level;
	}

	public Intrusion(long id, int level, Date timestamp) {
		super();
		this.id = id;
		this.level = IntrusionAlertLevel.values()[level-1];
		this.timestamp = timestamp;
	}
	
	public Intrusion(int level, Date timestamp) {
		super();
		this.level = IntrusionAlertLevel.values()[level];
		this.timestamp = timestamp;
	}
}
