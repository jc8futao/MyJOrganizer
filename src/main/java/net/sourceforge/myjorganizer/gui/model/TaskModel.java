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

import java.util.Date;

import javax.swing.event.EventListenerList;

import net.sourceforge.myjorganizer.data.Priority;
import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.data.TaskStatus;

public class TaskModel {

	private final Task task;
	private final EventListenerList listenerList = new EventListenerList();

	public TaskModel(Task task) {
		this.task = task;
	}

	public TaskModel() {
		this(new Task());
	}

	public Priority getPriority() {
		return this.task.getPriority();
	}

	public void setPriority(Priority priority) {
		if (getPriority().equals(priority))
			return;

		this.task.setPriority(priority);

		fireStatusChanged(this, "priority");
	}

	public void addTaskModelListener(TaskDataListener listener) {
		listenerList.add(TaskDataListener.class, listener);
	}

	protected void fireStatusChanged(Object source, String property) {
		Object[] listeners = listenerList.getListenerList();
		TaskDataEvent e = null;

		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TaskDataListener.class) {
				if (e == null) {
					e = new TaskDataEvent(source, property);
				}
				((TaskDataListener) listeners[i + 1]).contentChanged(e);
			}
		}
	}

	public TaskModel setName(String name) {
		if (name == null || !name.equals(task.getName())) {
			task.setName(name);
			fireStatusChanged(this, "name");
		}

		return this;
	}

	public String getName() {
		return task.getName();
	}

	public TaskModel setStartDate(Date startDate) {
		if (startDate == null || !startDate.equals(task.getStartDate())) {
			task.setStartDate(startDate);
			fireStatusChanged(this, "startDate");
		}

		return this;
	}

	public Date getStartDate() {
		return task.getStartDate();
	}

	public TaskModel setDueDate(Date dueDate) {
		if (dueDate == null || !dueDate.equals(task.getDueDate())) {
			task.setDueDate(dueDate);
			fireStatusChanged(this, "dueDate");
		}

		return this;
	}

	public Date getDueDate() {
		return task.getDueDate();
	}

	public TaskModel setStatus(TaskStatus status) {
		if (status == null || !status.equals(task.getStatus())) {
			task.setStatus(status);

			fireStatusChanged(this, "status");
		}

		return this;
	}

	public TaskStatus getStatus() {
		return task.getStatus();
	}

	public int getId() {
		return task.getId();
	}

	public TaskModel setCompletion(int completion) {
		if (completion != task.getCompletion()) {
			task.setCompletion(completion);
			fireStatusChanged(this, "completion");
		}

		return this;
	}

	public int getCompletion() {
		return task.getCompletion();
	}

	@Override
	public String toString() {
		return task.toString();
	}
}
