package osuapi.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChangelogEntryCategory implements DescriptionEnum<ChangelogEntryCategory> {
	WEB("Web"),
	AUDIO("Audio"),
	CODE("Code"),
	EDITOR("Editor"),
	GAMEPLAY("Gameplay"),
	GAMEPLAYOSU("Gameplay (osu!)"),
	GAMEPLAYCATCH("Gameplay (osu!catch)"),
	GAMEPLAYTAIKO("Gameplay (osu!taiko)"),
	GAMEPLAYMANIA("Gameplay (osu!mania)"),
	GRAPHICS("Graphics"),
	SONGSELECT("Song Select"),
	RELIABILITY("Reliability"),
	UI("UI"),
	CODEQUALITY("Code Quality"),
	TESTING("Testing"),
	ONLINE("Online"),
	RESULTS("Results"),
	FRAMEWORK("Framework"),
	MOBILE("Mobile"),
	SKINEDITOR("Skin Editor"),
	MULTIPLAYER("Multiplayer"),
	TOURNAMENT("Tournament"),
	SETTINGS("Settings"),
	PERFORMANCE("Performance"),
	WIKI("Wiki"),
	LOCALIZATION("Localization"),
	MISCELLANEOUS("Misc"),
	DIFFICULTYCALCULATION("Difficulty Calculation"),
	API("Api"),
	COSMETIC("Cosmetic"),
	CHAT("Chat"),
	SKINNING("Skinning"),
	MAINMENU("Main Menu"),
	SPECTATOR("Spectator");
	
	private String description;
	
	private ChangelogEntryCategory(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public ChangelogEntryCategory getEnum(String input) {
		ChangelogEntryCategory result = null;
		for (ChangelogEntryCategory ChangelogEntryCategory : values()) {
			if (StringUtils.equalsIgnoreCase(ChangelogEntryCategory.name(), input) ||
					StringUtils.equalsIgnoreCase(ChangelogEntryCategory.getDescription(), input)) {
				result = ChangelogEntryCategory;
				break;
			}
		}
		return result;
	}
}
