package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.jpa.entities.Priority;

public class PriorityDAO extends JPAEntityDAO<Priority> {
    public PriorityDAO(EntityManager entityManager) {
        super(entityManager, Priority.class);
    }
}
