package com.sharomank.regex.parser.types;

import com.sharomank.regex.parser.RegexPart;
import com.sharomank.regex.parser.enums.RegexTypes;

public class None extends RegexPart {

	public None(String token) {
		super( token, RegexTypes.None );
	}

}
