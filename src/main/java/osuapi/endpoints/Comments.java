package osuapi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import osuapi.client.OsuApiClient;
import osuapi.client.resources.OsuApiException;
import osuapi.enums.CommentSortType;
import osuapi.enums.CommentableType;
import osuapi.iterator.AsyncLazyEnumerable;
import osuapi.iterator.ExitToken;
import osuapi.models.comments.CommentBundle;
import osuapi.models.comments.CommentBundle.Cursor;

public final class Comments {
	
	private OsuApiClient client;

	protected Comments(OsuApiClient client) {
		this.client = client;
	}
	
	public CompletableFuture<CommentBundle> getComment(int commentId) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson("/comments/"+commentId, new CommentBundle())
		);
	}
	
	public AsyncLazyEnumerable<Cursor, CommentBundle> getComments(int after, CommentableType type,
			int commentableId, int parentId, CommentSortType sort) {
		ExitToken<Cursor> token = new ExitToken<>((new CommentBundle()).new Cursor(), Objects::nonNull);
		Function<ExitToken<Cursor>, CompletableFuture<CommentBundle>> func = t ->
			CompletableFuture.supplyAsync(() -> {
				Map<String, Object> params = new HashMap<>();
				params.put("cursor[id]", t.getToken().id==0? null : t.getToken().id);
				params.put("cursor[created_at]", t.getToken().createdAt==null? null : t.getToken().createdAt);
				params.put("after", after);
				params.put("commentable_type", type);
				params.put("commentable_id", commentableId);
				params.put("parent_id", parentId);
				params.put("sort", sort);
				CommentBundle bundle = new CommentBundle();
				bundle = client.getJson("/comments", params, new CommentBundle());
				if (bundle==null) {
			        try {
						throw new OsuApiException("An error occured while requesting the comment bundle. (bundle is null)");
					} catch (OsuApiException e) {
						e.printStackTrace();
					}
				}
				token.setNext(bundle==null? null : bundle.getCursor());
				return bundle;
			});
			return new AsyncLazyEnumerable<>(func, token);
	}
} 
