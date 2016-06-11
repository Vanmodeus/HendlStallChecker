package mus.HendlStallChecker.Repository;

public class Chicken {
	private long id;
	private String nfcid;
	private String name;
	private boolean inside;

	public Chicken(long id, String nfcid, String name, boolean inside) {
		super();
		this.id = id;
		this.nfcid = nfcid;
		this.name = name;
		this.inside = inside;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNfcid() {
		return nfcid;
	}

	public void setNfcid(String nfcid) {
		this.nfcid = nfcid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInside() {
		return inside;
	}

	public void setInside(boolean inside) {
		this.inside = inside;
	}

}
