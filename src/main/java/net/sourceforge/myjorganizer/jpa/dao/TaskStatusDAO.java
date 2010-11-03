package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.jpa.entities.TaskStatus;

public class TaskStatusDAO extends JPAEntityDAO<TaskStatus>{

    public TaskStatusDAO(EntityManager entityManager) {
        super(entityManager, TaskStatus.class);
    }

}
