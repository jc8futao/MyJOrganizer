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

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import net.sourceforge.myjorganizer.data.TaskStatus;

/**
 * <p>TaskStatusComboBoxRenderer class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskStatusComboBoxRenderer extends JComboBox implements
        TableCellRenderer {
    private static final long serialVersionUID = -9114625562997469320L;

    /**
     * <p>Constructor for TaskStatusComboBoxRenderer.</p>
     *
     * @param items an array of {@link net.sourceforge.myjorganizer.data.TaskStatus} objects.
     */
    public TaskStatusComboBoxRenderer(TaskStatus[] items) {
        super(items);
    }

    /** {@inheritDoc} */
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        // Select the current value
        setSelectedItem(value);
        return this;
    }
}
