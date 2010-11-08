package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

    public void deleteFromId(String left, String right) {
        final String ql = "DELETE FROM TaskDependency WHERE left = ? AND right = ?";
        Query query = getEntityManager().createQuery(ql);
        query.setParameter(1, left);
        query.setParameter(2, right);
        query.executeUpdate();
    }
}
