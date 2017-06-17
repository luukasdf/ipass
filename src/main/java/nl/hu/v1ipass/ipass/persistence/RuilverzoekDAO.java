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
				Ruilverzoek rv = new Ruilverzoek(ruilverzoekid, inhoud, verzbewoner, ontvbewoner, verztaak, ontvtaak);
				rv.setBewonerNaam(naam);
				rv.setTaakNaam(taaknaam);
				rv.setTijdstip(tijdstip);
				rv.setDatum(datum);
				rv.setTaakNaamWeg(taakweg);
								
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
	
	public List<Ruilverzoek> findAllJoined() {
		return SelectRuilverzoeken2("select r.*, b.naam, t.naam as taaknaam, t.tijdstip, t.datum , t2.naam as taaknaam2 from ruilverzoek r join bewoner b on (r.verzbewoner = b.persoonsnummer) join taak t on (r.verztaak = t.taakid) join taak t2 on(r.ontvtaak = t2.taakid)");
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

	public Ruilverzoek save(Ruilverzoek rv) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();

			String query = "insert into ruilverzoek values ( " + rv.getRuilID() + ",'" + rv.getInhoud() + "',"
					+ rv.getVerzendTaakID() + "," + rv.getRetourTaakID() + "," + rv.getZenderID() + ","
					+ rv.getOntvangerID() + ")";

			stmt.executeQuery(query);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return rv;
	}

}
