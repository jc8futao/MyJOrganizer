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

package net.sourceforge.myjorganizer.gui.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaskListModelTest {
	private TaskListModel taskListModel;
	private ArrayList<TaskModel> collection;

	@Before
	public void before() {
		this.collection = new ArrayList<TaskModel>();
		this.taskListModel = new TaskListModel(collection);
	}

	@Test
	public void testImplementsListModel() {
		assertTrue("taskListModel instanceof ListModel",
				taskListModel instanceof ListModel);
	}

	@Test
	public void testSize() {
		assertEquals(0, taskListModel.getSize());
		taskListModel.add(new TaskModel());
		assertEquals(1, taskListModel.getSize());
	}

	@Test
	public void testGetElementAt() {
		TaskModel task = new TaskModel();
		taskListModel.add(task);
		task.setName("Donald Duck");

		assertEquals(task, taskListModel.getElementAt(0));

		task = new TaskModel();
		taskListModel.add(task);
		task.setName("Goofy");

		assertEquals(task, taskListModel.getElementAt(1));
	}

	@Test
	public void testAddContains() {
		TaskModel task = new TaskModel();
		task.setName("Goofy");
		taskListModel.add(task);

		assertTrue("Model contains added task", taskListModel.contains(task));
	}

	@Test
	public void testAddTwice() {
		TaskModel task = new TaskModel();
		task.setName("Goofy");

		taskListModel.add(task);
		taskListModel.add(task);

		assertEquals(1, taskListModel.getSize());
	}

	@Test
	public void testAddTwiceCollection() {
		TaskModel task = new TaskModel();
		task.setName("Goofy");

		Collection<TaskModel> coll = new ArrayList<TaskModel>();
		coll.add(task);
		coll.add(task);

		taskListModel.add(coll);
		assertEquals(1, taskListModel.getSize());
	}

	@Test
	public void testRemoveContains() {
		TaskModel task = new TaskModel();
		task.setName("Goofy");
		taskListModel.add(task);
		taskListModel.remove(task);

		assertFalse("Model doesn't contains remove task",
				taskListModel.contains(task));
	}

	@Test
	public void testAddSendsEvent() {

		ListDataListener[] listeners = new ListDataListener[3];

		for (int i = 0; i < listeners.length; i++) {
			listeners[i] = mock(ListDataListener.class);
			taskListModel.addListDataListener(listeners[i]);
		}

		taskListModel.add(new TaskModel());

		for (int i = 0; i < listeners.length; i++) {

			ArgumentCaptor<ListDataEvent> ag = ArgumentCaptor
					.forClass(ListDataEvent.class);

			verify(listeners[i]).intervalAdded(ag.capture());
			ListDataEvent e = ag.getValue();

			assertEquals(taskListModel, e.getSource());
			assertEquals(0, e.getIndex0());
			assertEquals(0, e.getIndex1());
			assertEquals(ListDataEvent.INTERVAL_ADDED, e.getType());
		}
	}

	@Test
	public void testRemoveSendsEvent() {
		TaskModel task = new TaskModel();
		task.setName("Goofy");
		taskListModel.add(task);

		task = new TaskModel();
		task.setName("Donald Duck");
		taskListModel.add(task);

		ListDataListener[] listeners = new ListDataListener[3];

		for (int i = 0; i < listeners.length; i++) {
			listeners[i] = mock(ListDataListener.class);
			taskListModel.addListDataListener(listeners[i]);
		}

		taskListModel.remove(task);

		for (int i = 0; i < listeners.length; i++) {

			ArgumentCaptor<ListDataEvent> ag = ArgumentCaptor
					.forClass(ListDataEvent.class);

			verify(listeners[i]).intervalRemoved(ag.capture());
			ListDataEvent e = ag.getValue();

			assertEquals(taskListModel, e.getSource());
			assertEquals(1, e.getIndex0());
			assertEquals(1, e.getIndex1());
			assertEquals(ListDataEvent.INTERVAL_REMOVED, e.getType());
		}
	}

	@Test
	public void testAddTwiceNoEvent() {
		TaskModel task = new TaskModel();
		task.setName("Goofy");

		taskListModel.add(task);

		ListDataListener ldl = mock(ListDataListener.class);
		taskListModel.addListDataListener(ldl);

		taskListModel.add(task);

		ArgumentCaptor<ListDataEvent> ag = ArgumentCaptor
				.forClass(ListDataEvent.class);
		verify(ldl, never()).intervalAdded(ag.capture());
	}

	@Test
	public void testAddTwiceCollectionNoEvent() {
		TaskModel task = new TaskModel();
		task.setName("Goofy");

		taskListModel.add(task);

		ArrayList<TaskModel> list = new ArrayList<TaskModel>();
		list.add(task);

		ListDataListener ldl = mock(ListDataListener.class);
		taskListModel.addListDataListener(ldl);
		taskListModel.add(list);

		verifyZeroInteractions(ldl);
	}
}
