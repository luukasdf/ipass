package nl.hu.v1ipass.ipass.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.ipass.model.Taak;


public class TaakDAO extends BaseDAO {
	private List<Taak> SelectTaken(String query) {
		List<Taak> results = new ArrayList<Taak>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int taakid = dbResultSet.getInt("taakid");
				String naam = dbResultSet.getString("naam");
				String tijdstip = dbResultSet.getString("tijdstip");
				String afgetekend = dbResultSet.getString("afgetekend");
				int afdelingid = dbResultSet.getInt("afdelingid");
				int bewonerid = dbResultSet.getInt("bewonerid");
				Date datum = dbResultSet.getDate("datum");
				int duur = dbResultSet.getInt("duur"); 
				results.add(new Taak(taakid, naam, tijdstip, datum, afgetekend, afdelingid, bewonerid, duur));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}
	
	private List<Taak> SelectTaken2(String query) {
		List<Taak> results = new ArrayList<Taak>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int taakid = dbResultSet.getInt("taakid");
				String naam = dbResultSet.getString("naam");
				String tijdstip = dbResultSet.getString("tijdstip");
				String afgetekend = dbResultSet.getString("afgetekend");
				int afdelingid = dbResultSet.getInt("afdelingid");
				int bewonerid = dbResultSet.getInt("bewonerid");
				Date datum = dbResultSet.getDate("datum");
				int duur = dbResultSet.getInt("duur");
				String bewonerNaam = dbResultSet.getString("bewonernaam");
				Taak newTaak = new Taak(taakid, naam, tijdstip, datum, afgetekend, afdelingid, bewonerid, duur);
				newTaak.setBewonerNaam(bewonerNaam);
				results.add(newTaak);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Taak> findAll() {
		return SelectTaken("SELECT * FROM taak");
	}
	
	//join tussen bewoner en taak
	public List<Taak> findAllinclBewNaam(){
		return SelectTaken2("select t.*, b.naam as bewonernaam from bewoner b join taak t on (b.persoonsnummer = t.bewonerid) order by bewonerid");
	}
	
	public Taak findByCode(int i) {
		return SelectTaken("SELECT * FROM taak WHERE taakid = " + i + "").get(0);
	}
	
	public Taak hoogsteID() {
		return SelectTaken("select * from taak order by taakid desc;").get(0);
	}
	//bewoner met de minste taken ophalen
	public Taak minsteTaken(int afdel){
	List<Taak> results = new ArrayList<Taak>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "select taak.bewonerid, sum(taak.duur) as aantalminuten from taak where afdelingid="+afdel+" group by taak.bewonerid order by aantalminuten";
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int bewonerid = dbResultSet.getInt("bewonerid");
				int duur = dbResultSet.getInt("aantalminuten"); 
				Date date = new Date(2000-01-01);
				results.add(new Taak(0, "geenTaak", "geenTaak", date , "geenTaak",0, bewonerid, duur));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results.get(0);
	}

	public Taak update(Taak tk) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "update taak set naam = '" + tk.getNaam() 
					+ "', tijdstip = '" + tk.getTijdstip() + "', bewonerid = " + tk.getBewonerId()
					+ ", afgetekend = '" +tk.getAfgetekend()+"'"+ " where taakid = " + tk.getTaakID();
			
			//System.out.println(tk.getTaakID());
			stmt.executeQuery(query);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return tk;
	}
	
	public Taak save(Taak tk) {
		int nieuwId = hoogsteID().getTaakID() + 1;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "insert into taak values ( " + nieuwId  + ",'" + tk.getNaam() + "','"
					 + tk.getTijdstip() + "'," + tk.getAfdelingId() + ","
					+ +tk.getBewonerId() +",'Nee','"+tk.getDatum()+ "'," + tk.getDuur()+")";
			stmt.executeQuery(query);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return tk;
	}
	
	public Taak wijzigBewoner(int taakid, int bewonerid){
		Taak tk = findByCode(taakid);
		tk.setBewonerID(bewonerid);
		update(tk);
		return tk;
	}
	
	public Taak tekenAf(int id){
		Taak tk = findByCode(id);
		tk.setAfgetekend("Ja");
		update(tk);
		return tk;
	}

}
