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

package net.sourceforge.myjorganizer.gui.task.model;

import java.util.Collection;
import java.util.EventObject;

import net.sourceforge.myjorganizer.data.Task;

/**
 * <p>TaskEvent class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskEvent extends EventObject {
    private static final long serialVersionUID = 4144958728704612439L;
    private final Collection<Task> changedTasks;

    /**
     * <p>Getter for the field <code>changedTasks</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Task> getChangedTasks() {
        return changedTasks;
    }

    /**
     * <p>Constructor for TaskEvent.</p>
     *
     * @param source a {@link java.lang.Object} object.
     * @param changedTasks a {@link java.util.Collection} object.
     */
    public TaskEvent(Object source, Collection<Task> changedTasks) {
        super(source);

        this.changedTasks = changedTasks;
    }
}
