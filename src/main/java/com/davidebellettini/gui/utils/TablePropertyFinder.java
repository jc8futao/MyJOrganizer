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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * <p>
 * TablePropertyFinder class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TablePropertyFinder {
    /**
     * <p>
     * find
     * </p>
     * 
     * finds all properties annotated with
     * {@link com.davidebellettini.gui.utils.ShowInTable}
     * 
     * 
     * @param type a {@link java.lang.Class} object.
     * @return an array of {@link com.davidebellettini.gui.utils.TableProperty}
     *         objects.
     */
    public static TableProperty[] find(Class<?> type) {
        HashMap<String, Method> getters = new HashMap<String, Method>();
        HashMap<String, Method> setters = new HashMap<String, Method>();

        HashMap<String, HashMap<String, Method>> prefixes = new HashMap<String, HashMap<String, Method>>();

        prefixes.put("is", getters);
        prefixes.put("get", getters);
        prefixes.put("set", setters);

        ArrayList<TableProperty> properties = new ArrayList<TableProperty>();

        for (Method method : type.getMethods()) {
            String methodName = method.getName();

            if (method.getDeclaringClass() != Object.class) {
                for (String prefix : prefixes.keySet()) {
                    if (methodName.startsWith(prefix)) {
                        prefixes.get(prefix).put(
                                methodName.substring(prefix.length()), method);
                        break;
                    }
                }
            }
        }

        for (Entry<String, Method> entry : getters.entrySet()) {
            Method getter = entry.getValue();
            ShowInTable displayAnnotation = getter
                    .getAnnotation(ShowInTable.class);

            if (displayAnnotation != null
                    && getter.getReturnType() != Void.class) {
                TableProperty property = new TableProperty();
                property.setGetter(getter);

                if (displayAnnotation.name().equals("")) {
                    property.setName(entry.getKey());
                } else {
                    property.setName(displayAnnotation.name());
                }

                property.setPosition(displayAnnotation.position());

                if (displayAnnotation.editable()) {
                    Method setter = setters.get(entry.getKey());

                    if (setter != null
                            && setter.getParameterTypes().length == 1) {
                        property.setSetter(setter);
                    }
                }

                properties.add(property);
            }
        }

        TableProperty[] propertyArray = new TableProperty[0];
        propertyArray = properties.toArray(propertyArray);

        Arrays.sort(propertyArray, new Comparator<TableProperty>() {
            @Override
            public int compare(TableProperty o1, TableProperty o2) {
                return o1.getPosition() - o2.getPosition();
            }
        });

        return propertyArray;
    }
}
