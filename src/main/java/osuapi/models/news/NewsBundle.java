package osuapi.models.news;

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

    @Getter
    @Setter
    @NoArgsConstructor
    public static class NewsSidebar {

        @JsonProperty("current_year")
        private int currentYear;

        @JsonProperty("news_posts")
        private NewsPost[] currentPosts;

        @JsonProperty("years")
        private int[] years;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class NewsSearchMeta {
        
        @JsonProperty("limit")
        private int limit;

        @JsonProperty("sort")
        private String sort;
    }
}
