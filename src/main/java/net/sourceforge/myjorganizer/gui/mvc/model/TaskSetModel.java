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

package net.sourceforge.myjorganizer.gui.mvc.model;

import java.util.Collection;
import java.util.Observable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import net.sourceforge.myjorganizer.data.Task;

public class TaskSetModel extends Observable {

	private Collection<Task> taskList;
	private EntityManager entityManager;

	public TaskSetModel(EntityManager entityManager) {
		this.entityManager = entityManager;

		// TODO verificare lock in lettura
		EntityTransaction tx = entityManager.getTransaction();
		this.taskList = entityManager.createQuery("FROM Task", Task.class).getResultList();
		tx.commit();
	}

	public int add(Task task) {
		EntityTransaction tx = entityManager.getTransaction();

		entityManager.persist(task);

		taskList.add(task);

		tx.commit();

		setChanged();
		notifyObservers();

		return task.getId();
	}

	public void update(Task task) {
		EntityTransaction tx = entityManager.getTransaction();

		entityManager.merge(task);

		taskList.add(task);

		// TODO e se fosse un clone?

		tx.commit();

		setChanged();
		notifyObservers();
	}

	public void delete(Task task) {
		EntityTransaction tx = entityManager.getTransaction();
		
		entityManager.remove(task);
		taskList.remove(task);

		tx.commit();

		setChanged();
		notifyObservers();
	}

	public Collection<Task> getList() {
		return taskList;
	}
}
