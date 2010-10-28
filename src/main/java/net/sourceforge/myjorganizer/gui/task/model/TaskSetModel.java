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

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import net.sourceforge.myjorganizer.jpa.entities.Task;

/**
 * <p>TaskSetModel class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskSetModel extends ObservableEntityModel {

    private Collection<Task> taskList;

    /**
     * <p>Constructor for TaskSetModel.</p>
     *
     * @param entityManager a {@link javax.persistence.EntityManager} object.
     */
    public TaskSetModel(EntityManager entityManager) {
        super(entityManager);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        this.taskList = entityManager.createQuery("FROM Task", Task.class)
                .getResultList();
        tx.commit();
    }

    /**
     * <p>add</p>
     *
     * @param task a {@link net.sourceforge.myjorganizer.jpa.entities.Task} object.
     * @return a int.
     */
    public int add(Task task) {
        EntityTransaction tx = beginTransaction();

        getEntityManager().persist(task);
        taskList.add(task);

        commitAndNotify(tx);

        return task.getId();
    }

    /**
     * <p>update</p>
     *
     * @param task a {@link net.sourceforge.myjorganizer.jpa.entities.Task} object.
     */
    public void update(Task task) {
        EntityTransaction tx = beginTransaction();

        getEntityManager().merge(task);

        commitAndNotify(tx);
    }

    /**
     * <p>updateMany</p>
     *
     * @param tasks a {@link java.lang.Iterable} object.
     */
    public void updateMany(Iterable<Task> tasks) {
        EntityTransaction tx = beginTransaction();

        for (Task task : tasks) {
            getEntityManager().merge(task);
        }

        commitAndNotify(tx);
    }

    /**
     * <p>delete</p>
     *
     * @param task a {@link net.sourceforge.myjorganizer.jpa.entities.Task} object.
     */
    public void delete(Task task) {
        EntityTransaction tx = beginTransaction();

        getEntityManager().remove(task);
        taskList.remove(task);

        commitAndNotify(tx);
    }

    /**
     * <p>getList</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Task> getList() {
        return taskList;
    }

    /**
     * <p>addMany</p>
     *
     * @param tasks a {@link java.lang.Iterable} object.
     */
    public void addMany(Iterable<Task> tasks) {
        EntityTransaction tx = beginTransaction();

        for (Task task : tasks) {
            getEntityManager().persist(task);
            taskList.add(task);
        }

        commitAndNotify(tx);
    }
}
