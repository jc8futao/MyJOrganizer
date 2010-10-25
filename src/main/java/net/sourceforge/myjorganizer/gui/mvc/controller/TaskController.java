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

package net.sourceforge.myjorganizer.gui.mvc.controller;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import javax.persistence.EntityManager;
import javax.swing.JTabbedPane;

import net.sourceforge.myjorganizer.gui.mvc.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.mvc.view.TaskFourQuadrantsView;
import net.sourceforge.myjorganizer.gui.mvc.view.TaskStatView;
import net.sourceforge.myjorganizer.gui.mvc.view.TaskSourceView;
import net.sourceforge.myjorganizer.gui.mvc.view.TaskTableView;

public class TaskController {
	private TaskSetModel taskSetModel;
	private TaskTableView jTableView;
	private TaskSourceView sourceView;
	private TaskFourQuadrantsView fourQuadrantsView;
	private TaskStatView statView;

	public TaskController(EntityManager entityManager, JTabbedPane pane) {
		this.taskSetModel = new TaskSetModel(entityManager);

		jTableView = new TaskTableView();
		sourceView = new TaskSourceView();
		fourQuadrantsView = new TaskFourQuadrantsView();
		statView = new TaskStatView();

		taskSetModel.addObserver(jTableView);
		taskSetModel.addObserver(sourceView);
		taskSetModel.addObserver(fourQuadrantsView);
		taskSetModel.addObserver(statView);

		sourceView.update(taskSetModel, null);
		jTableView.update(taskSetModel, null);
		fourQuadrantsView.update(taskSetModel, null);
		statView.update(taskSetModel, null);

		pane.add(sourceView);
		pane.add(jTableView);
		pane.add(fourQuadrantsView);
		pane.add(statView);

		int i = 0;
		pane.setTitleAt(i++, _("TASK_SOURCE"));
		pane.setTitleAt(i++, _("TASK_LIST"));
		pane.setTitleAt(i++, _("TASK_QUADRANTS"));
		pane.setTitleAt(i++, _("TASK_STATS"));
	}
}
