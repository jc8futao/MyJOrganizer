package net.sourceforge.myjorganizer.gui.task.model;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.jpa.dao.JPATaskDependencyDAO;
import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;

public class TaskDependencyModel extends ObservableEntityModel<TaskDependency> {

    public TaskDependencyModel(EntityManager entityManager) {
        super(entityManager, new JPATaskDependencyDAO(entityManager,
                TaskDependency.class));

        this.setList(entityManager.createQuery("FROM TaskDependency",
                TaskDependency.class).getResultList());
    }
}
