package net.sourceforge.myjorganizer.gui.mvc.model;

import java.util.Collection;
import java.util.EventObject;

import net.sourceforge.myjorganizer.data.Task;

public class TaskEvent extends EventObject {
	private static final long serialVersionUID = 4144958728704612439L;
	private final Collection<Task> changedTasks;

	public Collection<Task> getChangedTasks() {
		return changedTasks;
	}

	public TaskEvent(Object source, Collection<Task> changedTasks) {
		super(source);

		this.changedTasks = changedTasks;
	}
}
