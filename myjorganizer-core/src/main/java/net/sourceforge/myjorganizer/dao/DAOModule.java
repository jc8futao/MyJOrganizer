package net.sourceforge.myjorganizer.dao;

import net.sourceforge.myjorganizer.jpa.dao.JPATaskDAO;
import net.sourceforge.myjorganizer.jpa.dao.JPATaskDependencyDAO;
import net.sourceforge.myjorganizer.jpa.dao.JPATaskPriorityDAO;
import net.sourceforge.myjorganizer.jpa.dao.JPATaskStatusDAO;

import com.google.inject.AbstractModule;

public class DAOModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TaskDAO.class).to(JPATaskDAO.class);
		bind(TaskDependencyDAO.class).to(JPATaskDependencyDAO.class);
		bind(TaskPriorityDAO.class).to(JPATaskPriorityDAO.class);
		bind(TaskStatusDAO.class).to(JPATaskStatusDAO.class);
	}
}
