package osuapi.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;

import osuapi.client.OsuApiClient;
import osuapi.enums.CommentSortType;
import osuapi.enums.CommentableType;
import osuapi.models.comments.CommentBundle;
import osuapi.models.comments.CommentBundle.Cursor;
import osuapi.models.custom.AsyncLazyEnumerator;
import osuapi.models.custom.ExitToken;
import osuapi.models.custom.OsuApiException;

public class Comments {
	
	@Autowired
	private OsuApiClient client;
	
	public CompletableFuture<CommentBundle> getComment(int commentId) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return client.getJson("/comments/"+commentId, new CommentBundle());
			} catch (OsuApiException e) {
				e.printStackTrace();
			}
			return null;
		});
	}
	
	public AsyncLazyEnumerator<Cursor, CommentBundle> getComments(int after, CommentableType type,
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
				CommentBundle bundle = null;
				try {
					bundle = client.getJson("/comments"
							+client.buildQueryString(params), new CommentBundle());
				} catch (UnsupportedEncodingException | OsuApiException e) {
					e.printStackTrace();
				}
				if (bundle==null) {
			        try {
						throw new OsuApiException("An error occured while requesting the comment bundle. (bundle is null)");
					} catch (OsuApiException e) {
						e.printStackTrace();
					}
				}
				token.setNext(bundle.getCursor());
				return bundle;
			});
			return new AsyncLazyEnumerator<>(func, token);
	}
} 
