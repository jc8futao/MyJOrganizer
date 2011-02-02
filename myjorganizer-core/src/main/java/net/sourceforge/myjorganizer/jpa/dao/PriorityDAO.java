package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.jpa.entities.TaskPriority;

/**
 * <p>PriorityDAO class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class PriorityDAO extends JPAEntityDAO<TaskPriority> {
    /**
     * <p>Constructor for PriorityDAO.</p>
     *
     * @param entityManager a {@link javax.persistence.EntityManager} object.
     */
    public PriorityDAO(EntityManager entityManager) {
        super(entityManager, TaskPriority.class);
    }
}