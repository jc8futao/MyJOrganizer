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
