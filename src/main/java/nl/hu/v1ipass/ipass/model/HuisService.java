package nl.hu.v1ipass.ipass.model;

import java.util.List;

import nl.hu.v1ipass.ipass.persistence.*;

public class HuisService {
	private AfdelingDAO afdelingDAO = new AfdelingDAO();
	private BewonerDAO bewonerDAO = new BewonerDAO();
	private TaakDAO taakDAO = new TaakDAO();
	private RuilverzoekDAO ruilverzoekDAO = new RuilverzoekDAO();
	private RuilOptieDAO ruilOptieDAO = new RuilOptieDAO();
	
	public List<Afdeling> getAllAfdelingen() {
		return afdelingDAO.findAll();
	}

	public List<Bewoner> getAllBewoners() {
		return bewonerDAO.findAll();
	}

	public List<Taak> getAllTaken() {
		return taakDAO.findAll();
	}

	public List<Ruilverzoek> getAllRuilverzoeken() {
		return ruilverzoekDAO.findAll();
	}
	
	public List<Ruilverzoek> getAllRuilverzoekenJoined() {
		return ruilverzoekDAO.findAllJoined();
	}
	
	public List<RuilOptie> getAllRuilOpties() {
		return ruilOptieDAO.findAll();
	}

	public void deleteRuilverzoek(int id) {
		Ruilverzoek rv = ruilverzoekDAO.findByCode(id);

		if (rv != null) {
			ruilverzoekDAO.delete(rv);
		} else
			throw new IllegalArgumentException("id does not exist!");
	}

	public void updateTaak(Taak tk) {
		taakDAO.update(tk);
	}

	public void addTaak(Taak tk) {
		taakDAO.save(tk);
	}
	public void tekenTaakAf(int id){
		taakDAO.tekenAf(id);
	}
	
	public Taak getMinsteTaken(int afdel){
		return taakDAO.minsteTaken(afdel);
	}
	
	public void taakRuilen(int verzId, int verzTaakId, int ontvId, int ontvTaakId){
		taakDAO.wijzigBewoner(ontvTaakId, verzId);
		taakDAO.wijzigBewoner(verzTaakId, ontvId);
		
	}
	
	public void addRuilverzoek(Ruilverzoek rv){
		ruilverzoekDAO.save(rv);
	}
}