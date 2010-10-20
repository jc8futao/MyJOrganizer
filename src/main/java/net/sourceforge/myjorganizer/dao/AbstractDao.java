package net.sourceforge.myjorganizer.dao;

import java.io.Serializable;
import java.util.Collection;

public interface AbstractDao<ID extends Serializable, T> {
	public ID create(T entity);
	public T retrieve(ID id);

	public void update(T entity);

	public void delete(T entity);
	
	public Collection<T> findAll();
}
