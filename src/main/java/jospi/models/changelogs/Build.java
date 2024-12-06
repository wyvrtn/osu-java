package jospi.models.changelogs;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Build {

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @JsonProperty("display_version")
    private String displayVersion;

    @JsonProperty("id")
    private int id;

    @JsonProperty("users")
    private int users;

    @JsonProperty("version")
    private String version;

    @JsonProperty("youtube_id")
    private String youtubeId;

    @JsonProperty("changelog_entries")
    private ChangelogEntry[] changelog;

    @JsonProperty("update_stream")
    private UpdateStream updateStream;

    @JsonProperty("versions")
    private Versions versions;
}
