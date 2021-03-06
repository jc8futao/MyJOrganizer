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

package net.sourceforge.myjorganizer.gui.task.view.table;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import net.sourceforge.myjorganizer.jpa.entities.TaskStatus;

/**
 * <p>TaskStatusComboBoxEditor class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskStatusComboBoxEditor extends DefaultCellEditor {
    /**
	 * 
	 */
    private static final long serialVersionUID = 2650011890512281580L;

    /**
     * <p>Constructor for TaskStatusComboBoxEditor.</p>
     *
     * @param items an array of {@link net.sourceforge.myjorganizer.jpa.entities.TaskStatus} objects.
     */
    public TaskStatusComboBoxEditor(TaskStatus[] items) {
        super(new JComboBox(items));
    }
}
