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

package com.davidebellettini.gui.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Valid only on getter methods
 */
@Target(ElementType.METHOD)
/**
 * Read at runtime
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * Annotation for using with GenericTableModel
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 */
public @interface ShowInTable {
    /**
     * Used for sorting columns
     * 
     * @return
     */
    int position() default Integer.MAX_VALUE;

    /**
     * Overrides guessed column name
     * 
     * @return
     */
    String name() default "";

    /**
     * Tells if the cell can be written
     * 
     * @return
     */
    boolean editable() default true;
}
