package net.sourceforge.myjorganizer.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testEscapeNewline() {
        assertEquals("\"\\n\"", StringUtils.escape((Object)"\n"));
    }

    @Test
    public void testEscapeDoubleQuote() {
        assertEquals("\"\\\"\"", StringUtils.escape("\""));
    }

    @Test
    public void testEscapeBackslash() {
        assertEquals("\"\\\\\"", StringUtils.escape("\\"));
    }
    
    @Test
    public void unescapeNewLine() {
        assertEquals("\n", StringUtils.unescape("\"\\n\""));
    }
}
