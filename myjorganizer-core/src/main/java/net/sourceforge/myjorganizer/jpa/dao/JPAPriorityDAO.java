package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.dao.PriorityDAO;
import net.sourceforge.myjorganizer.jpa.entities.TaskPriority;

/**
 * <p>
 * PriorityDAO class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class JPAPriorityDAO extends JPAEntityDAO<TaskPriority> implements
		PriorityDAO {
	/**
	 * <p>
	 * Constructor for PriorityDAO.
	 * </p>
	 * 
	 * @param entityManager
	 *            a {@link javax.persistence.EntityManager} object.
	 */
	public JPAPriorityDAO(EntityManager entityManager) {
		super(entityManager, TaskPriority.class);
	}
}
