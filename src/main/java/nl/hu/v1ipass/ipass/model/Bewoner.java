package nl.hu.v1ipass.ipass.model;

public class Bewoner {
	private int persoonsNummer;
	private String naam;
	private String woonadres;
	private int afdelingID;
	private String duurNietAf;

	public Bewoner(int pN, String nm, String adr, int afdid) {
		persoonsNummer = pN;
		naam = nm;
		woonadres = adr;
		afdelingID = afdid;
	}

	public int getPersoonsNummer() {
		return persoonsNummer;
	}

	public String getNaam() {
		return naam;
	}

	public String getWoonadres() {
		return woonadres;
	}

	public int getAfdelingID() {
		return afdelingID;
	}
	
	public String getDuurNietAf(){
		return duurNietAf;
	}
	
	public void setDuurNietAf(String DNA){
		duurNietAf = DNA;
	}
}
