package com.sharomank.regex.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.sharomank.regex.parser.types.Alternation;
import com.sharomank.regex.parser.types.Anchor;
import com.sharomank.regex.parser.types.CharacterClass;
import com.sharomank.regex.parser.types.CharacterGroup;
import com.sharomank.regex.parser.types.Group;
import com.sharomank.regex.parser.types.NonPrintable;
import com.sharomank.regex.parser.types.None;
import com.sharomank.regex.parser.types.Quantifier;
import com.sharomank.regex.parser.types.QuantifierGroup;
import com.sharomank.regex.parser.types.RegexPartError;

/**
 * @author Roman Kurbangaliyev
 * @since 31.05.12
 */
public class RegexParserTest {

    @Test
    public void simpleGroup() throws Exception {
        String regex = "(test)";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new Group( "(" ) );
		expected.add( new None( "t" ) );
		expected.add( new None( "e" ) );
		expected.add( new None( "s" ) );
		expected.add( new None( "t" ) );
		expected.add( new Group( ")" ) );

        checkParse(regex, expected);
    }

    @Test
    public void simpleCharacterGroup() throws Exception {
        String regex = "[test]";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new CharacterGroup( "[test]" ) );

        checkParse(regex, expected);
    }

    @Test
    public void simpleCharacterClass() throws Exception {
        String regex = "\\d\\D\\s\\S\\w\\W.";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new CharacterClass( "\\d" ) );
		expected.add( new CharacterClass( "\\D" ) );
		expected.add( new CharacterClass( "\\s" ) );
		expected.add( new CharacterClass( "\\S" ) );
		expected.add( new CharacterClass( "\\w" ) );
		expected.add( new CharacterClass( "\\W" ) );
		expected.add( new CharacterClass( "." ) );

        checkParse(regex, expected);
    }

    @Test
    public void simpleQuantifier() throws Exception {
        String regex = "a+b?d*";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new None( "a" ) );
		expected.add( new Quantifier( "+" ) );
		expected.add( new None( "b" ) );
		expected.add( new Quantifier( "?" ) );
		expected.add( new None( "d" ) );
		expected.add( new Quantifier( "*" ) );

		checkParse( regex, expected );
    }

    @Test
    public void simpleQuantifierGroup() throws Exception {
        String regex = "a{2}";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new None( "a" ) );
		expected.add( new QuantifierGroup( "{2}" ) );

        checkParse(regex, expected);
    }

    @Test
    public void simpleAlternation() throws Exception {
        String regex = "a|b";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new None( "a" ) );
		expected.add( new Alternation( "|" ) );
		expected.add( new None( "b" ) );

        checkParse(regex, expected);
    }

    @Test
    public void simpleBackslash() throws Exception {
        String regex = "\\\\\\\\";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new None( "\\\\" ) );
		expected.add( new None( "\\\\" ) );

		checkParse( regex, expected );
    }

    @Test
    public void simpleAnchorWithoutBackslash() throws Exception {
        String regex = "^t$";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new Anchor( "^" ) );
		expected.add( new None( "t" ) );
		expected.add( new Anchor( "$" ) );

		checkParse( regex, expected );
    }

    @Test
    public void simpleAnchorStartAndEnd() throws Exception {
        String regex = "\\At\\Z";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new Anchor( "\\A" ) );
		expected.add( new None( "t" ) );
		expected.add( new Anchor( "\\Z" ) );

		checkParse( regex, expected );
    }

    @Test
    public void simpleAnchorStartAndEndLine() throws Exception {
        String regex = "\\At\\z";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new Anchor( "\\A" ) );
		expected.add( new None( "t" ) );
		expected.add( new Anchor( "\\z" ) );

		checkParse( regex, expected );
    }

    @Test
    public void simpleAnchorOther() throws Exception {
        String regex = "\\G\\b\\B";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new Anchor( "\\G" ) );
		expected.add( new Anchor( "\\b" ) );
		expected.add( new Anchor( "\\B" ) );

		checkParse( regex, expected );
    }

    @Test
    public void simpleNonPrintable() throws Exception {
        String regex = "\\a\\t\\e\\f\\v\\r\\n";
		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();

		expected.add( new NonPrintable( "\\a" ) );
		expected.add( new NonPrintable( "\\t" ) );
		expected.add( new NonPrintable( "\\e" ) );
		expected.add( new NonPrintable( "\\f" ) );
		expected.add( new NonPrintable( "\\v" ) );
		expected.add( new NonPrintable( "\\r" ) );
		expected.add( new NonPrintable( "\\n" ) );

        checkParse(regex, expected);
    }

    @Test
    public void simpleParseError() throws Exception {
        String regex = "parse|error\\";

		List<AbstractRegexPart> expected = new ArrayList<AbstractRegexPart>();
		expected.add( new RegexPartError( "parse|error\\" ) );

        checkParse(regex, expected);
    }

	private void checkParse( String regex, List<AbstractRegexPart> expected ) {
		List<AbstractRegexPart> actual = RegexParser.parse( regex );
        matchRegexParts(expected, actual);
    }

	private boolean matchRegexParts( List<AbstractRegexPart> expected, List<AbstractRegexPart> actual ) {
        if (expected == null || actual == null) {
            throw new IllegalArgumentException("list params cannot be null");
        }
        if (expected.size() != actual.size()) {
            throw new IllegalStateException("size is different");
        }

		Iterator<AbstractRegexPart> expIterator = expected.iterator();
		Iterator<AbstractRegexPart> actIterator = actual.iterator();

        while (expIterator.hasNext()) {
			AbstractRegexPart ex = expIterator.next();
			AbstractRegexPart ac = actIterator.next();
            if (!ex.equals(ac)) {
                throw new IllegalStateException("parts is different");
            }
        }
        return true;
    }
}
