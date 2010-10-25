package net.sourceforge.myjorganizer.gui.mvc.view;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import net.sourceforge.myjorganizer.gui.mvc.model.TaskEvent;
import net.sourceforge.myjorganizer.gui.mvc.model.TaskEventListener;

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
