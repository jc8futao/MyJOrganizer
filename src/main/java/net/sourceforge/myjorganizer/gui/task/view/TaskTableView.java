/**
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

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.data.TaskStatus;
import net.sourceforge.myjorganizer.gui.task.model.TaskEvent;
import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskTableModel;

public class TaskTableView extends AbstractTaskView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4741518527769099366L;
	private JTable jTable;
	private TaskTableModel tableModel = new TaskTableModel();
	private TaskStatus[] taskStatuses = new TaskStatus[0];
	private TableModelListener tableListener;

	public TaskTableView() {
		super(new GridLayout(1, 1));

		this.jTable = new JTable(tableModel);
		add(new JScrollPane(this.jTable));

		this.tableListener = new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {

				Collection<Task> changedTasks = new ArrayList<Task>();

				try {
					for (int i = e.getFirstRow(), last = e.getLastRow(); i <= last; i++) {
						changedTasks.add(tableModel.getRowData(i));
					}
				} catch (IndexOutOfBoundsException ex) {
				}

				fireTaskEvent(new TaskEvent(this, changedTasks));
			}
		};
	}

	public TaskStatus[] getTaskStatuses() {
		return taskStatuses;
	}

	@Override
	public Observer getTaskSetModelObserver() {
		return new Observer() {
			public void update(Observable o, Object arg) {
				TaskSetModel taskSetModel = (TaskSetModel) o;

				tableModel.removeTableModelListener(tableListener);
				tableModel.setList(taskSetModel.getList());
				tableModel.addTableModelListener(tableListener);
			}
		};
	}

	@Override
	public Observer getTaskStatusModelObserver() {
		return new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				TaskStatusModel model = (TaskStatusModel) o;
				taskStatuses = model.getList().toArray(taskStatuses);

				TableColumn statusColumn = jTable.getColumn("Status");

				statusColumn.setCellEditor(new TaskStatusComboBoxEditor(
						taskStatuses));
				statusColumn.setCellRenderer(new TaskStatusComboBoxRenderer(
						taskStatuses));
			}
		};
	}
}
