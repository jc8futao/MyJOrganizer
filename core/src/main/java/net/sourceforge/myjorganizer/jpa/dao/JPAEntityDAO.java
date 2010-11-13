package net.sourceforge.myjorganizer.jpa.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.dao.EntityDAO;

/**
 * <p>
 * JPAEntityDAO class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class JPAEntityDAO<T> implements EntityDAO<T> {

    private final EntityManager entityManager;
    private final Class<T> class1;

    /**
     * <p>
     * Constructor for JPAEntityDAO.
     * </p>
     * 
     * @param entityManager
     *            a {@link javax.persistence.EntityManager} object.
     * @param class1
     *            a {@link java.lang.Class} object.
     * @param <T>
     *            a T object.
     */
    public JPAEntityDAO(EntityManager entityManager, Class<T> class1) {
        this.entityManager = entityManager;
        this.class1 = class1;
    }

    /** {@inheritDoc} */
    @Override
    public T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    /**
     * <p>
     * Getter for the field <code>entityManager</code>.
     * </p>
     * 
     * @return a {@link javax.persistence.EntityManager} object.
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /** {@inheritDoc} */
    @Override
    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    /** {@inheritDoc} */
    @Override
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    /** {@inheritDoc} */
    @Override
    public void persistMany(Iterable<T> entities) {
        for (T entity : entities) {
            persist(entity);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Iterable<T> mergeMany(Iterable<T> entities) {
        ArrayList<T> merged = new ArrayList<T>();

        for (T entity : entities) {
            merged.add(merge(entity));
        }

        return merged;
    }

    /** {@inheritDoc} */
    @Override
    public T find(Object primaryKey) {
        return getEntityManager().find(class1, primaryKey);
    }

    public void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    @Override
    public void detachMany(Iterable<T> entities) {
        for (T entity : entities)
            detach(entity);
    }

    @Override
    public void refreshMany(Iterable<T> entities) {
        for (T entity : entities)
            refresh(entity);
    }

    @Override
    public void detach(T entity) {
        getEntityManager().detach(entity);
    }
}
