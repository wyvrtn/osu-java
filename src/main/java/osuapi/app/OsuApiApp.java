package osuapi.app;

import osuapi.client.OsuApiClient;
import osuapi.client.authorization.ClientCredentialsGrant;

public class OsuApiApp {
	public static void main(String[] args) {
		ClientCredentialsGrant grant = new ClientCredentialsGrant(1, "secret");
		OsuApiClient client = new OsuApiClient(grant);
		System.out.println(client);
	}
}
