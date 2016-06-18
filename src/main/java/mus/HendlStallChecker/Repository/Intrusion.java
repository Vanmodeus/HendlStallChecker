package mus.HendlStallChecker.Repository;

import java.util.Date;

public class Intrusion {
	private long id;
	private IntrusionAlertLevel level;
	private Date timestamp;
	private byte[] image;

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

	public Intrusion(long id, int level, Date timestamp, byte[] image) {
		super();
		this.id = id;
		this.level = IntrusionAlertLevel.values()[level - 1];
		this.timestamp = timestamp;
		this.image = image;
	}

	public Intrusion(int level, Date timestamp, byte[] image) {
		super();
		this.level = IntrusionAlertLevel.values()[level];
		this.timestamp = timestamp;
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
