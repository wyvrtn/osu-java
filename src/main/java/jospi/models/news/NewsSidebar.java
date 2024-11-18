package jospi.models.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsSidebar {

    @JsonProperty("current_year")
    private int currentYear;

    @JsonProperty("news_posts")
    private NewsPost[] currentPosts;

    @JsonProperty("years")
    private int[] years;
}
