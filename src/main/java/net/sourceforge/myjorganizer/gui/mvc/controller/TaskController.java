package net.sourceforge.myjorganizer.gui.mvc.controller;

import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.JTabbedPane;

import net.sourceforge.myjorganizer.dao.HibernateDao;
import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.gui.mvc.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.mvc.view.TaskJListView;
import net.sourceforge.myjorganizer.gui.mvc.view.TaskSourceView;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import org.hibernate.Session;

public class TaskController {
	private TaskSetModel taskSetModel;
	private HibernateDao<Integer, Task> taskDao;
	private TaskJListView jListView;
	private TaskSourceView sourceView;

	public TaskController(Session session, JTabbedPane pane) {
		this.taskDao = new HibernateDao<Integer, Task>(Task.class);
		taskDao.setSession(session);
		this.taskSetModel = new TaskSetModel(taskDao);

		jListView = new TaskJListView();
		sourceView = new TaskSourceView();
		
		taskSetModel.addObserver(jListView);
		taskSetModel.addObserver(sourceView);
		
		sourceView.update(taskSetModel, null);
		jListView.update(taskSetModel, null);
		
		pane.add(sourceView);
		pane.add(jListView);
		
		int i = 0;
		pane.setTitleAt(i++, _("TASK_SOURCE"));
		pane.setTitleAt(i++, _("TASK_LIST"));
	}
}
