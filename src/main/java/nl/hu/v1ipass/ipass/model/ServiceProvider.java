package nl.hu.v1ipass.ipass.model;

public class ServiceProvider {
	private static HuisService huisService = new HuisService();

	public static HuisService getHuisService() {
		return huisService;
	}
}