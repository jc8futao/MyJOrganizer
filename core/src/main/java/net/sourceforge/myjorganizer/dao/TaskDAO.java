package net.sourceforge.myjorganizer.dao;

import net.sourceforge.myjorganizer.jpa.entities.Task;

/**
 * <p>TaskDAO interface.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public interface TaskDAO extends EntityDAO<Task> {
    /**
     * <p>delete</p>
     *
     * @param id a {@link java.lang.String} object.
     * @return a boolean.
     */
    public boolean delete(String id);
}
