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

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import net.sourceforge.myjorganizer.gui.task.model.TaskEvent;
import net.sourceforge.myjorganizer.gui.task.model.TaskEventListener;

public abstract class AbstractTaskView extends JPanel implements TaskView {

	private static final long serialVersionUID = 983360402903856594L;

	private List<TaskEventListener> listeners = new ArrayList<TaskEventListener>();

	protected AbstractTaskView(LayoutManager layout) {
		super(layout);
	}

	protected AbstractTaskView() {
		super();
	}
	
	@Override
	public void addTaskEventListener(TaskEventListener l) {
		this.listeners.add(l);
	}

	@Override
	public void removeTaskEventListener(TaskEventListener l) {
		this.listeners.remove(l);
	}

	protected synchronized void fireTaskEvent(TaskEvent e) {
		for (TaskEventListener l : this.listeners) {
			l.tasksChanged(e);
		}
	}
}
