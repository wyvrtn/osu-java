package jospi.models.news;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class NewsPost {
    
    @JsonProperty("author")
    private String author;

    @JsonProperty("edit_url")
    private String editUrl;

    @JsonProperty("first_image")
    private String firstImage;

    @JsonProperty("id")
    private int id;

    @JsonProperty("published_at")
    private LocalDateTime publishedAt;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("title")
    private String title;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("content")
    private String content;

    @JsonProperty("navigation")
    private Navigation navigation;

    @JsonProperty("preview")
    private String preview;
}