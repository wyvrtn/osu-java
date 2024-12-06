package jospi.models.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.models.users.User;
import jospi.models.wikis.WikiPage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchResult<T> {

    @JsonProperty("data")
    private T[] data;

    @JsonProperty("total")
    private int total;

    public static <T extends Object> Map<String, List<?>> process(SearchResult<? extends T> instance) {
        List<User> userEntries = new ArrayList<>();
        List<WikiPage> wikiPageEntries = new ArrayList<>();
        for (T element : instance.getData()) {
            if (element instanceof User) userEntries.add((User) element);
            if (element instanceof WikiPage) wikiPageEntries.add((WikiPage) element);
        }
        Map<String, List<?>> result = new ConcurrentHashMap<>();
        result.put("user_entries", userEntries);
        result.put("wiki_page_entries", wikiPageEntries);
        return result;
    }
}
