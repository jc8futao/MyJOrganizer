package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.dao.TaskStatusDAO;
import net.sourceforge.myjorganizer.jpa.entities.TaskStatus;

/**
 * <p>
 * TaskStatusDAO class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class JPATaskStatusDAO extends JPAEntityDAO<TaskStatus> implements
		TaskStatusDAO {

	/**
	 * <p>
	 * Constructor for TaskStatusDAO.
	 * </p>
	 * 
	 * @param entityManager
	 *            a {@link javax.persistence.EntityManager} object.
	 */
	public JPATaskStatusDAO(EntityManager entityManager) {
		super(entityManager, TaskStatus.class);
	}
}
