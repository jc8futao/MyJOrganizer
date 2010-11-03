/*
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

package net.sourceforge.myjorganizer.gui.task.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import net.sourceforge.myjorganizer.jpa.dao.JPATaskDAO;
import net.sourceforge.myjorganizer.jpa.entities.Task;

/**
 * <p>
 * TaskSetModel class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskSetModel extends ObservableEntityModel<Task> {
    /**
     * <p>
     * Constructor for TaskSetModel.
     * </p>
     * 
     * @param entityManager
     *            a {@link javax.persistence.EntityManager} object.
     */
    public TaskSetModel(EntityManager entityManager) {
        super(entityManager, new JPATaskDAO(entityManager));

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        this.setList(entityManager.createQuery("FROM Task", Task.class)
                .getResultList());
        tx.commit();
    }

    public void delete(String id) {
        EntityTransaction tx = beginTransaction();
        Task task = getDao().find(id);

        if (task == null) {
            tx.rollback();
            throw new PersistenceException("Task " + id + " not found");
        }

        getList().remove(task);
        getEntityManager().remove(task);

        commitAndNotify(tx);
    }

    public void markAsDone(String id) {
        EntityTransaction tx = beginTransaction();

        Task task = getDao().find(id);
        if (task != null) {
            task.setCompletion(100);

            commitAndNotify(tx);
        } else {
            tx.rollback();
        }
    }
}
