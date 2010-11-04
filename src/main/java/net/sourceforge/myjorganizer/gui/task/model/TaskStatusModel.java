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

import net.sourceforge.myjorganizer.jpa.dao.TaskStatusDAO;
import net.sourceforge.myjorganizer.jpa.entities.TaskStatus;

/**
 * <p>
 * TaskStatusModel class.
 * </p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskStatusModel extends ObservableEntityModel<TaskStatus> {

    /**
     * <p>
     * Constructor for TaskStatusModel.
     * </p>
     *
     * @param entityManager
     *            a {@link javax.persistence.EntityManager} object.
     */
    public TaskStatusModel(EntityManager entityManager) {
        super(entityManager, new TaskStatusDAO(entityManager));

        EntityTransaction tx = beginTransaction();

        this.setList(entityManager.createQuery("FROM TaskStatus",
                TaskStatus.class).getResultList());
        tx.commit();
    }

    /**
     * Gets a Task Status by id
     *
     * @param id a {@link java.lang.String} object.
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.TaskStatus} object.
     */
    public TaskStatus getById(String id) {
        EntityTransaction tx = beginTransaction();
        TaskStatus status = getEntityManager().find(TaskStatus.class, id);

        if (status == null) {
            status = new TaskStatus(id);
            getEntityManager().persist(status);

            getList().add(status);
            commitAndNotify(tx);
        } else {
            tx.commit();
        }

        return status;
    }
}
