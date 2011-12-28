package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

import net.sourceforge.myjorganizer.dao.TaskPriorityDAO;
import net.sourceforge.myjorganizer.jpa.entities.TaskPriority;

/**
 * <p>
 * PriorityDAO class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class JPATaskPriorityDAO extends JPAEntityDAO<TaskPriority> implements
		TaskPriorityDAO {
	/**
	 * <p>
	 * Constructor for PriorityDAO.
	 * </p>
	 * 
	 * @param entityManager
	 *            a {@link javax.persistence.EntityManager} object.
	 */
	@Inject
	public JPATaskPriorityDAO(EntityManager entityManager) {
		super(entityManager, TaskPriority.class);
	}
}
