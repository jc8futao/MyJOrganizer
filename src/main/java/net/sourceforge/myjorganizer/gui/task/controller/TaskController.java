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

package net.sourceforge.myjorganizer.gui.task.controller;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.util.Observer;

import javax.persistence.EntityManager;
import javax.swing.JTabbedPane;

import net.sourceforge.myjorganizer.data.SampleData;
import net.sourceforge.myjorganizer.data.TaskStatus;
import net.sourceforge.myjorganizer.gui.task.model.TaskEvent;
import net.sourceforge.myjorganizer.gui.task.model.TaskEventListener;
import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel;
import net.sourceforge.myjorganizer.gui.task.view.AbstractTaskView;
import net.sourceforge.myjorganizer.gui.task.view.TaskFourQuadrantsView;
import net.sourceforge.myjorganizer.gui.task.view.TaskSourceView;
import net.sourceforge.myjorganizer.gui.task.view.TaskStatView;
import net.sourceforge.myjorganizer.gui.task.view.TaskTableView;

public class TaskController implements TaskEventListener {
	private TaskSetModel taskSetModel;
	private TaskTableView jTableView;
	private TaskSourceView sourceView;
	private TaskFourQuadrantsView fourQuadrantsView;
	private TaskStatView statView;
	private JTabbedPane pane;
	private TaskStatusModel taskStatusModel;

	public TaskController(EntityManager entityManager, JTabbedPane pane) {
		this.taskSetModel = new TaskSetModel(entityManager);
		this.taskStatusModel = new TaskStatusModel(entityManager);
		this.pane = pane;

		jTableView = new TaskTableView();
		sourceView = new TaskSourceView();
		fourQuadrantsView = new TaskFourQuadrantsView();
		statView = new TaskStatView();

		TaskStatus[] taskStatuses = new TaskStatus[0];
		taskStatuses = taskStatusModel.getList().toArray(taskStatuses);

		addView(sourceView);
		addView(jTableView);
		addView(fourQuadrantsView);
		addView(statView);

		int i = 0;
		pane.setTitleAt(i++, _("TASK_SOURCE"));
		pane.setTitleAt(i++, _("TASK_LIST"));
		pane.setTitleAt(i++, _("TASK_QUADRANTS"));
		pane.setTitleAt(i++, _("TASK_STATS"));
	}

	@Override
	public void tasksChanged(TaskEvent e) {
		taskSetModel.updateMany(e.getChangedTasks());
	}

	private void addView(AbstractTaskView view) {
		pane.add(view);

		view.addTaskEventListener(this);

		Observer taskSetModelObserver = view.getTaskSetModelObserver();
		Observer taskStatusModelObserver = view.getTaskStatusModelObserver();

		if (taskSetModelObserver != null) {
			taskSetModelObserver.update(taskSetModel, null);
			taskSetModel.addObserver(taskSetModelObserver);
		}

		if (taskStatusModelObserver != null) {
			taskStatusModelObserver.update(taskStatusModel, null);
			taskStatusModel.addObserver(taskStatusModelObserver);
		}
	}

	public void loadSampledata() {
		SampleData.loadSampleTaskData(taskSetModel);
	}

	public TaskSetModel getTaskSetModel() {
		return taskSetModel;
	}
}
