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
