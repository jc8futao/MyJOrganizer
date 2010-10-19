package net.sourceforge.myjorganizer.gui.mvc.view;

import javax.swing.JList;
import javax.swing.JScrollPane;

import net.sourceforge.myjorganizer.gui.mvc.model.TaskModel;

public class TaskListPanelView extends TaskPanelView {

	public TaskListPanelView(TaskModel taskModel) {
		super(taskModel);
		
		this.add(new JScrollPane(new JList(taskModel.getListModel())));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6763005515470324316L;

}
