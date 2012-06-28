package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.RegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class Anchor extends RegexPart {

	public Anchor(String token) {
		super( token, RegexTypes.Anchor );
	}

}
