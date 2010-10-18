package net.sourceforge.myjorganizer.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TaskStatusTest {
	@Test
	public void testNameAccessors() {
		String name = "Donald Duck";
		TaskStatus tstatus = new TaskStatus(name);

		assertEquals(name, tstatus.getName());
	}

	@Test
	public void testIdAccessors() {
		int id = 10;

		TaskStatus tstatus = new TaskStatus("Hello");
		tstatus.setId(id);
		
		assertEquals(id, tstatus.getId());
	}
}
