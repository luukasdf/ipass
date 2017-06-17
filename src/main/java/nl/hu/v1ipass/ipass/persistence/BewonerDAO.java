package nl.hu.v1ipass.ipass.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.ipass.model.Bewoner;

public class BewonerDAO extends BaseDAO {
	private List<Bewoner> selectBewoners(String query) {
		List<Bewoner> results = new ArrayList<Bewoner>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int persoonsnummer = dbResultSet.getInt("persoonsnummer");
				String naam = dbResultSet.getString("naam");
				String woonadres = dbResultSet.getString("woonadres");
				int afdelingid = dbResultSet.getInt("afdelingid");

				results.add(new Bewoner(persoonsnummer, naam, woonadres, afdelingid));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Bewoner> findAll() {
		return selectBewoners("SELECT * FROM bewoner");
	}

}