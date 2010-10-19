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

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.AbstractListModel;

/**
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 */
public class TaskListModel extends AbstractListModel {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -9045001700570860047L;
	
	private final ArrayList<TaskModel> collection;

	public TaskListModel(Collection<TaskModel> collection) {
		this.collection = new ArrayList<TaskModel>(collection);
	}

	public TaskListModel() {
		this.collection = new ArrayList<TaskModel>();
	}

	@Override
	public int getSize() {
		return collection.size();
	}

	@Override
	public Object getElementAt(int index) {
		return collection.get(index);
	}

	public boolean contains(TaskModel t) {
		return collection.contains(t);
	}

	public void add(TaskModel task) {
		if (collection.contains(task))
			return;

		int newIndex = collection.size();

		collection.add(task);

		notifyAdd(newIndex, newIndex);
	}

	private void notifyAdd(int index0, int index1) {
		fireIntervalAdded(this, index0, index1);
	}

	private void notifyRemove(int index0, int index1) {
		fireIntervalRemoved(this, index0, index1);
	}

	/**
	 * Adds items from the collection to the list
	 * 
	 * @param tasks
	 */
	public void add(Collection<TaskModel> tasks) {
		int newIndex = collection.size();

		for (TaskModel task : tasks) {
			if (!collection.contains(task)) {
				add(task);
			}
		}

		if (collection.size() > newIndex) {
			notifyAdd(newIndex, collection.size() - 1);
		}
	}

	/**
	 * Removes a task from the model
	 * 
	 * @param task a task
	 */
	public void remove(TaskModel task) {
		int index = collection.indexOf(task);
		collection.remove(task);

		notifyRemove(index, index);
	}
}
