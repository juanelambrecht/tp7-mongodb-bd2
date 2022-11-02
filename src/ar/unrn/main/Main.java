package ar.unrn.main;

import com.mongodb.client.MongoDatabase;

import ar.unrn.tp.mongo.MongoDB;
import ar.unrn.tp.web.WebAPI;

public class Main {

	public static void main(String[] args) {
		MongoDB mongo = new MongoDB();
		mongo.connect("localhost", 27017, "blog");
		// mongo.insertPosts();
		// mongo.createIndex();

		WebAPI servicio = new WebAPI(1234, mongo);
		servicio.start();

	}

}
