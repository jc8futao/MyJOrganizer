package net.sourceforge.myjorganizer.gui.mvc.model;

import java.util.List;

import javax.swing.AbstractListModel;

import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class HibernateModel<T> {
	private Session session;

	public HibernateModel(Session session) {
		this.session = session;
	}

	public void saveOrUpdate(T entity) {
		Transaction transaction = getSession().beginTransaction();

		getSession().saveOrUpdate(entity);

		transaction.commit();
		
		dirty();
	}

	public abstract int size();
	protected abstract void dirty();

	public AbstractListModel getListModel() {
		return new ListModel(this);
	}
	
	protected abstract List getList();

	protected Session getSession() {
		return this.session;
	}

	
	private class ListModel extends AbstractListModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2135054280601227252L;
		private HibernateModel<T> model;

		public ListModel(HibernateModel<T> model) {
			this.model = model;
			
			
		}

		@Override
		public Object getElementAt(int index) {
			// TODO Auto-generated method stub
			return model.getList().get(index);
		}

		@Override
		public int getSize() {
			return model.getList().size();
		}

	}
}
