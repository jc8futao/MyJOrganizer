package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.dao.TaskDAO;
import net.sourceforge.myjorganizer.jpa.entities.Task;

/**
 * <p>JPATaskDAO class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class JPATaskDAO extends JPAEntityDAO<Task> implements TaskDAO {

    /**
     * <p>Constructor for JPATaskDAO.</p>
     *
     * @param entityManager a {@link javax.persistence.EntityManager} object.
     */
    public JPATaskDAO(EntityManager entityManager) {
        super(entityManager, Task.class);
    }

    /** {@inheritDoc} */
    @Override
    public boolean delete(String id) {
        return getEntityManager().createQuery("DELETE FROM Task WHERE id=?").setParameter(1, id).executeUpdate() > 0;        
    }
}
