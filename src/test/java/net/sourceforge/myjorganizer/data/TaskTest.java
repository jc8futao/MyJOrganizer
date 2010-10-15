package net.sourceforge.myjorganizer.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TaskTest {
	private Task task;

	@Before
	public void setup() {
		task = new Task();
	}
	
	@Test
	// fluent interface
	public void testNameSetterReturnsTask() {
		assertEquals(task, task.setName(null));
	}

	@Test
	public void testNameAccessors() {
		String name = "piripicchio";
		task.setName(name);

		assertEquals(name, task.getName());
	}
	
	@Test
	public void testConstructorNameAndPriority()
	{
		String name = "hello";
		Priority priority = Priority.factory(true, true);
		
		task = new Task(name, priority);
		
		assertEquals(name, task.getName());
		assertEquals(priority, task.getPriority());
	}

	@Test
	// fluent interface
	public void testStartDateSetterReturnsTask() {
		assertEquals(task, task.setStartDate(null));
	}

	@Test
	public void testStartDateAccessors() {
		Date date = new Date();
		task.setStartDate(date);

		assertEquals(date, task.getStartDate());
	}

	@Test
	// fluent interface
	public void testDueDateSetterIsFluent() {
		assertEquals(task, task.setDueDate(null));
	}

	@Test
	public void testDueDateAccessors() {
		Date date = new Date();
		task.setDueDate(date);

		assertEquals(date, task.getDueDate());
	}

	@Test
	public void testStatusAccessors() {
		TaskStatus status = new TaskStatus("test");
		task.setStatus(status);

		assertEquals(status, task.getStatus());
	}

	@Test
	public void testStatusSetterIsFluent() {
		assertEquals(task, task.setStatus(null));
	}

	@Test
	public void testIdAccessors() {
		task.setId(1);

		assertEquals(1, task.getId());
	}

	@Test
	public void testIdSetterReturnsTask() {
		assertSame(task, task.setId(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompletionCannotBeNegative() {
		task.setCompletion(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompletionCannotBeMoreThan100() {
		task.setCompletion(101);
	}

	@Test
	public void testCompletionAccessors() {
		for (int i = 0; i <= 100; i++) {
			task.setCompletion(i);
			assertEquals(i, task.getCompletion());
		}
	}

	@Test
	public void testCompletionSetterIsFluent() {
		assertEquals(task, task.setCompletion(10));
	}

	@Test
	public void testToString() {

		task.setName("DonaldDuck");
		assertEquals(task.getName(), task.toString());
	}

	@Test
	public void testUrgentAccessors() {
		boolean urgent = !task.isUrgent();
		task.setUrgent(urgent);

		assertEquals(urgent, task.isUrgent());
	}

	@Test
	public void testUrgentSetterIsFluent() {
		assertEquals(task, task.setUrgent(true));
	}

	@Test
	public void testImportantAccessors() {
		boolean important = !task.isImportant();
		task.setImportant(important);

		assertEquals(important, task.isImportant());
	}

	@Test
	public void testImportantSetterIsFluent() {
		assertEquals(task, task.setImportant(true));
	}

	@Test
	public void testPriorityAccessors() {
		task.setPriority(Priority.factory(true, true));

		assertEquals(Priority.factory(true, true), task.getPriority());
	}

	@Test
	public void testPrioritySetterIsFluent() {
		assertEquals(task, task.setPriority(null));
	}
}
