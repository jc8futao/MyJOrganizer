package net.sourceforge.myjorganizer.jpa.dao;

import javax.persistence.EntityManager;

import net.sourceforge.myjorganizer.dao.TaskDAO;
import net.sourceforge.myjorganizer.jpa.entities.Task;

public class JPATaskDAO extends JPAEntityDAO<Task> implements TaskDAO {

    public JPATaskDAO(EntityManager entityManager) {
        super(entityManager, Task.class);
    }

    @Override
    public boolean delete(String id) {
        return getEntityManager().createQuery("DELETE FROM Task WHERE id=?").setParameter(1, id).executeUpdate() > 0;        
    }
}
