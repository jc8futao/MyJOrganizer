package net.sourceforge.myjorganizer.gui.mvc.controller;

import javax.swing.JTabbedPane;

import net.sourceforge.myjorganizer.dao.HibernateDao;
import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.gui.mvc.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.mvc.view.TaskJListView;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import org.hibernate.Session;

public class TaskController {
	private TaskSetModel taskSetModel;
	private HibernateDao<Integer, Task> taskDao;
	private TaskJListView jListView;

	public TaskController(Session session, JTabbedPane pane) {
		this.taskDao = new HibernateDao<Integer, Task>(Task.class);
		taskDao.setSession(session);
		this.taskSetModel = new TaskSetModel(taskDao);

		jListView = new TaskJListView();
		taskSetModel.addObserver(jListView);
		
		jListView.setListData(taskSetModel.getList().toArray());
		pane.add(jListView);

		int i = 0;
		pane.setTitleAt(i++, _("TASK_LIST"));
	}
}
