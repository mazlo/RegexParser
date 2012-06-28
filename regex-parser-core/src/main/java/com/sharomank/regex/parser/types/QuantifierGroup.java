package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.AbstractRegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class QuantifierGroup extends AbstractRegexPart {

	public QuantifierGroup(String token) {
		super( token, RegexTypes.QuantifierGroup );
	}

	@Override
	public String getContent() {
		String part = getPart();
		return part.substring( 1, part.length() - 1 );
	}

}
