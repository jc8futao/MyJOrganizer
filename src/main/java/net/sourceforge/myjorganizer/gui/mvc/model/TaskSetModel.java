package net.sourceforge.myjorganizer.gui.mvc.model;

import java.util.Collection;
import java.util.Observable;

import org.hibernate.Transaction;

import net.sourceforge.myjorganizer.dao.HibernateDao;
import net.sourceforge.myjorganizer.data.Task;

public class TaskSetModel extends Observable {

	private HibernateDao<Integer, Task> dao;
	private Collection<Task> taskList;

	public TaskSetModel(HibernateDao<Integer, Task> dao) {
		this.dao = dao;

		// TODO verificare lock in lettura
		Transaction tx = dao.getSession().beginTransaction();
		this.taskList = dao.findAll();
		tx.commit();
	}

	public int add(Task task) {
		Transaction tx = dao.getSession().beginTransaction();

		int id = dao.create(task);

		taskList.add(task);

		tx.commit();

		setChanged();
		notifyObservers();

		return id;
	}

	public void update(Task task) {
		Transaction tx = dao.getSession().beginTransaction();

		dao.update(task);

		taskList.add(task);

		// TODO e se fosse un clone?

		tx.commit();

		setChanged();
		notifyObservers();
	}

	public void delete(Task task) {
		Transaction tx = dao.getSession().beginTransaction();

		dao.delete(task);
		taskList.remove(task);

		tx.commit();

		setChanged();
		notifyObservers();
	}

	public Collection<Task> getList() {
		return taskList;
	}
}
