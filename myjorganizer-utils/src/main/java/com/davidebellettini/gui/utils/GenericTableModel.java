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

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

/**
 * <p>Abstract GenericTableModel class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public abstract class GenericTableModel<T> extends AbstractTableModel {
    /**
	 * 
	 */
    private static final long serialVersionUID = 8213634086228585295L;
    private static HashMap<Class<?>, Class<?>> conversionTypes = new HashMap<Class<?>, Class<?>>();

    static {
        conversionTypes.put(Byte.TYPE, Byte.class);
        conversionTypes.put(Short.TYPE, Short.class);
        conversionTypes.put(Integer.TYPE, Integer.class);
        conversionTypes.put(Long.TYPE, Long.class);
        conversionTypes.put(Float.TYPE, Float.class);
        conversionTypes.put(Double.TYPE, Double.class);
        conversionTypes.put(Boolean.TYPE, Boolean.class);
        conversionTypes.put(Character.TYPE, Character.class);
    }

    private static Class<?> convertToNonPrimitive(Class<?> type) {
        Class<?> converted = conversionTypes.get(type);

        if (converted != null)
            return converted;

        return type;
    }

    private TableProperty[] properties;

    /**
     * <p>Constructor for GenericTableModel.</p>
     *
     * @param type a {@link java.lang.Class} object.
     */
    public GenericTableModel(Class<? extends T> type) {
        properties = TablePropertyFinder.find(type);
    }

    /** {@inheritDoc} */
    @Override
    public int getColumnCount() {
        return properties.length;
    }

    /** {@inheritDoc} */
    @Override
    public String getColumnName(int columnIndex) {
        return properties[columnIndex].getName();
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return convertToNonPrimitive(properties[columnIndex].getType());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return properties[columnIndex].isWritable();
    }

    /**
     * <p>getRowData</p>
     *
     * @param rowIndex a int.
     * @return a T object.
     */
    public abstract T getRowData(int rowIndex);

    /**
     * <p>Getter for the field <code>properties</code>.</p>
     *
     * @return an array of {@link com.davidebellettini.gui.utils.TableProperty} objects.
     */
    protected TableProperty[] getProperties() {
        return properties;
    }

    /**
     * <p>getProperty</p>
     *
     * @param index a int.
     * @return a {@link com.davidebellettini.gui.utils.TableProperty} object.
     */
    protected TableProperty getProperty(int index) {
        return properties[index];
    }
}
