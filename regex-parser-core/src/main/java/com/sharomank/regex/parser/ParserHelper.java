package com.sharomank.regex.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.sharomank.regex.parser.enums.RegexTypes;
import com.sharomank.regex.parser.types.RegexPartUnkown;

/**
 * Helper class for parse regular expression
 *
 * @author Roman Kurbangaliyev
 * @since 21.05.2012
 */
public class ParserHelper {
    private final String expression;
    private int previousIndex;
    private int currentIndex;
    private RegexTypes currentType;
    private List<AbstractRegexPart> result;

    public ParserHelper(String expression) {
        this.expression = expression;
        this.result = new ArrayList<AbstractRegexPart>();
    }

    public String getExpression() {
        return expression;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public RegexTypes getCurrentType() {
        return currentType;
    }

    public void setCurrentType(RegexTypes currentType) {
        this.currentType = currentType;
    }

    public List<AbstractRegexPart> getResult() {
        return result;
    }

	public void putCurrentRegexPart() {

		AbstractRegexPart part;
		Class clazz = null;

		try {
			clazz = Class.forName( Constants.REGEX_TYPES_PACKAGE_NAME + "." + getCurrentType() );
		} catch ( ClassNotFoundException e ) {
			part = new RegexPartUnkown( getToken() );

			e.printStackTrace();
		}

		try {
			if ( clazz == null )
				throw new InstantiationException( "Could not instantiate class." );

			part = (AbstractRegexPart) clazz.getDeclaredConstructor( String.class ).newInstance( getToken() );
		} catch ( InstantiationException e ) {
			part = new RegexPartUnkown( getToken() );

			e.printStackTrace();
		} catch ( IllegalAccessException e ) {
			part = new RegexPartUnkown( getToken() );

			e.printStackTrace();
		} catch ( IllegalArgumentException e ) {
			part = new RegexPartUnkown( getToken() );

			e.printStackTrace();
		} catch ( SecurityException e ) {
			part = new RegexPartUnkown( getToken() );

			e.printStackTrace();
		} catch ( InvocationTargetException e ) {
			part = new RegexPartUnkown( getToken() );

			e.printStackTrace();
		} catch ( NoSuchMethodException e ) {
			part = new RegexPartUnkown( getToken() );

			e.printStackTrace();
		}

        result.add(part);
        setCurrentType(null);
        updatePreviousIndex();
    }

    /**
     * Is start of expression
     *
     * @return <code>true</code> - expression is start, <code>false</code> - expression isn't start.
     */
    public boolean isStart() {
        return getCurrentIndex() == 1;
    }

    /**
     * Is end of expression
     *
     * @return <code>true</code> - expression is end, <code>false</code> - expression isn't end.
     */
    public boolean isEnd() {
        return getCurrentIndex() >= getExpression().length();
    }

    public void incrementCurrentIndex() {
        currentIndex++;
    }

    public void updatePreviousIndex() {
        previousIndex = currentIndex;
    }

    public String getToken() {
        return expression.substring(previousIndex, currentIndex);
    }

    public char getCurrentChar() {
        return expression.charAt(currentIndex);
    }

    public char getPreviousChar() {
        char previousChar = ' ';
        if (currentIndex > 0) {
            previousChar = expression.charAt(currentIndex - 1);
        }
        return previousChar;
    }

    public String getPreviousToken() {
        if (result.size() > 0) {
            return result.get(result.size() - 1).getPart();
        }
        return "";
    }
}
