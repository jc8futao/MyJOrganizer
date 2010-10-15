package net.sourceforge.myjorganizer.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class PriorityTest {

	@Test
	public void testUrgentGetter() {
		boolean urgent = true;

		Priority priority = Priority.factory(urgent, true);
		assertEquals(urgent, priority.isUrgent());

		urgent = !urgent;

		priority = Priority.factory(urgent, true);
		assertEquals(urgent, priority.isUrgent());
	}

	@Test
	public void testImportantGetter() {
		boolean important = true;

		Priority priority = Priority.factory(true, important);
		assertEquals(important, priority.isImportant());

		important = !important;

		priority = Priority.factory(true, important);
		assertEquals(important, priority.isImportant());
	}

	@Test
	public void testFlyweight() {

		boolean[][] values = { { false, false }, { false, true },
				{ true, false }, { true, true } };

		for (boolean[] currentValues : values)
			assertSame(Priority.factory(currentValues[0], currentValues[1]),
					Priority.factory(currentValues[0], currentValues[1]));
	}
}
