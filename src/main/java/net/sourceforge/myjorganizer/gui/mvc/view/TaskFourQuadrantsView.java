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

package net.sourceforge.myjorganizer.gui.mvc.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;

import javax.swing.JList;
import javax.swing.JScrollPane;

import net.sourceforge.myjorganizer.data.Priority;
import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.gui.mvc.model.TaskSetModel;

public class TaskFourQuadrantsView extends AbstractTaskView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1944351748661553463L;
	HashMap<Priority, JList> lists = new HashMap<Priority, JList>();
	HashMap<Priority, List<Task>> listData = new HashMap<Priority, List<Task>>();

	public TaskFourQuadrantsView() {
		Collection<Priority> priorities = Priority.getAll();

		int cols = priorities.size() / 2 + priorities.size() % 2;
		setLayout(new GridLayout(cols, 2));

		for (Priority priority : priorities) {
			JList currentList = new JList();
			lists.put(priority, currentList);
			listData.put(priority, new ArrayList<Task>());

			add(new JScrollPane(currentList));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		TaskSetModel model = (TaskSetModel) o;

		for (List<Task> tasks : listData.values()) {
			tasks.clear();
		}

		for (Task task : model.getList()) {
			listData.get(task.getPriority()).add(task);
		}

		for (Entry<Priority, List<Task>> entry : listData.entrySet()) {
			lists.get(entry.getKey()).setListData(entry.getValue().toArray());
		}
	}
}
