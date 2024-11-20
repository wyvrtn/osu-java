package jospi.models.users;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.misc.Ruleset;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserExtended extends User {

    @JsonProperty("discord")
    private String discord;

    @JsonProperty("has_supported")
    private boolean hasSupported;

    @JsonProperty("interests")
    private String interests;

    @JsonProperty("join_date")
    private OffsetDateTime joinDate;

    @JsonProperty("location")
    private String location;

    @JsonProperty("max_blocks")
    private int maxBlocks;

    @JsonProperty("max_friends")
    private int maxFriends;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("playmode")
    private Ruleset playmode;

    @JsonProperty("playstyle")
    private String[] playstyle;

    @JsonProperty("post_count")
    private int forumPostCount;

    @JsonProperty("profile_hue")
    private int profileHue;

    @JsonProperty("profile_order")
    private ProfilePage[] profilePages;

    @JsonProperty("title")
    private String title;

    @JsonProperty("title_url")
    private String titleUrl;

    @JsonProperty("twitter")
    private String twitterUrl;

    @JsonProperty("website")
    private String websiteUrl;
}
