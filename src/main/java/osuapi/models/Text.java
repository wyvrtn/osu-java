package osuapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Text {
	
	@JsonProperty("html")
	private String Html = null;
	
	@JsonProperty("bbcode")
	private String BBCode;
	
	@JsonProperty("raw")
	private String Raw;
	
	private String Markup = BBCode!=null? BBCode : Raw;
}
