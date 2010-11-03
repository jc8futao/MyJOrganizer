package net.sourceforge.myjorganizer.gui.task.model;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.jpa.dao.JPATaskDependencyDAO;
import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;

/**
 * <p>TaskDependencyModel class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskDependencyModel extends ObservableEntityModel<TaskDependency> {

    /**
     * <p>Constructor for TaskDependencyModel.</p>
     *
     * @param entityManager a {@link javax.persistence.EntityManager} object.
     */
    public TaskDependencyModel(EntityManager entityManager) {
        super(entityManager, new JPATaskDependencyDAO(entityManager,
                TaskDependency.class));

        this.setList(entityManager.createQuery("FROM TaskDependency",
                TaskDependency.class).getResultList());
    }
}
