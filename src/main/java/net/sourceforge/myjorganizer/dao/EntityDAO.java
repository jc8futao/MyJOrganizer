package net.sourceforge.myjorganizer.dao;


/**
 * <p>EntityDAO interface.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public interface EntityDAO<T> {
    /**
     * <p>find</p>
     *
     * @param primaryKey a {@link java.lang.Object} object.
     * @param <T> a T object.
     * @return a T object.
     */
    public T find(Object primaryKey);

    /**
     * <p>persist</p>
     *
     * @param entity a T object.
     */
    public void persist(T entity);

    /**
     * <p>persistMany</p>
     *
     * @param entities a {@link java.lang.Iterable} object.
     */
    public void persistMany(Iterable<T> entities);
    
    public void detachMany(Iterable<T> entities);
    
    public void detach(T entity);
    
    public void refreshMany(Iterable<T> entities);

    /**
     * <p>merge</p>
     *
     * @param entity a T object.
     * @return a T object.
     */
    public T merge(T entity);

    /**
     * <p>mergeMany</p>
     *
     * @param entities a {@link java.lang.Iterable} object.
     * @return a {@link java.lang.Iterable} object.
     */
    public Iterable<T> mergeMany(Iterable<T> entities);

    /**
     * <p>remove</p>
     *
     * @param entity a T object.
     */
    public void remove(T entity);
}
