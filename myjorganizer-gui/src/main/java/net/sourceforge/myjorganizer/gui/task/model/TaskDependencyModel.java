package net.sourceforge.myjorganizer.gui.task.model;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import net.sourceforge.myjorganizer.jpa.dao.JPATaskDependencyDAO;
import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;

/**
 * <p>
 * TaskDependencyModel class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskDependencyModel extends ObservableEntityModel<TaskDependency> {

	/**
	 * <p>
	 * Constructor for TaskDependencyModel.
	 * </p>
	 * 
	 * @param entityManager
	 *            a {@link javax.persistence.EntityManager} object.
	 */
	public TaskDependencyModel(EntityManager entityManager) {
		super(entityManager, new JPATaskDependencyDAO(entityManager));

		this.setList(entityManager.createQuery("FROM TaskDependency",
				TaskDependency.class).getResultList());
	}

	public void deleteFromId(String left, String right) {
		EntityTransaction tx = beginTransaction();
		try {
			TaskDependency dependency = ((JPATaskDependencyDAO) getDao())
					.findFromId(left, right);
			getEntityManager().remove(dependency);

			Set<TaskDependency> leftdeps = dependency.getLeft()
					.getLeftDependencies();
			if (leftdeps != null)
				leftdeps.remove(dependency);

			Set<TaskDependency> rightdeps = dependency.getRight()
					.getRightDependencies();

			if (rightdeps != null)
				rightdeps.remove(dependency);

			getList().remove(dependency);
		} catch (PersistenceException e) {
			tx.rollback();
			throw e;
		}
		commitAndNotify(tx);
	}
}
