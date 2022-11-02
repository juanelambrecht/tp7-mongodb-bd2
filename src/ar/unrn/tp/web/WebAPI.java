package ar.unrn.tp.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import com.google.gson.Gson;

import ar.unrn.tp.mongo.MongoDB;

public class WebAPI {

	private int puerto;
	private MongoDB mongo;

	public WebAPI(int puerto, MongoDB mongo) {
		this.puerto = puerto;
		this.mongo = mongo;
	}

	public void start() {
		Javalin javalin = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start(this.puerto);

		javalin.get("/pages/{id}", pages());
		javalin.get("/posts/latest", latest());
		javalin.get("/posts/{id}", posts());
		javalin.get("/byauthor", byauthor());
		javalin.get("/posts/author/{name}", author());
		javalin.get("/search/{text}", search());

		javalin.exception(Exception.class, (e, ctx) -> {
			ctx.json(Map.of("result", "error", "message", "algo salio mal.: " + e.getMessage())).status(400);
		});
	}

	private Handler pages() {
		return ctx -> {
			var id = String.valueOf(ctx.pathParam("id"));
			var pages = this.mongo.findAllPages(id);

			ctx.json(pages);
		};
	}

	private Handler latest() {
		return ctx -> {
			var latest = this.mongo.findPostLatest();

			ctx.json(latest);
		};
	}

	private Handler posts() {
		return ctx -> {
			var id = String.valueOf(ctx.pathParam("id"));
			var posts = this.mongo.findPostID(id);
			ctx.json(posts);
		};
	}

	private Handler byauthor() {
		return ctx -> {
			var byauthor = this.mongo.findByAuthor();

			ctx.json(byauthor);
		};
	}

	private Handler author() {
		return ctx -> {
			var nombre = String.valueOf(ctx.pathParam("name"));
			var postAuthor = this.mongo.findAuthorPosts(nombre);

			ctx.json(postAuthor);
		};
	}

	private Handler search() {
		return ctx -> {
			var text = String.valueOf(ctx.pathParam("text"));
			var resultSearch = this.mongo.findSearch(text);

			ctx.json(resultSearch);
		};
	}

}
