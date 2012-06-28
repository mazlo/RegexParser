package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.AbstractRegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class Group extends AbstractRegexPart {

	public Group(String token) {
		super( token, RegexTypes.Group );
	}

	@Override
	public String getContent() {
		return getPart();
	}

}
