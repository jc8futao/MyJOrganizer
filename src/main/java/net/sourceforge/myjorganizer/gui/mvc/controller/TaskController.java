package net.sourceforge.myjorganizer.gui.mvc.controller;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import javax.swing.JTabbedPane;

import net.sourceforge.myjorganizer.data.HibernateUtil;
import net.sourceforge.myjorganizer.gui.mvc.model.TaskModel;
import net.sourceforge.myjorganizer.gui.mvc.view.TaskListPanelView;
import net.sourceforge.myjorganizer.gui.mvc.view.TaskSourcePanelView;
import net.sourceforge.myjorganizer.gui.mvc.view.TaskSquaredPanelView;

public class TaskController implements TaskPanelListener {

	private TaskListPanelView taskListPanelView;
	private TaskSquaredPanelView taskSquaredPanelView;
	private TaskSourcePanelView taskSourcePanelView;
	private TaskModel taskModel;

	public TaskController(JTabbedPane tabbedPane) {
		this.taskModel = new TaskModel(HibernateUtil.getSession());
		
		tabbedPane.add(this.taskListPanelView = new TaskListPanelView(taskModel));
		tabbedPane.add(this.taskSquaredPanelView = new TaskSquaredPanelView(taskModel));
		tabbedPane.add(this.taskSourcePanelView = new TaskSourcePanelView(taskModel));

		int i = 0;
		tabbedPane.setTitleAt(i++, _("TASK_LIST"));
		tabbedPane.setTitleAt(i++, _("TASK_SQUARED"));
		tabbedPane.setTitleAt(i++, _("TASK_SOURCE"));
	}

}
