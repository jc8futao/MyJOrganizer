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
import net.sourceforge.myjorganizer.jpa.entities.TaskStatus;

/**
 * <p>TaskStatusModel class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskStatusModel extends ObservableEntityModel {

    private Collection<TaskStatus> taskStatusList;

    /**
     * <p>Constructor for TaskStatusModel.</p>
     *
     * @param entityManager a {@link javax.persistence.EntityManager} object.
     */
    public TaskStatusModel(EntityManager entityManager) {
        super(entityManager);

        EntityTransaction tx = beginTransaction();

        this.taskStatusList = entityManager.createQuery("FROM TaskStatus",
                TaskStatus.class).getResultList();
        tx.commit();
    }

    /**
     * <p>add</p>
     *
     * @param taskStatus a {@link net.sourceforge.myjorganizer.jpa.entities.TaskStatus} object.
     * @return a int.
     */
    public int add(TaskStatus taskStatus) {
        EntityTransaction tx = beginTransaction();

        getEntityManager().persist(taskStatus);
        taskStatusList.add(taskStatus);

        commitAndNotify(tx);

        return taskStatus.getId();
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
        taskStatusList.remove(task);

        commitAndNotify(tx);
    }

    /**
     * <p>getList</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<TaskStatus> getList() {
        return taskStatusList;
    }

    /**
     * <p>addMany</p>
     *
     * @param tasks a {@link java.lang.Iterable} object.
     */
    public void addMany(Iterable<TaskStatus> tasks) {
        EntityTransaction tx = beginTransaction();

        for (TaskStatus task : tasks) {
            getEntityManager().persist(task);
            taskStatusList.add(task);
        }

        commitAndNotify(tx);
    }
}
