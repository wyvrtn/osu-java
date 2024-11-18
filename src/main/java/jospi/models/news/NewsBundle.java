package jospi.models.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsBundle {

    @JsonProperty("cursor_string")
    private String cursorString;

    @JsonProperty("news_posts")
    private NewsPost[] newsPosts;

    @JsonProperty("news_sidebar")
    private NewsSidebar sidebar;

    @JsonProperty("search")
    private NewsSearchMeta searchMeta;
}
