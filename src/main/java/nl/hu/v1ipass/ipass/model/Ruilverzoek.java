package nl.hu.v1ipass.ipass.model;

import java.sql.Date;

public class Ruilverzoek {
	private int ruilID;
	private String inhoud;
	private int zender;
	private int ontvanger;
	private int verzendTaak;
	private int retourTaak;
	
	private String naam;
	private String taaknaam;
	private String tijdstip;
	private Date datum;
	private String taaknaamWeg;
	
	private Date datum2;
	private String tijdstip2;
	
	
	public Ruilverzoek(int id, String inh, int zend, int ontv, int verzTK, int retrTK) {
		ruilID = id;
		inhoud = inh;
		zender = zend;
		ontvanger = ontv;
		verzendTaak = verzTK;
		retourTaak = retrTK;
	}

	public int getRuilID() {
		return ruilID;
	}

	public String getInhoud() {
		return inhoud;
	}

	public int getZenderID() {
		return zender;
	}

	public int getOntvangerID() {
		return ontvanger;
	}

	public int getVerzendTaakID() {
		return verzendTaak;
	}

	public int getRetourTaakID() {
		return retourTaak;
	}
	
	public String getBewonerNaam(){
		return naam;
	}
	
	public void setBewonerNaam(String nm){
		naam = nm;
	}
	
	public String getTaakNaam(){
		return taaknaam;
	}
	
	public void setTaakNaam(String tn){
		taaknaam = tn;
	}
	
	public String getTijdstip(){
		return tijdstip;
	}
	
	public void setTijdstip(String tstp){
		tijdstip = tstp;
	}
	
	public Date getDatum(){
		return datum;
	}
	
	public void setDatum(Date dat){
		datum = dat;
	}
	
	public String getTaakNaamWeg(){
		return taaknaamWeg;
	}
	
	public void setTaakNaamWeg(String tnw){
		taaknaamWeg = tnw;
	}
	
	public Date getDatum2(){
		return datum2;
	}
	
	public void setDatum2(Date d){
		datum2 = d;
	}
	
	public String getTijdstip2(){
		return tijdstip2;
	}
	
	public void setTijdstip2(String tijd2){
		tijdstip2 = tijd2;
	}
}
