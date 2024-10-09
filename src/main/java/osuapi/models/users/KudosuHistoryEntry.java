package osuapi.models.users;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.users.KudosuAction;
import osuapi.enums.users.KudosuModel;

@Getter
@Setter
@NoArgsConstructor
public class KudosuHistoryEntry {
    
    @JsonProperty("id")
    private int id;

    @JsonProperty("action")
    private KudosuAction action;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("model")
    private KudosuModel model;

    @JsonProperty("created_at")
    private OffsetDateTime created_at;

    @JsonProperty("giver")
    private KudosuGiver giver;

    @JsonProperty("post")
    private KudosuPost post;
}
