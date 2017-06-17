package nl.hu.v1ipass.ipass.model;

import java.sql.Date;

public class Taak {
	private int taakID;
	private String naam;
	private String tijdstip;
	private Date datum;
	private String afgetekend;
	private int afdelingId;
	private int bewonerId;
	private int duur;

	public Taak(int id, String nm, String tid, Date dat, String aft, int afd, int bew, int dr) {
		taakID = id;
		naam = nm;
		datum = dat;
		tijdstip = tid;
		afgetekend = aft;
		afdelingId = afd;
		bewonerId = bew;
		duur = dr;
	}
	
	public int getTaakID() {
		return taakID;
	}

	public String getNaam() {
		return naam;
	}

	public String getTijdstip() {
		return tijdstip;
	}
	
	public String getAfgetekend(){
		return afgetekend;
	}

	public int getAfdelingId() {
		return afdelingId;
	}

	public int getBewonerId() {
		return bewonerId;
	}
	public void setAfgetekend(String afg){
		afgetekend = afg;
	}
	
	public Date getDatum(){
		return datum;
	}
	
	public void setBewonerID(int bewID){
		bewonerId = bewID;
	}
	
	public int getDuur(){
		return duur;
	}
	
	public void setDuur(int dr){
		duur = dr;
	}

}
