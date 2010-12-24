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

import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.myjorganizer.jpa.entities.Task;

import com.davidebellettini.gui.utils.GenericTableModel;
import com.davidebellettini.gui.utils.TableProperty;

/**
 * <p>TaskTableModel class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskTableModel extends GenericTableModel<Task> {

    private static final long serialVersionUID = -4356614797850225465L;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * <p>Constructor for TaskTableModel.</p>
     */
    public TaskTableModel() {
        super(Task.class);
    }

    /** {@inheritDoc} */
    @Override
    public int getRowCount() {
        return tasks.size();
    }

    /** {@inheritDoc} */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task t = tasks.get(rowIndex);

        TableProperty property = getProperty(columnIndex);

        try {
            return property.getGetter().invoke(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Task t = tasks.get(rowIndex);
        TableProperty property = getProperty(columnIndex);

        try {
            property.getSetter().invoke(t, aValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     * <p>setList</p>
     *
     * @param list a {@link java.util.Collection} object.
     */
    public void setList(Collection<Task> list) {
        this.tasks = new ArrayList<Task>(list);

        fireTableDataChanged();
    }

    /** {@inheritDoc} */
    @Override
    public Task getRowData(int rowIndex) {
        return tasks.get(rowIndex);
    }
}
