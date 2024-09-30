package osuapi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.beatmaps.Beatmap;
import osuapi.models.beatmaps.BeatmapSet;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapPlaycount {
  /// <summary>
  /// The ID of the beatmap.
  /// </summary>
  @JsonProperty("beatmap_id")
  public int beatmapId;

  /// <summary>
  /// The beatmap object. This may be null if the beatmap has been deleted.
  /// </summary>
  @JsonProperty("beatmap")
  public Beatmap beatmap;

  /// <summary>
  /// The beatmap set containing the beatmap. This may be null if the beatmap has been deleted.
  /// </summary>
  @JsonProperty("beatmapset")
  public BeatmapSet beatmapSet;

  /// <summary>
  /// The amount of times the user played the beatmap.
  /// </summary>
  @JsonProperty("count")
  public int count;
}
