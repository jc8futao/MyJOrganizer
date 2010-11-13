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

import org.junit.Test;

import com.davidebellettini.reflection.Property;

public class TablePropertyFinderTest {

	@Test
	public void testFind() {
		Property[] properties = TablePropertyFinder.find(TestClass.class);
		assertNotNull(properties);

		assertEquals("Dummy", properties[0].getName());
		assertTrue(properties[0].isWritable());
		assertTrue(properties[0].isReadable());
		
		assertEquals("Dummy2", properties[1].getName());
		assertFalse(properties[1].isWritable());
		assertTrue(properties[1].isReadable());
	}

	@SuppressWarnings("unused")
	private class TestClass {
		private String dummy;

		public void setDummy(String dummy) {
			this.dummy = dummy;
		}

		@ShowInTable(position = 2)
		public String getDummy2() {
			return this.dummy;
		}

		@ShowInTable(position = 1)
		public String getDummy() {
			return this.dummy;
		}
	}
}
