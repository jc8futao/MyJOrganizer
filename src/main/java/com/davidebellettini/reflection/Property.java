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

package com.davidebellettini.reflection;

import java.lang.reflect.Method;

/**
 * Container for easily handling property reflection data
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class Property {

    private Method getter;
    private Method setter;
    private String name;

    /**
     * <p>Constructor for Property.</p>
     */
    public Property() {
    }

    /**
     * <p>Getter for the field <code>getter</code>.</p>
     *
     * @return a {@link java.lang.reflect.Method} object.
     */
    public Method getGetter() {
        return this.getter;
    }

    /**
     * <p>Getter for the field <code>setter</code>.</p>
     *
     * @return a {@link java.lang.reflect.Method} object.
     */
    public Method getSetter() {
        return this.setter;
    }

    /**
     * <p>Setter for the field <code>getter</code>.</p>
     *
     * @param getter a {@link java.lang.reflect.Method} object.
     */
    public void setGetter(Method getter) {
        this.getter = getter;
    }

    /**
     * <p>Setter for the field <code>setter</code>.</p>
     *
     * @param setter a {@link java.lang.reflect.Method} object.
     */
    public void setSetter(Method setter) {
        this.setter = setter;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>getType</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    public Class<?> getType() {
        return getGetter().getReturnType();
    }

    /**
     * <p>isReadable</p>
     *
     * @return a boolean.
     */
    public boolean isReadable() {
        return getGetter() != null;
    }

    /**
     * <p>isWritable</p>
     *
     * @return a boolean.
     */
    public boolean isWritable() {
        return getSetter() != null;
    }
}
