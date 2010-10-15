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
		// TODO Auto-generated method stub
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
