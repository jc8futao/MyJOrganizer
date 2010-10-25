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

package com.davidebellettini.gui.utils;

import javax.swing.table.AbstractTableModel;

public abstract class GenericTableModel<T> extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8213634086228585295L;
	
	private TableProperty[] properties;

	public GenericTableModel(Class<? extends T> type) {
		properties = TablePropertyFinder.find(type);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return properties.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return properties[columnIndex].getName();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return properties[columnIndex].getType();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return properties[columnIndex].isWritable();
	}
}
