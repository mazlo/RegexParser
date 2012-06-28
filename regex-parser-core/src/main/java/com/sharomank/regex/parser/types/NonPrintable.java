package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.RegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class NonPrintable extends RegexPart {

	public NonPrintable(String token) {
		super( token, RegexTypes.NonPrintable );
	}

}
