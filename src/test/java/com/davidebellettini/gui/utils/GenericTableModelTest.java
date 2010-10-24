package com.davidebellettini.gui.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.davidebellettini.gui.utils.GenericTableModel;

public class GenericTableModelTest {

	private GenericTableModel<TestType> genericTableModel;

	@Before
	public void setUp() {
		this.genericTableModel = new GenericTableModel<TestType>(TestType.class);
	}

	@Test
	public void testColumnCount() {
		assertEquals(2, genericTableModel.getColumnCount());
	}

	@Test
	public void testRowCount() {
		assertEquals(0, genericTableModel.getRowCount());
	}

	@Test
	public void testColumnName() {
		assertEquals("Dummy", genericTableModel.getColumnName(0));
		assertEquals("Dummy2", genericTableModel.getColumnName(1));
	}

	@Test
	public void testColumnClass() {
		assertEquals(String.class, genericTableModel.getColumnClass(0));
	}

	private class TestType {
		@SuppressWarnings("unused")
		@DisplayInTable(position = 0)
		public String getDummy() {
			return "dummy";
		}

		@SuppressWarnings("unused")
		@DisplayInTable(position = 1)
		public String getDummy2() {
			return "dummy";
		}
	}
}
