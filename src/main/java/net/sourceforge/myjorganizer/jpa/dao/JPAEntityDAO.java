package net.sourceforge.myjorganizer.jpa.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.dao.EntityDAO;

public class JPAEntityDAO<T> implements EntityDAO<T> {

    private final EntityManager entityManager;
    private final Class<T> class1;
    

    public JPAEntityDAO(EntityManager entityManager, Class<T> class1) {
        this.entityManager = entityManager;
        this.class1 = class1;
    }

    @Override
    public T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void persistMany(Iterable<T> entities) {
        for (T entity : entities) {
            persist(entity);
        }
    }

    @Override
    public Iterable<T> mergeMany(Iterable<T> entities) {
        ArrayList<T> merged = new ArrayList<T>();

        for (T entity : entities) {
            merged.add(merge(entity));
        }

        return merged;
    }

    @Override
    public T find(Object primaryKey) {
        return getEntityManager().find(class1, primaryKey);
    }
}
