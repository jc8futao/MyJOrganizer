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
public final class Priority implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 5637832605950364727L;

    @Id
    private final boolean urgent;
    @Id
    private final boolean important;

    private static ArrayList<Priority> instances = new ArrayList<Priority>();

    static {
        instances.add(new Priority(true, true));
        instances.add(new Priority(false, true));
        instances.add(new Priority(true, false));
        instances.add(new Priority(false, false));
    }

    private Priority() {
        this(false, false);
    }

    private Priority(boolean urgent, boolean important) {
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
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.Priority} object.
     */
    public static Priority factory(boolean urgent, boolean important) {
        for (Priority priority : instances) {
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
    public static Collection<Priority> getAll() {
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
        Priority other = (Priority) obj;
        if (important != other.important)
            return false;
        if (urgent != other.urgent)
            return false;
        return true;
    }
}
