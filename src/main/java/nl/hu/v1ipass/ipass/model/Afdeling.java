package nl.hu.v1ipass.ipass.model;

import java.util.ArrayList;

public class Afdeling {
	private int afdelingID;
	private String naam;
	private String locatie;
	private ArrayList<Taak> deTaken = new ArrayList<Taak>();
	private ArrayList<Bewoner> deBewoners = new ArrayList<Bewoner>();
	private ArrayList<Ruilverzoek> deRuilverzoeken = new ArrayList<Ruilverzoek>();
	
	public Afdeling(int id, String nm, String loc){
		afdelingID = id;
		naam = nm;
		locatie = loc;
	}
	
	public int GetAfdelingID(){
		return afdelingID;
	}
	
	public String getNaam(){
		return naam;
	}
	
	public String getLocatie(){
		return locatie;
	}
	
	public ArrayList<Taak> getTaken(){
		return deTaken;
	}
	
	public ArrayList<Bewoner> getBewoners(){
		return deBewoners;
	}
	
	public ArrayList<Ruilverzoek> getRuilverzoeken(){
		return deRuilverzoeken;
	}
}
