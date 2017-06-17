package nl.hu.v1ipass.ipass.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.ipass.model.Afdeling;

public class AfdelingDAO extends BaseDAO {
	private List<Afdeling> selectAfdelingen(String query) {
		List<Afdeling> results = new ArrayList<Afdeling>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int afdelingId = dbResultSet.getInt("afdelingid");
				String naam = dbResultSet.getString("naam");
				String locatie = dbResultSet.getString("locatie");

				results.add(new Afdeling(afdelingId, naam, locatie));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Afdeling> findAll() {
		return selectAfdelingen("SELECT * FROM afdeling");
	}

}
