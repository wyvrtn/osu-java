package jospi.endpoints;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import jospi.client.core.OsuApiClient;
import jospi.client.request.HttpMethod;
import jospi.client.resources.OsuApiException;
import jospi.enums.comments.CommentSortType;
import jospi.enums.comments.CommentableType;
import jospi.iterator.AsyncLazyEnumerable;
import jospi.iterator.ExitToken;
import jospi.models.comments.CommentBundle;
import jospi.models.comments.CommentBundle.Cursor;

public final class Comments {
	private static final String BASE = "/comments/";
	
	private final OsuApiClient client;

	protected Comments(OsuApiClient client) {
		this.client = client;
	}
	
	public CompletableFuture<CommentBundle> getComment(int commentId) {
		return client.getJsonAsync(BASE+commentId);
	}
	
	public AsyncLazyEnumerable<Cursor, CommentBundle> getComments(int after, CommentableType type,
			int commentableId, int parentId, CommentSortType sort) {
		ExitToken<Cursor> token = new ExitToken<>((new CommentBundle()).new Cursor());
		Function<ExitToken<Cursor>, CompletableFuture<CommentBundle>> func = t ->
			CompletableFuture.supplyAsync(() -> {
				CommentBundle bundle = new CommentBundle();
				bundle = client.getJson(BASE, map -> {
					map.put("cursor[id]", t.getToken().id==0? null : t.getToken().id);
					map.put("cursor[created_at]", t.getToken().createdAt==null? null : t.getToken().createdAt);
					map.put("after", after);
					map.put("commentable_type", type);
					map.put("commentable_id", commentableId);
					map.put("parent_id", parentId);
					map.put("sort", sort);
				});
				if (bundle==null) {
			        throw new OsuApiException("An error occured while requesting the comment bundle. (bundle is null)");
				}
				token.setNext(bundle==null? null : bundle.getCursor());
				return bundle;
			});
			return new AsyncLazyEnumerable<>(func, token);
	}

	// REQUIRES USER

	public CompletableFuture<CommentBundle> newComment(int id, CommentableType type, String message, int parentId) {
		client.requiresUser();
		return client.getJsonAsync(BASE, map -> {
				map.put("commentable_id", id);
				map.put("commentable_type", type);
				map.put("message", message);
				map.put("parent_id", parentId);
			}, HttpMethod.POST);
	}
} 
