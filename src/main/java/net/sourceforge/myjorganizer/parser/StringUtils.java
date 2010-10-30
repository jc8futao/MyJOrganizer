package net.sourceforge.myjorganizer.parser;

/**
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * 
 *         String manipulation tools
 */
public class StringUtils {

    private final static String[][] replacements = { { "\\", "\\\\" },
            { "\n", "\\n" }, { "\"", "\\\"" } };

    /**
     * Escapes a string
     * 
     * @param unescaped
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
     * @param escaped
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
