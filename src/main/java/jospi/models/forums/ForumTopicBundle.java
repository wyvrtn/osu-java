package jospi.models.forums;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForumTopicBundle {

	@JsonProperty("topic")
	private ForumTopic topic;

	@JsonProperty("posts")
	private ForumPost[] posts;
}
