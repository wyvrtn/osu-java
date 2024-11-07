package osuapi.app;

import lombok.Getter;
import lombok.Setter;
import osuapi.client.authorization.ClientCredentialsGrant;
import osuapi.client.core.OsuApiClient;

public class OsuApiApp {
	public static void main(String[] args) {
		ClientCredentialsGrant grant = new ClientCredentialsGrant(1, "secret");
		OsuApiClient client = new OsuApiClient(grant);
		System.out.println(client.endpoints);
		System.out.println(client);
		OsuApiApp app = new OsuApiApp();
		app.test();
	}

	public void test() {
		Foo foo = new Foo(){
			@Override
			public void constructor() {
				super.id = 2;
				super.sus = "skibidi";
			}
		};
		test1(foo);
	}

	public void test1(Foo foo) {
		foo.constructor();
		System.out.println(foo.getId());
		System.out.println(foo.getSus());
	}

	@Getter
	@Setter
	public static abstract class Foo {
		private int id = 1;
		private String sus = "sus";

		public abstract void constructor();
	}
}
