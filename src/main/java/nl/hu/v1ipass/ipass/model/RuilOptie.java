package nl.hu.v1ipass.ipass.model;

import java.sql.Date;
//klasse voor mogelijke ruilverzoeken
public class RuilOptie {
	private int taakid;
	private int bewonerid;
	private int afdelingid;
	private Date datum;
	private String naam;
	private String taaknaam;
	private String tijdstip;

	public RuilOptie(int td, int bd, int ad, Date dat, String nm, String tnm, String tdsp) {
		taakid = td;
		bewonerid = bd;
		afdelingid = ad;
		datum = dat;
		naam = nm;
		taaknaam = tnm;
		tijdstip = tdsp;
	}

	public int getTaakID() {
		return taakid;
	}

	public int getBewonerID() {
		return bewonerid;
	}

	public int getAfdelingID() {
		return afdelingid;
	}

	public Date getDatum() {
		return datum;
	}

	public String getBewonerNaam() {
		return naam;
	}

	public String getTaakNaam() {
		return taaknaam;
	}

	public String getTijdstip() {
		return tijdstip;
	}
}
