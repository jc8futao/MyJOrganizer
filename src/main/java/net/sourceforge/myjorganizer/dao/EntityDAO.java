package net.sourceforge.myjorganizer.dao;


public interface EntityDAO<T> {
    public T find(Object primaryKey);

    public void persist(T entity);

    public void persistMany(Iterable<T> entities);

    public T merge(T entity);

    public Iterable<T> mergeMany(Iterable<T> entities);

    public void remove(T entity);
}
