package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.RegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class Alternation extends RegexPart {

	public Alternation(String token) {
		super( token, RegexTypes.Alternation );
	}

}
