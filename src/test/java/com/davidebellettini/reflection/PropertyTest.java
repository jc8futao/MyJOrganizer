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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class PropertyTest {

	private Method getter;
	private Method setter;

	@Before
	public void setUp() throws SecurityException, NoSuchMethodException {
		getter = TestClass.class.getMethod("getDummy");
		setter = TestClass.class.getMethod("setDummy", String.class);
	}

	@Test
	public void testGetterAccessors() {
		Property p = new Property();
		p.setGetter(getter);

		assertEquals(getter, p.getGetter());
	}

	@Test
	public void testSetterAccessors() {
		Property p = new Property();
		p.setSetter(setter);
		assertEquals(setter, p.getSetter());
	}

	@Test
	public void testNameAccessors() {
		String name = "DonaldDuck";

		Property p = new Property();
		p.setName(name);

		assertEquals(name, p.getName());
	}

	@Test
	public void testGetType() {
		Property p = new Property();
		p.setGetter(getter);

		assertEquals(getter.getReturnType(), p.getType());
	}

	@Test
	public void testIsReadable() {
		Property p = new Property();

		assertFalse(p.isReadable());

		p.setGetter(getter);

		assertTrue(p.isReadable());
	}
	
	@Test
	public void testIsWritable() {
		Property p = new Property();

		assertFalse(p.isWritable());

		p.setSetter(setter);

		assertTrue(p.isWritable());
	}

	private class TestClass {
		@SuppressWarnings("unused")
		public String getDummy() {
			return "Dummy";
		}

		@SuppressWarnings("unused")
		public void setDummy(String dummy) {

		}
	}
}
