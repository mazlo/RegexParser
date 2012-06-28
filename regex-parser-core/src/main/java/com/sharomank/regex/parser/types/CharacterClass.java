package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.AbstractRegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class CharacterClass extends AbstractRegexPart {

	public CharacterClass(String token) {
		super( token, RegexTypes.CharacterClass );
	}

	@Override
	public String getContent() {
		return getPart();
	}

}
