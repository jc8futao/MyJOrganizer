package net.sourceforge.myjorganizer.parser;

/**
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 */
public class StringUtils {
    public static String escape(String unescaped) {
        return "\"" + unescaped.replace("\"", "\\\"") + "\"";
    }

    public static String unescape(String escaped) {
        int length = escaped.length();
        return escaped.substring(1, length - 2).replace("\\\"", "\"");
    }
}
