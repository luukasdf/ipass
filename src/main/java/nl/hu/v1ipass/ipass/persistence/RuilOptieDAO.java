package nl.hu.v1ipass.ipass.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.ipass.model.RuilOptie;


public class RuilOptieDAO extends BaseDAO {
	private List<RuilOptie> SelectRuilOpties(String query) {
		List<RuilOptie> results = new ArrayList<RuilOptie>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int taakid = dbResultSet.getInt("taakid");
				int bewonerid = dbResultSet.getInt("bewonerid");
				int afdelingid = dbResultSet.getInt("afdelingid");
				Date datum = dbResultSet.getDate("datum");
				String naam = dbResultSet.getString("naam");
				String taaknaam = dbResultSet.getString("taaknaam");
				String tijdstip = dbResultSet.getString("tijdstip");
				results.add(new RuilOptie(taakid, bewonerid, afdelingid, datum, naam, taaknaam, tijdstip));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<RuilOptie> findAll() {
		return SelectRuilOpties(
				"select taakid, bewonerid, b.afdelingid, t.datum, b.naam, t.tijdstip, t.naam as taaknaam from taak t join bewoner b on (t.bewonerid = b.persoonsnummer) where t.afgetekend = 'Nee';");
	}

}