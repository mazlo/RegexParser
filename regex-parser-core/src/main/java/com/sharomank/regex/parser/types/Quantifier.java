package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.AbstractRegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class Quantifier extends AbstractRegexPart {

	public Quantifier(String token) {
		super( token, RegexTypes.Quantifier );
	}

	@Override
	public String getContent() {
		return getPart();
	}

}
