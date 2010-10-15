package net.sourceforge.myjorganizer.gui.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import net.sourceforge.myjorganizer.data.Priority;

import org.junit.Before;
import org.junit.Test;

public class TaskSquaredPanelModelTest {
	private TaskSquaredPanelModel squaredPanelModel;

	@Before
	public void before() {
		this.squaredPanelModel = new TaskSquaredPanelModel(Priority.getAll());
	}

	@Test
	public void testGetListModels() {
		Map<Priority, TaskListModel> x = squaredPanelModel.getListModels();

		assertEquals(Priority.getAll().size(), x.size());
	}

	@Test
	public void testAddTaskModel() {
		TaskModel task = new TaskModel();
		squaredPanelModel.add(task);

		assertTrue(squaredPanelModel.contains(task));
	}
	
	@Test
	public void testNotContains()
	{
		assertFalse(squaredPanelModel.contains(new TaskModel()));
	}

}
