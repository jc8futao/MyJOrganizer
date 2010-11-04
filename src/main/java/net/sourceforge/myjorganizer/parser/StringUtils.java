package net.sourceforge.myjorganizer.parser;

/**
 * <p>StringUtils class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 *
 *         String manipulation tools
 * @version $Id$
 */
public class StringUtils {

    private final static String[][] replacements = { { "\\", "\\\\" },
            { "\n", "\\n" }, { "\"", "\\\"" }, {"\t", "\\t" }};

    /**
     * Escapes a string
     *
     * @param unescaped a {@link java.lang.String} object.
     * @return escaped string
     */
    public static String escape(String unescaped) {

        for (int i = 0; i < replacements.length; i++) {
            unescaped = unescaped.replace(replacements[i][0],
                    replacements[i][1]);
        }

        return "\"" + unescaped + "\"";
    }

    /**
     * Escapes an object's toString
     *
     * @param unescaped object
     * @return escaped string
     */
    public static String escape(Object unescaped) {
        return escape(unescaped.toString());
    }

    /**
     * Unescapes a string
     *
     * @param escaped a {@link java.lang.String} object.
     * @return unescaped string
     */
    public static String unescape(String escaped) {
        escaped = escaped.substring(1, escaped.length() - 1);

        for (int i = 0; i < replacements.length; i++) {
            escaped = escaped.replace(replacements[i][1], replacements[i][0]);
        }

        return escaped;
    }
}
