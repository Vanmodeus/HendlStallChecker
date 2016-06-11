package mus.HendlStallChecker.Repository;

import java.util.Date;

public class Log {
	private long id;
	private long idChicken;
	private Date timestamp;

	public Log(long id, long idChicken, Date timestamp) {
		super();
		this.id = id;
		this.idChicken = idChicken;
		this.timestamp = timestamp;
	}

	public Log(long idChicken, Date timestamp) {
		super();
		this.idChicken = idChicken;
		this.timestamp = timestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdChicken() {
		return idChicken;
	}

	public void setIdChicken(long idChicken) {
		this.idChicken = idChicken;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
