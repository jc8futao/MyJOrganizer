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

import static org.junit.Assert.*;

import javax.swing.event.TableModelListener;

import org.junit.Before;
import org.junit.Test;

import com.davidebellettini.gui.utils.GenericTableModel;

public class GenericTableModelTest {

	private GenericTableModel<TestType> genericTableModel;

	@Before
	public void setUp() {
		this.genericTableModel = new GenericTableModel<TestType>(TestType.class) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -2189372718553533468L;

			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void addTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removeTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub

			}

			@Override
			public TestType getRowData(int rowIndex) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Test
	public void testColumnCount() {
		assertEquals(2, genericTableModel.getColumnCount());
	}

	@Test
	public void testColumnName() {
		assertEquals("Dummy", genericTableModel.getColumnName(0));
		assertEquals("Hello", genericTableModel.getColumnName(1));
	}

	@Test
	public void testColumnClass() {
		assertEquals(String.class, genericTableModel.getColumnClass(0));
	}

	@SuppressWarnings("unused")
	private class TestType {
		private String dummy;

		@ShowInTable(position = 0)
		public String getDummy() {
			return this.dummy;
		}

		@ShowInTable(position = 1, name = "Hello")
		public String getDummy2() {
			return "dummy";
		}

		public void setDummy(String dummy) {
			this.dummy = dummy;
		}
	}
}
