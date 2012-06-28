package com.sharomank.regex.parser;

import java.text.MessageFormat;

import com.sharomank.regex.parser.enums.RegexTypes;

/**
 * Class for store result parsing
 *
 * @author Roman Kurbangaliyev
 * @since 21.05.2012
 */
public abstract class AbstractRegexPart {

    private final String part;
	private String content;
    private final RegexTypes type;

    public AbstractRegexPart(String token, RegexTypes type) {
        if (token == null || type == null) {
            throw new IllegalArgumentException();
        }

        this.part = token;
        this.type = type;
    }

    public String getPart() {
        return part;
    }

	public abstract String getContent();

	public void setContent( String content ) {
		this.content = content;
	}

    public RegexTypes getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractRegexPart regexPart = (AbstractRegexPart) o;
        if (!part.equals(regexPart.part)) {
            return false;
        } else if (type != regexPart.type) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = part.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Regex part=''{0}'', type={1}", part, type.name());
    }
}