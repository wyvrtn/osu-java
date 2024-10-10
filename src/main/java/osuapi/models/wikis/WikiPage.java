package osuapi.models.wikis;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.wikis.WikiPageLayout;

@Getter
@Setter
@NoArgsConstructor
public class WikiPage {
	
	@JsonProperty("available_locales")
	private String[] availableLocales;
	
	@JsonProperty("layout")
	private WikiPageLayout layout;
	
	@JsonProperty("locale")
	private String locale;
	private String markdown;
	
	@JsonProperty("path")
	private String path;
	
	@JsonProperty("subtitle")
	private String subtitle;
	
	@JsonProperty("tags")
	private String[] tags;
	
	@JsonProperty("title")
	private String title;
}
