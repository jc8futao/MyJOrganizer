package net.sourceforge.myjorganizer.dao;

import net.sourceforge.myjorganizer.jpa.entities.Task;

public interface TaskDAO extends EntityDAO<Task> {
    public boolean delete(String id);
}
