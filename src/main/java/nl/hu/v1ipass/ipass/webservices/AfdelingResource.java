package nl.hu.v1ipass.ipass.webservices;

import java.sql.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.ipass.model.Afdeling;
import nl.hu.v1ipass.ipass.model.Bewoner;
import nl.hu.v1ipass.ipass.model.HuisService;
import nl.hu.v1ipass.ipass.model.RuilOptie;
import nl.hu.v1ipass.ipass.model.Ruilverzoek;
import nl.hu.v1ipass.ipass.model.ServiceProvider;
import nl.hu.v1ipass.ipass.model.Taak;

@Path("/huis")
public class AfdelingResource {

	@GET
	@Path("/afdelingen")
	@Produces("application/json")
	public String getAfdelingen() {
		HuisService service = ServiceProvider.getHuisService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Afdeling a : service.getAllAfdelingen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", a.GetAfdelingID());
			job.add("naam", a.getNaam());
			job.add("locatie", a.getLocatie());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/bewoners")
	@Produces("application/json")
	public String getBewoners() {
		HuisService service = ServiceProvider.getHuisService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Bewoner b : service.getAllBewoners()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("persoonsnummer", b.getPersoonsNummer());
			job.add("naam", b.getNaam());
			job.add("woonadres", b.getWoonadres());
			job.add("afdelingID", b.getAfdelingID());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/bewonersnietaf")
	@Produces("application/json")
	public String getBewoners2() {
		HuisService service = ServiceProvider.getHuisService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Bewoner b : service.getNietAfBewonersDuur()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("persoonsnummer", b.getPersoonsNummer());
			job.add("naam", b.getNaam());
			job.add("woonadres", b.getWoonadres());
			job.add("afdelingID", b.getAfdelingID());
			job.add("duur", b.getDuurNietAf());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/bewonersduur")
	@Produces("application/json")
	public String getBewoners3() {
		HuisService service = ServiceProvider.getHuisService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Bewoner b : service.getallBewonersDuur()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("persoonsnummer", b.getPersoonsNummer());
			job.add("naam", b.getNaam());
			job.add("woonadres", b.getWoonadres());
			job.add("afdelingID", b.getAfdelingID());
			job.add("duur", b.getDuurNietAf());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/taken")
	@Produces("application/json")
	public String getTaken() {
		HuisService service = ServiceProvider.getHuisService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Taak t : service.getAllTaken()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("taakid", t.getTaakID());
			job.add("naam", t.getNaam());
			job.add("tijdstip", t.getTijdstip());
			job.add("datum", t.getDatum().toString());
			job.add("afgetekend", t.getAfgetekend());
			job.add("afdelingID", t.getAfdelingId());
			job.add("bewonerID", t.getBewonerId());
			job.add("duur", t.getDuur());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/takenjoin")
	@Produces("application/json")
	public String getTakenjoin() {
		HuisService service = ServiceProvider.getHuisService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Taak t : service.getAllTakenMetBew()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("taakid", t.getTaakID());
			job.add("naam", t.getNaam());
			job.add("tijdstip", t.getTijdstip());
			job.add("datum", t.getDatum().toString());
			job.add("afgetekend", t.getAfgetekend());
			job.add("afdelingID", t.getAfdelingId());
			job.add("bewonerID", t.getBewonerId());
			job.add("duur", t.getDuur());
			job.add("bewonernaam", t.getBewonerNaam());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/ruilverzoeken")
	@Produces("application/json")
	public String getRuilverzoeken() {
		HuisService service = ServiceProvider.getHuisService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Ruilverzoek rv : service.getAllRuilverzoeken()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("ruilverzoekId", rv.getRuilID());
			job.add("inhoud", rv.getInhoud());
			job.add("verzendId", rv.getZenderID());
			job.add("ontvangerId", rv.getOntvangerID());
			job.add("verzendTaakId", rv.getVerzendTaakID());
			job.add("ontvangTaakId", rv.getRetourTaakID());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/ruilverzoekenjoined")
	@Produces("application/json")
	public String getRuilverzoekenJoined() {
		HuisService service = ServiceProvider.getHuisService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Ruilverzoek rv : service.getAllRuilverzoekenJoined()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("ruilverzoekId", rv.getRuilID());
			job.add("inhoud", rv.getInhoud());
			job.add("verzendId", rv.getZenderID());
			job.add("ontvangerId", rv.getOntvangerID());
			job.add("verzendTaakId", rv.getVerzendTaakID());
			job.add("ontvangTaakId", rv.getRetourTaakID());
			job.add("naam", rv.getBewonerNaam());
			job.add("taaknaam", rv.getTaakNaam());
			job.add("tijdstip", rv.getTijdstip());
			job.add("datum", rv.getDatum().toString());
			job.add("taaknaam2", rv.getTaakNaamWeg());
			job.add("datum2", rv.getDatum2().toString());
			job.add("tijdstip2", rv.getTijdstip2());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/ruilopties")
	@Produces("application/json")
	public String getRuilOpties() {
		HuisService service = ServiceProvider.getHuisService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (RuilOptie ro : service.getAllRuilOpties()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("taakid", ro.getTaakID());
			job.add("bewonerid", ro.getBewonerID());
			job.add("afdelingid", ro.getAfdelingID());
			job.add("datum", ro.getDatum().toString());
			job.add("naam", ro.getBewonerNaam());
			job.add("taaknaam", ro.getTaakNaam());
			job.add("tijdstip", ro.getTijdstip());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@DELETE
	@Path("/ruilverzoeken/{id}")
	public String deleteCountry(@PathParam("id") int id) {
		HuisService service = ServiceProvider.getHuisService();
		Ruilverzoek found = null;
		for (Ruilverzoek rv : service.getAllRuilverzoeken()) {
			if (rv.getRuilID() == id) {
				found = rv;
				break;
			}
		}
		service.deleteRuilverzoek(found.getRuilID());
		return "succes";
	}

	@PUT
	@Path("/takenaftekenen/{id}")
	public String tekenTaakAf(@PathParam("id") int id){
		HuisService service = ServiceProvider.getHuisService();
		service.tekenTaakAf(id);
		return "succes";
	}
	
	@GET
	@Path("/minstetaken/{id}")
	public String minsteTaken(@PathParam("id") int id){
		HuisService service = ServiceProvider.getHuisService();
		Taak t = service.getMinsteTaken(id);
		//JsonArrayBuilder jab = Json.createArrayBuilder();
		
		JsonObjectBuilder job = Json.createObjectBuilder();	
		job.add("bewonerid", t.getBewonerId());
		job.add("duur", t.getDuur());
		//jab.add(job);		
		
		//JsonArray array = jab.build();
		return job.build().toString();
	}
	
	@POST
	@Path("/ruilverzoeken/{inhoud}/{verztaak}/{ontvtaak}/{verzbewoner}/{ontvbewoner}")
	public String addRuilverzoek(
			@PathParam("inhoud") String inhoud,
			@PathParam("verztaak") int verztaak,
			@PathParam("ontvtaak") int ontvtaak,
			@PathParam("verzbewoner") int verzbewoner,
			@PathParam("ontvbewoner") int ontvbewoner){
		HuisService service = ServiceProvider.getHuisService();		
		Ruilverzoek rv = new Ruilverzoek(0, inhoud, verzbewoner, ontvbewoner, verztaak, ontvtaak);
		service.addRuilverzoek(rv);
		return "succes";
		
		
		//ruilverzoeken/{id}/{inhoud}/{verztaak}/{ontvtaak}/{verzbewoner/{ontvbewoner}
	}
	
	@PUT
	@Path("/takenruilen/{ruilverzoekid}/{verzId}/{verzTaakId}/{ontvId}/{ontvTaakId}")
	public String ruilTaakOm(
			@PathParam("ruilverzoekid") int ruilverzoekId,
			@PathParam("verzId") int verzId,
			@PathParam("verzTaakId") int verzTaakId,
			@PathParam("ontvId") int ontvId,
			@PathParam("ontvTaakId") int ontvTaakId){
	HuisService service = ServiceProvider.getHuisService();
	service.taakRuilen(verzId, verzTaakId, ontvId, ontvTaakId);
	service.deleteRuilverzoek(ruilverzoekId);
	service.deleteRuilverzoekByTaak(verzTaakId, ontvTaakId);
	return "succes";
	}
	
	@POST
	@Path("/taken/{afdelingId}")
	public String addTaak(
			@PathParam("afdelingId") int afdelingId,
			@FormParam("userId") int bewonerId,
			@FormParam("naam") String naam,
			@FormParam("datum") Date datum,
			@FormParam("tijdstip") String tijdstip,
			@FormParam("duur") int duur){
	Taak newTaak = new Taak(0,naam, tijdstip, datum, "Nee", afdelingId, bewonerId, duur);
	HuisService service = ServiceProvider.getHuisService();
	service.addTaak(newTaak);
	return "succes";
	}
}
