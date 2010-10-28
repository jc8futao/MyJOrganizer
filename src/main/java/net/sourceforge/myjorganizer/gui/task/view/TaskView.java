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

package net.sourceforge.myjorganizer.gui.task.view;

import java.util.Observer;

import net.sourceforge.myjorganizer.gui.task.model.TaskEventListener;

/**
 * <p>TaskView interface.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public interface TaskView {
    /**
     * <p>addTaskEventListener</p>
     *
     * @param l a {@link net.sourceforge.myjorganizer.gui.task.model.TaskEventListener} object.
     */
    public void addTaskEventListener(TaskEventListener l);

    /**
     * <p>removeTaskEventListener</p>
     *
     * @param l a {@link net.sourceforge.myjorganizer.gui.task.model.TaskEventListener} object.
     */
    public void removeTaskEventListener(TaskEventListener l);

    /**
     * <p>getTaskSetModelObserver</p>
     *
     * @return a {@link java.util.Observer} object.
     */
    public Observer getTaskSetModelObserver();

    /**
     * <p>getTaskStatusModelObserver</p>
     *
     * @return a {@link java.util.Observer} object.
     */
    public Observer getTaskStatusModelObserver();
}
