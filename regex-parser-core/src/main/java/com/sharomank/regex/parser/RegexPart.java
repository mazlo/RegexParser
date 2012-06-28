package com.sharomank.regex.parser;

import com.sharomank.regex.parser.enums.RegexTypes;

public class RegexPart extends AbstractRegexPart {

	public RegexPart(String token, RegexTypes type) {
		super( token, type );
	}

	@Override
	public String getContent() {
		return getPart();
	}

}
