package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;

public class JPATaskDependencyDAO extends JPAEntityDAO<TaskDependency> {

    public JPATaskDependencyDAO(EntityManager entityManager,
            Class<TaskDependency> class1) {
        super(entityManager, class1);
        // TODO Auto-generated constructor stub
    }
}
