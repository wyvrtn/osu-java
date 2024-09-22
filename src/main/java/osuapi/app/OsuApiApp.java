package osuapi.app;

import osuapi.client.ClientCredentialsGrant;
import osuapi.client.OsuApiClient;

public class OsuApiApp {
	public static void main(String[] args) {
		ClientCredentialsGrant grant = new ClientCredentialsGrant(1, "secret");
		OsuApiClient client = new OsuApiClient(grant);
		System.out.println(client);
	}
	
}
