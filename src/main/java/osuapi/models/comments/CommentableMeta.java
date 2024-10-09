package osuapi.models.comments;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.comments.CommentableOwnerTitle;

@Getter
@Setter
@NoArgsConstructor
public class CommentableMeta {
	
	private int id;
	
	@JsonProperty("owner_id")
	private int ownerId;
	
	@JsonProperty("owner_title")
	private CommentableOwnerTitle ownerTitle;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("url")
	private String url;
}
