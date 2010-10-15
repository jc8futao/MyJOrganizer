package net.sourceforge.myjorganizer.i18n;

import java.util.ResourceBundle;

public class Translator {
	private static ResourceBundle bundle;
	static {
		try {
			bundle = ResourceBundle.getBundle("messages");
		} catch (Exception e) {
		}
	}

	public static String _(String source) {
		if (bundle != null && bundle.containsKey(source))
			return bundle.getString(source);

		return "@" + source;
	}
}
