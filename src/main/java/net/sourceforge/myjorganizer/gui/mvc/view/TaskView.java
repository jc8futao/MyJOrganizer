package net.sourceforge.myjorganizer.gui.mvc.view;

import java.util.Observer;

import net.sourceforge.myjorganizer.gui.mvc.model.TaskEventListener;

public interface TaskView extends Observer {
	public void addTaskEventListener(TaskEventListener l);
	public void removeTaskEventListener(TaskEventListener l);
}
