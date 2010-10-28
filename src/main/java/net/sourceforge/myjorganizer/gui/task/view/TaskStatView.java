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

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

public class TaskStatView extends AbstractTaskView implements TaskView, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2541380650705637543L;

	private JTabbedPane tabbedPane = new JTabbedPane();
	private AbstractTaskSubView completionView = new TaskStatCompletionView();
	private TaskStatPriorityView priorityView = new TaskStatPriorityView();

	public TaskStatView() {
		super(new GridLayout(1, 1));

		tabbedPane.add(completionView);
		tabbedPane.add(priorityView);

		tabbedPane.setTitleAt(0, _("TASK_COMPLETION"));
		tabbedPane.setTitleAt(1, _("TASK_PRIORITY"));

		this.add(tabbedPane);
	}

	@Override
	public void update(Observable o, Object arg) {
		completionView.update(o, arg);
		priorityView.update(o, arg);
	}

	@Override
	public Observer getTaskSetModelObserver() {
		return this;
	}

	@Override
	public Observer getTaskStatusModelObserver() {
		return null;
	}
}
