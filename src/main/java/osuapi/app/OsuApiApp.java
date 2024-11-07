package osuapi.app;

import osuapi.client.authorization.ClientCredentialsGrant;
import osuapi.client.core.OsuApiClient;
import osuapi.models.structs.MatchEventParameters;

public class OsuApiApp {
	public static void main(String[] args) {
		ClientCredentialsGrant grant = new ClientCredentialsGrant(1, "secret");
		OsuApiClient client = new OsuApiClient(grant);
		System.out.println(client.endpoints);
		System.out.println(client);
		System.out.println((new MatchEventParameters() {
			@Override
			public Class<? extends MatchEventParameters> constructor() {
				System.out.println("called");
				return this.getClass();
			}
		}));
	}
}
