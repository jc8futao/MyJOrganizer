package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;

/**
 * <p>JPATaskDependencyDAO class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class JPATaskDependencyDAO extends JPAEntityDAO<TaskDependency> {

    /**
     * <p>Constructor for JPATaskDependencyDAO.</p>
     *
     * @param entityManager a {@link javax.persistence.EntityManager} object.
     * @param class1 a {@link java.lang.Class} object.
     */
    public JPATaskDependencyDAO(EntityManager entityManager,
            Class<TaskDependency> class1) {
        super(entityManager, class1);
        // TODO Auto-generated constructor stub
    }
}
