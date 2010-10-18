package net.sourceforge.myjorganizer.gui.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import net.sourceforge.myjorganizer.data.Priority;
import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.data.TaskStatus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaskModelTest {
	private TaskModel taskModel;
	private Task task;
	private ArgumentCaptor<TaskDataEvent> event;

	@Mock
	private TaskDataListener listener;

	@Before
	public void setUp() {
		taskModel = new TaskModel(task = new Task());
		taskModel.addTaskModelListener(listener);
		event = ArgumentCaptor.forClass(TaskDataEvent.class);
	}

	@Test
	public void testGetPriority() {
		boolean[][] values = { { false, false }, { false, true },
				{ true, false }, { true, true } };

		for (boolean[] currentValues : values) {
			task.setPriority(Priority.factory(currentValues[0],
					currentValues[1]));
			assertEquals(task.getPriority(), taskModel.getPriority());
		}
	}

	@Test
	public void testSetPriority() {
		boolean[][] values = { { false, false }, { false, true },
				{ true, false }, { true, true } };

		for (boolean[] currentValues : values) {
			taskModel.setPriority(Priority.factory(currentValues[0],
					currentValues[1]));

			assertEquals(Priority.factory(currentValues[0], currentValues[1]),
					task.getPriority());
		}
	}

	@Test
	public void testSetPriorityFiresEvent() {
		taskModel.setPriority(Priority.factory(true, true));
		taskModel.setPriority(Priority.factory(true, true));

		verify(listener, times(1)).contentChanged(event.capture());

		assertEquals("priority", event.getValue().getPropertyName());
	}

	@Test
	public void testNameAccessors() {
		String name = "piripicchio";
		taskModel.setName(name);

		verify(listener, times(1)).contentChanged(event.capture());
		assertEquals("name", event.getValue().getPropertyName());

		assertEquals(name, taskModel.getName());
	}

	@Test
	// fluent interface
	public void testNameSetterIsFluent() {
		assertEquals(taskModel, taskModel.setName(null));
	}

	@Test
	public void testStartDateAccessors() {
		Date date = new Date();
		taskModel.setStartDate(date);

		verify(listener, times(1)).contentChanged(event.capture());
		assertEquals("startDate", event.getValue().getPropertyName());

		assertEquals(date, taskModel.getStartDate());
	}

	@Test
	public void testStartSetterIsFluent() {
		assertEquals(taskModel, taskModel.setStartDate(null));
	}

	@Test
	public void testDueDateAccessors() {
		Date date = new Date();
		taskModel.setDueDate(date);

		verify(listener, times(1)).contentChanged(event.capture());
		assertEquals("dueDate", event.getValue().getPropertyName());

		assertEquals(date, taskModel.getDueDate());
	}

	@Test
	public void testDueDateSetterIsFluent() {
		assertEquals(taskModel, taskModel.setDueDate(null));
	}

	@Test
	public void testStatusAccessors() {
		TaskStatus status = new TaskStatus("test");
		taskModel.setStatus(status);

		verify(listener, times(1)).contentChanged(event.capture());
		assertEquals("status", event.getValue().getPropertyName());

		assertEquals(status, taskModel.getStatus());
	}

	@Test
	public void testStatusSetterIsFluent() {
		assertEquals(taskModel, taskModel.setStatus(null));
	}

	@Test
	public void testIdGetter() {
		int id = 10;

		task.setId(id);
		assertEquals(task.getId(), taskModel.getId());
	}

	@Test
	public void testCompletionAccessors() {
		int completion = 10;

		taskModel.setCompletion(completion);

		verify(listener, times(1)).contentChanged(event.capture());
		assertEquals("completion", event.getValue().getPropertyName());

		assertEquals(completion, taskModel.getCompletion());
	}

	@Test
	public void testCompletionSetterIsFluent() {
		assertEquals(taskModel, taskModel.setCompletion(10));
	}

	@Test
	public void testToString() {
		assertEquals(task.toString(), taskModel.toString());
	}
}
