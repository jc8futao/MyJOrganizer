package net.sourceforge.myjorganizer.gui.mvc.view;

import javax.swing.JPanel;

import net.sourceforge.myjorganizer.gui.mvc.controller.TaskPanelListener;
import net.sourceforge.myjorganizer.gui.mvc.model.TaskModel;

public abstract class TaskPanelView extends JPanel {

	private TaskModel model;

	public TaskPanelView(TaskModel taskModel) {
		// TODO Auto-generated constructor stub
	}

	public void addTaskPanelListener(TaskPanelListener listener) {
		// TODO Auto-generated constructor stub
	}

	protected TaskModel getModel() {
		return this.model;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6151994177502944084L;

}
