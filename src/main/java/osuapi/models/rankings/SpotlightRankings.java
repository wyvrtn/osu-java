package osuapi.models.rankings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.beatmaps.BeatmapSetExtended;
import osuapi.models.users.UserStatistics;

@Getter
@Setter
@NoArgsConstructor
public class SpotlightRankings {
    
    private BeatmapSetExtended[] beatmapsets;

    private UserStatistics[] rankings;

    private Spotlight spotlight;

    private int total;

    public SpotlightRankings(RankingsBundle bundle) {
        this.beatmapsets = bundle.getBeatmapsets();
        this.rankings = bundle.getRankings();
        this.spotlight = bundle.getSpotlight();
        this.total = bundle.getTotal();
    }
}
