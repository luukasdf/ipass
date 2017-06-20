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
	//voor de Join functie
	private List<Bewoner> selectBewoners2(String query) {
		List<Bewoner> results = new ArrayList<Bewoner>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int persoonsnummer = dbResultSet.getInt("persoonsnummer");
				String naam = dbResultSet.getString("naam");
				String woonadres = dbResultSet.getString("woonadres");
				int afdelingid = dbResultSet.getInt("afdelingid");
				String duurNietAf =  dbResultSet.getString("duur");
				Bewoner newBewoner = new Bewoner(persoonsnummer, naam, woonadres, afdelingid);
				newBewoner.setDuurNietAf(duurNietAf);
				results.add(newBewoner);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}
	


	public List<Bewoner> findAll() {
		return selectBewoners("SELECT * FROM bewoner");
	}
	//alle "stoute jongens" ophalen, bewoners die een taak in het verleden niet hebben afgetekend
	public List<Bewoner> findAllNietAfgetekend(){
		return selectBewoners2("select b.*, sum(t.duur) as duur from bewoner b join taak t on (b.persoonsnummer = t.bewonerid) where afgetekend = 'Nee' and datum < current_date group by persoonsnummer order by duur desc");
	}
	//alle bewoners ophalen met hun werkelijk afgetekende duur
	public List<Bewoner> findAllDuur(){
		return selectBewoners2("select b.*, sum(t.duur) as duur from bewoner b join taak t on (b.persoonsnummer = t.bewonerid) where  afgetekend = 'Ja' and datum < current_date group by persoonsnummer order by duur desc");
	}


}
