/*
 * This file is part of MyJOrganizer.
 *
 * MyJOrganizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyJOrganizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MyJOrganizer.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sourceforge.myjorganizer.i18n;

import java.util.ResourceBundle;

/**
 * <p>Translator class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class Translator {
    private static ResourceBundle bundle;
    static {
        try {
            bundle = ResourceBundle.getBundle("messages");
        } catch (Exception e) {
        }
    }

    /**
     * <p>_</p>
     *
     * @param source a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String _(String source) {
        if (bundle != null && bundle.containsKey(source))
            return bundle.getString(source);

        return "@" + source;
    }
}
