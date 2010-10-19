package net.sourceforge.myjorganizer.gui.mvc.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import net.sourceforge.myjorganizer.data.Task;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaskModelTest {

	@Mock
	private Session session;

	private TaskModel taskModel;

	@Before
	public void setUp() {
		this.taskModel = new TaskModel(session);
	}

	@Test
	public void testAddTask() {
		Task task = new Task();
		Transaction transaction = mock(Transaction.class);
		
		when(session.beginTransaction()).thenReturn(transaction);
		
		taskModel.saveOrUpdate(task);
		
		verify(session).beginTransaction();
		verify(session).saveOrUpdate(task);
		
		verify(transaction).commit();
	}
}
