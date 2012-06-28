package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.AbstractRegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class RegexPartError extends AbstractRegexPart {

	public RegexPartError(String token) {
		super( token, RegexTypes.ParseError );
	}

	@Override
	public String getContent() {
		return getPart();
	}

}
