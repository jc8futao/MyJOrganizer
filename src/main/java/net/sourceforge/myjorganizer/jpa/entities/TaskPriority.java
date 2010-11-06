/*
 * This file is part of MyJOrganizer.
 *
 * MyJOrganizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyJOrganizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MyJOrganizer.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sourceforge.myjorganizer.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
/**
 * <p>Priority class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
@Table(name = "priorities")
public final class TaskPriority implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 5637832605950364727L;

    @Id
    private final boolean urgent;
    @Id
    private final boolean important;

    private static ArrayList<TaskPriority> instances = new ArrayList<TaskPriority>();

    static {
        instances.add(new TaskPriority(true, true));
        instances.add(new TaskPriority(false, true));
        instances.add(new TaskPriority(true, false));
        instances.add(new TaskPriority(false, false));
    }

    private TaskPriority() {
        this(false, false);
    }

    private TaskPriority(boolean urgent, boolean important) {
        this.urgent = urgent;
        this.important = important;
    }

    /**
     * <p>isUrgent</p>
     *
     * @return a boolean.
     */
    public boolean isUrgent() {
        return this.urgent;
    }

    /**
     * <p>isImportant</p>
     *
     * @return a boolean.
     */
    public boolean isImportant() {
        return this.important;
    }

    /**
     * <p>factory</p>
     *
     * @param urgent a boolean.
     * @param important a boolean.
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.TaskPriority} object.
     */
    public static TaskPriority factory(boolean urgent, boolean important) {
        for (TaskPriority priority : instances) {
            if ((priority.isImportant() == important)
                    && (priority.isUrgent() == urgent)) {
                return priority;
            }
        }

        return null;
    }

    /**
     * <p>getAll</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public static Collection<TaskPriority> getAll() {
        return instances;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (important ? 1231 : 1237);
        result = prime * result + (urgent ? 1231 : 1237);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaskPriority other = (TaskPriority) obj;
        if (important != other.important)
            return false;
        if (urgent != other.urgent)
            return false;
        return true;
    }
}
