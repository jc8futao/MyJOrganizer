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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.myjorganizer.data.Priority;

public class TaskSquaredPanelModel {
	private final Map<Priority, TaskListModel> listModels = new HashMap<Priority, TaskListModel>();

	public TaskSquaredPanelModel(Collection<Priority> priorities) {
		for (Priority priority : priorities) {
			listModels.put(priority, new TaskListModel());
		}
	}

	/**
	 * @warning use this method only for constructing JList
	 * @return
	 */
	public Map<Priority, TaskListModel> getListModels() {
		return listModels;
	}

	public void add(TaskModel task) {
		TaskListModel model = listModels.get(task.getPriority());
		model.add(task);
	}

	/**
	 * returns true if the model contains the task
	 * 
	 * @param task
	 * @return
	 */
	public boolean contains(TaskModel task) {
		for (TaskListModel lmodel : listModels.values()) {
			if (lmodel.contains(task))
				return true;
		}

		return false;
	}
}
