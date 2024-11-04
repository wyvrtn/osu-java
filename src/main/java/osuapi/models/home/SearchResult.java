package osuapi.models.home;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.users.User;
import osuapi.models.wikis.WikiPage;

@Getter
@Setter
@NoArgsConstructor
public class SearchResult<T> {
    
    @JsonProperty("data")
    private T[] data;

    @JsonProperty("total")
    private int total;

    public static <T> Pair<List<User>, List<WikiPage>> process(SearchResult<T> instance) {
        List<User> left = new ArrayList<>();
        List<WikiPage> right = new ArrayList<>();
        for (T element : instance.getData()) {
            if (element instanceof User) left.add((User) element);
            if (element instanceof WikiPage) right.add((WikiPage) element);
        }
        return Pair.of(left, right);
    }
}
