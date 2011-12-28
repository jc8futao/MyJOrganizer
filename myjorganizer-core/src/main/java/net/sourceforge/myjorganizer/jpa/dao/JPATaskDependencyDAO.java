package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import net.sourceforge.myjorganizer.dao.TaskDependencyDAO;
import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;

/**
 * <p>
 * JPATaskDependencyDAO class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id: JPATaskDependencyDAO.java 142 2010-11-08 15:34:36Z dbellettini
 *          $
 */
public class JPATaskDependencyDAO extends JPAEntityDAO<TaskDependency>
		implements TaskDependencyDAO {

	/**
	 * <p>
	 * Constructor for JPATaskDependencyDAO.
	 * </p>
	 * 
	 * @param entityManager
	 *            a {@link javax.persistence.EntityManager} object.
	 * @param class1
	 *            a {@link java.lang.Class} object.
	 */
	public JPATaskDependencyDAO(EntityManager entityManager,
			Class<TaskDependency> class1) {
		super(entityManager, class1);
	}

	public TaskDependency findFromId(String left, String right) {
		final String ql = "FROM TaskDependency WHERE left_id = ? AND right_id = ?";

		TypedQuery<TaskDependency> query = getEntityManager().createQuery(ql,
				TaskDependency.class);
		query.setParameter(1, left);
		query.setParameter(2, right);

		return query.getSingleResult();
	}
}
