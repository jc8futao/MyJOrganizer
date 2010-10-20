package net.sourceforge.myjorganizer.dao;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Session;

public class HibernateDao<ID extends Serializable, T> implements AbstractDao<ID, T> {

	public HibernateDao(Class<T> type) {
		super();
		this.type = type;
	}

	private Session session;
	private Class<T> type;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ID create(T entity) {
		return (ID)getSession().save(entity);
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T retrieve(ID id) {
		return (T)getSession().get(type, id);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> findAll() {
		return getSession().createCriteria(type).list();
	}
}
