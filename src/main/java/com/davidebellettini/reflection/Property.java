/**
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
 */
public class Property {

    private Method getter;
    private Method setter;
    private String name;

    public Property() {
    }

    public Method getGetter() {
        return this.getter;
    }

    public Method getSetter() {
        return this.setter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getType() {
        return getGetter().getReturnType();
    }

    public boolean isReadable() {
        return getGetter() != null;
    }

    public boolean isWritable() {
        return getSetter() != null;
    }
}
