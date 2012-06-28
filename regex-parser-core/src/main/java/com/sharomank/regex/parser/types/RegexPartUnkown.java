package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.RegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class RegexPartUnkown extends RegexPart {

	public RegexPartUnkown(String token) {
		super( token, RegexTypes.Unknown );
	}

}
