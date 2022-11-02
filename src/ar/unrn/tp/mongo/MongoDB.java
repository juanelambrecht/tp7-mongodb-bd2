package ar.unrn.tp.mongo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.MongoClient;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import com.mongodb.client.MongoCursor;

public class MongoDB {

	private MongoClient mongoClient;
	private MongoDatabase db;

	public MongoDatabase connect(String host, int port, String database) {
		// Getting a connection
		this.mongoClient = new MongoClient(host, port);

		// Select the database
		this.db = mongoClient.getDatabase(database);

		return db;

	}

	public void disconnect() {
		this.mongoClient.close();
	}

	public String find(String collection, String key, String value) {
		try {
			// Select the collection
			MongoCollection<Document> col = db.getCollection(collection);

			// Create the document to specify find criteria
			Document findDocument = new Document(key, value);

			// Document to store query results
			FindIterable<Document> resultDocument = col.find(findDocument);

			// Return the name of the first returned document
			return resultDocument.first().toJson();
		} finally {
			this.disconnect();
		}

	}

	public void createIndex() {
		MongoCollection<Document> collections = db.getCollection("posts");
		collections.createIndex(Indexes.text("text"));
	}

	public void insert() {
		try {
			// Retrieving a collection
			MongoCollection<Document> col = db.getCollection("pages");
			System.out.println("Collection sampleCollection selected successfully");
			Document document = new Document("_id", new ObjectId())
					.append("title", "Sobre las Infusiones, legales... ;)")
					.append("text", "Una infusión es una bebida...").append("author", "Yo mismo")
					.append("date", new Date());

			// Inserting document into the collection
			col.insertOne(document);
			System.out.println("Document inserted successfully");
		} finally {
			this.disconnect();
		}

	}

	public void insertPosts() {
		// Retrieving a collection
		try {
			MongoCollection<Document> col = db.getCollection("posts");
			System.out.println("Collection sampleCollection selected successfully");
			Document document = new Document("_id", new ObjectId("634f028c1dff6174c7249b95")).append("title", "Café")
					.append("resume", "Sobre el Café solo...").append("text", "El texto completo del posts...")
					.append("tags", Arrays.asList("cafe", "infusion"))
					.append("relatedlinks", Arrays.asList("http://cafenegro.com", "http://cafecito.com"))
					.append("author", "Jorge Boles").append("date", new Date());

			// Inserting document into the collection
			col.insertOne(document);
			System.out.println("Document inserted successfully");

			Document document2 = new Document("_id", new ObjectId("634f040ad6d9c42c96c211dd")).append("title", "Té")
					.append("resume", "Sobre el Té solo...").append("text", "El texto completo del posts...")
					.append("tags", Arrays.asList("te", "infusion"))
					.append("relatedlinks", Arrays.asList("http://te.com", "http://teconleche.com"))
					.append("author", "Julio Mark").append("date", new Date());

			// Inserting document into the collection
			col.insertOne(document2);
			System.out.println("Document inserted successfully");

			Document document3 = new Document().append("title", "Te verde")
					.append("resume", "Sobre el te verde solo...").append("text", "El texto completo del posts...")
					.append("tags", Arrays.asList("te", "infusion"))
					.append("relatedlinks", Arrays.asList("http://teverde.com", "http://teverde.com"))
					.append("author", "Jorge Boles").append("date", new Date());

			// Inserting document into the collection
			col.insertOne(document);
			System.out.println("Document inserted successfully");

			// Inserting document into the collection
			col.insertOne(document3);
			System.out.println("Document inserted successfully");

		} finally {
			this.disconnect();
		}

	}

	public String findAllPages(String id) {

		MongoCollection<Document> collections = db.getCollection("pages");

		FindIterable<Document> d = collections.find();

		return StreamSupport.stream(d.spliterator(), false).map(Document::toJson)
				.collect(Collectors.joining(", ", "[", "]"));
	}

	public String findPostLatest() {
		
		MongoCollection<Document> collections = db.getCollection("posts");

		FindIterable<Document> d = collections.find().sort(Sorts.descending("_id")).limit(4);

		return StreamSupport.stream(d.spliterator(), false).map(Document::toJson)
				.collect(Collectors.joining(", ", "[", "]"));
	}

	public String findPostID(String id) {

		MongoCollection<Document> collections = db.getCollection("posts");

		FindIterable<Document> d = collections.find(Filters.eq("_id", new ObjectId(id)));

		return StreamSupport.stream(d.spliterator(), false).map(Document::toJson)
				.collect(Collectors.joining(", ", "[", "]"));
	}

	public String findByAuthor() {
		
		MongoCollection<Document> collections = db.getCollection("posts");

		AggregateIterable<Document> d = collections
				.aggregate(Arrays.asList(Aggregates.group("$author", Accumulators.sum("count", 1))));

		return StreamSupport.stream(d.spliterator(), false).map(Document::toJson)
				.collect(Collectors.joining(", ", "[", "]"));
	}

	public String findAuthorPosts(String nombre) {

		MongoCollection<Document> collections = db.getCollection("posts");
		Document findDocument = new Document("author", nombre);
		FindIterable<Document> d = collections.find(findDocument);

		return StreamSupport.stream(d.spliterator(), false).map(Document::toJson)
				.collect(Collectors.joining(", ", "[", "]"));
	}

	public String findSearch(String text) {

		MongoCollection<Document> collections = db.getCollection("posts");

		FindIterable<Document> d = collections.find(Filters.text(text));

		return StreamSupport.stream(d.spliterator(), false).map(Document::toJson)
				.collect(Collectors.joining(", ", "[", "]"));
	}

}
