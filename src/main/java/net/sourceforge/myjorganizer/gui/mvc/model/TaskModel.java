package net.sourceforge.myjorganizer.gui.mvc.model;

import java.util.List;

import org.hibernate.Session;

import net.sourceforge.myjorganizer.data.Task;

public class TaskModel extends HibernateModel<Task> {
	private List list;

	public TaskModel(Session session) {
		super(session);
	}

	public int size() {
		// TODO Auto-generated method stub
		return getList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected synchronized List getList() {
		if (this.list == null) {
			this.list = getSession().createCriteria(Task.class).list();
		}
		
		return this.list;
	}

	@Override
	protected void dirty() {
		this.list = null;

	}
}