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
import java.util.List;
import java.util.Observable;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import net.sourceforge.myjorganizer.jpa.dao.JPAEntityDAO;

/**
 * <p>
 * Abstract ObservableEntityModel class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id: ObservableEntityModel.java 106 2010-10-28 08:34:07Z
 *          dbellettini$
 */
public abstract class ObservableEntityModel<T> extends Observable {

    private final EntityManager entityManager;
    private Collection<T> list;
    private final JPAEntityDAO<T> dao;
    private Validator validator;

    /**
     * <p>
     * Constructor for ObservableEntityModel.
     * </p>
     * 
     * @param entityManager
     *            a {@link javax.persistence.EntityManager} object.
     * @param dao
     *            a {@link net.sourceforge.myjorganizer.jpa.dao.JPAEntityDAO}
     *            object.
     * @param <T>
     *            a T object.
     */
    protected ObservableEntityModel(EntityManager entityManager,
            JPAEntityDAO<T> dao) {
        this.entityManager = entityManager;
        this.dao = dao;
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

    /**
     * <p>
     * update
     * </p>
     * 
     * @param entity
     *            a T object.
     */
    public void update(T entity) {
        ensureIsValid(entity);
        
        EntityTransaction tx = beginTransaction();

        try {
            getDao().merge(entity);
        } catch (PersistenceException e) {
            tx.rollback();

            throw e;
        }
        try {
            commitAndNotify(tx);
        } catch (RollbackException e) {
            tx = beginTransaction();

            getDao().refresh(entity);

            commitAndNotify(tx);

            throw e;
        }
    }

    /**
     * <p>
     * getList
     * </p>
     * 
     * @return a {@link java.util.Collection} object.
     */
    public Collection<T> getList() {
        return list;
    }

    /**
     * <p>
     * delete
     * </p>
     * 
     * @param entity
     *            a T object.
     */
    public void delete(T entity) {
        EntityTransaction tx = beginTransaction();

        try {
            getDao().remove(entity);
            list.remove(entity);
        } catch (PersistenceException e) {
            tx.rollback();
            throw e;
        }

        commitAndNotify(tx);
    }

    /**
     * <p>
     * add
     * </p>
     * 
     * @param entity
     *            a T object.
     */
    public void add(T entity) {
        ensureIsValid(entity);
        
        EntityTransaction tx = beginTransaction();

        try {
            getEntityManager().persist(entity);
            list.add(entity);
        } catch (PersistenceException e) {
            tx.rollback();

            throw e;
        }

        try {
            commitAndNotify(tx);
        } catch (RollbackException e) {
            getEntityManager().detach(entity);

            throw e;
        }
    }

    /**
     * <p>
     * updateMany
     * </p>
     * 
     * @param entities
     *            a {@link java.lang.Iterable} object.
     */
    public void updateMany(Iterable<T> entities) {
        ensureIsValid(entities);
        
        EntityTransaction tx = beginTransaction();
        try {
            getDao().mergeMany(entities);
        } catch (PersistenceException e) {
            tx.rollback();
            throw e;
        }

        commitAndNotify(tx);
    }

    /**
     * <p>
     * addMany
     * </p>
     * 
     * @param entities
     *            a {@link java.lang.Iterable} object.
     */
    public void addMany(Iterable<T> entities) {
        ensureIsValid(entities);
        EntityTransaction tx = beginTransaction();

        try {
            getDao().persistMany(entities);
            for (T entity : entities) {
                getList().add(entity);
            }
        } catch (PersistenceException e) {
            tx.rollback();
            throw e;
        }

        commitAndNotify(tx);
    }

    /**
     * <p>
     * Getter for the field <code>dao</code>.
     * </p>
     * 
     * @return a {@link net.sourceforge.myjorganizer.jpa.dao.JPAEntityDAO}
     *         object.
     */
    public JPAEntityDAO<T> getDao() {
        return dao;
    }

    /**
     * <p>
     * beginTransaction
     * </p>
     * 
     * @return a {@link javax.persistence.EntityTransaction} object.
     */
    protected EntityTransaction beginTransaction() {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        return tx;
    }

    /**
     * <p>
     * commitAndNotify
     * </p>
     * 
     * @param tx
     *            a {@link javax.persistence.EntityTransaction} object.
     */
    protected void commitAndNotify(EntityTransaction tx) {
        tx.commit();

        setChanged();
        notifyObservers();
    }

    /**
     * <p>
     * rawDelete
     * </p>
     * 
     * @param entity
     *            a T object.
     */
    protected void rawDelete(T entity) {
        getDao().remove(entity);
        getList().remove(entity);
    }

    /**
     * <p>
     * Setter for the field <code>list</code>.
     * </p>
     * 
     * @param list
     *            a {@link java.util.List} object.
     */
    protected void setList(List<T> list) {
        this.list = list;
    }

    protected Validator getValidator() {

        if (this.validator == null)
            this.validator = Validation.buildDefaultValidatorFactory()
                    .getValidator();

        return this.validator;
    }

    protected void ensureIsValid(T entity) {
        Set<ConstraintViolation<T>> violations = getValidator().validate(
                entity);
    
        if (violations.size() > 0) {
            throw new ValidationException("Validation failed");
        }
    }

    protected void ensureIsValid(Iterable<T> entities) {
        for (T entity : entities)
            ensureIsValid(entity);
    }
}
