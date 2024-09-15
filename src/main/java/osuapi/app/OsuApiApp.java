package osuapi.app;

import osuapi.client.OsuApiClient;

public class OsuApiApp {
	public static void main(String[] args) {
		OsuApiClient client = new OsuApiClient(1, "secret");
		System.out.println(client);
	}
	
}
