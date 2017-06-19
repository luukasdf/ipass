package nl.hu.v1ipass.ipass.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.ipass.model.Ruilverzoek;

public class RuilverzoekDAO extends BaseDAO {
	private List<Ruilverzoek> SelectRuilverzoeken(String query) {
		List<Ruilverzoek> results = new ArrayList<Ruilverzoek>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int ruilverzoekid = dbResultSet.getInt("ruilverzoekid");
				String inhoud = dbResultSet.getString("inhoud");
				int verztaak = dbResultSet.getInt("verztaak");
				int ontvtaak = dbResultSet.getInt("ontvtaak");
				int verzbewoner = dbResultSet.getInt("verzbewoner");
				int ontvbewoner = dbResultSet.getInt("ontvbewoner");
				results.add(new Ruilverzoek(ruilverzoekid, inhoud, verzbewoner, ontvbewoner, verztaak, ontvtaak));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}
	
	private List<Ruilverzoek> SelectRuilverzoeken2(String query) {
		List<Ruilverzoek> results = new ArrayList<Ruilverzoek>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int ruilverzoekid = dbResultSet.getInt("ruilverzoekid");
				String inhoud = dbResultSet.getString("inhoud");
				int verztaak = dbResultSet.getInt("verztaak");
				int ontvtaak = dbResultSet.getInt("ontvtaak");
				int verzbewoner = dbResultSet.getInt("verzbewoner");
				int ontvbewoner = dbResultSet.getInt("ontvbewoner");
				
				String naam = dbResultSet.getString("naam");
				String taaknaam = dbResultSet.getString("taaknaam");
				String tijdstip = dbResultSet.getString("tijdstip");
				Date datum = dbResultSet.getDate("datum");
				String taakweg = dbResultSet.getString("taaknaam2");
				Date datum2 = dbResultSet.getDate("datum2");
				String tijdstip2 = dbResultSet.getString("tijdstip2");
				Ruilverzoek rv = new Ruilverzoek(ruilverzoekid, inhoud, verzbewoner, ontvbewoner, verztaak, ontvtaak);
				rv.setBewonerNaam(naam);
				rv.setTaakNaam(taaknaam);
				rv.setTijdstip(tijdstip);
				rv.setDatum(datum);
				rv.setTaakNaamWeg(taakweg);
				rv.setDatum2(datum2);
				rv.setTijdstip2(tijdstip2);
				results.add(rv);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Ruilverzoek> findAll() {
		return SelectRuilverzoeken("SELECT * FROM ruilverzoek");
	}

	public Ruilverzoek findByCode(int i) {
		return SelectRuilverzoeken("SELECT * FROM ruilverzoek WHERE ruilverzoekid = " + i + "").get(0);
	}
	
	public List<Ruilverzoek> findByVerztaak(int i ){
		return SelectRuilverzoeken("select * from ruilverzoek where verztaak = " + i + "");
	}
	
	public List<Ruilverzoek> findByOntvtaak(int i ){
		return SelectRuilverzoeken("select * from ruilverzoek where ontvtaak = " + i + "");
	}
	
	public Ruilverzoek hoogsteId() {
		return SelectRuilverzoeken("select * from ruilverzoek order by ruilverzoekid desc").get(0);
	}
	
	public List<Ruilverzoek> findAllJoined() {
		return SelectRuilverzoeken2("select r.*, b.naam, t.naam as taaknaam, t.tijdstip, t.datum , t2.naam as taaknaam2, t2.datum as datum2, t2.tijdstip as tijdstip2 from ruilverzoek r join bewoner b on (r.verzbewoner = b.persoonsnummer) join taak t on (r.verztaak = t.taakid) join taak t2 on(r.ontvtaak = t2.taakid)");
	}

	public boolean delete(Ruilverzoek ruilverzoek) {

		boolean result = false;
		boolean ruilverzoekExists = findByCode(ruilverzoek.getRuilID()) != null;
		if (ruilverzoekExists) {
			String query = "DELETE FROM ruilverzoek WHERE ruilverzoekid = '" + ruilverzoek.getRuilID() + "'";

			try (Connection con = getConnection()) {

				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					result = true;
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return result;
	}
	
	public boolean deleteByTaken(int verztaak, int ontvtaak){
		// dit zorgt ervoor dat alle ruilverzoeken met taken die iets te maken hebben 
		// met een ruilverzoek dat geaccepteerd wordt verwijderd worden,
		// zodat er geen foute ruilen plaats vinden.
		List<Ruilverzoek> taken1 = new ArrayList<Ruilverzoek>();
		List<Ruilverzoek> taken2 = new ArrayList<Ruilverzoek>();
		
		taken1 = findByVerztaak(verztaak);
		taken2 = findByOntvtaak(ontvtaak);
		
		for (Ruilverzoek rv : taken1){
			delete(rv);
		}
		
		for (Ruilverzoek rv : taken2){
			delete(rv);
		}
		
		return true;
	}

	public Ruilverzoek save(Ruilverzoek rv) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			int nieuwId = hoogsteId().getRuilID() +1;
			System.out.println(nieuwId);
			String query = "insert into ruilverzoek values ( " + nieuwId + ",'" + rv.getInhoud() + "',"
					+ rv.getVerzendTaakID() + "," + rv.getRetourTaakID() + "," + rv.getZenderID() + ","
					+ rv.getOntvangerID() + ")";

			stmt.executeQuery(query);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return rv;
	}

}
