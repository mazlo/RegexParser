package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.AbstractRegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class CharacterGroup extends AbstractRegexPart {

	public CharacterGroup(String token) {
		super( token, RegexTypes.CharacterGroup );
	}

	@Override
	public String getContent() {
		String part = getPart();
		return part.substring( 1, part.length() - 1 );
	}

}
