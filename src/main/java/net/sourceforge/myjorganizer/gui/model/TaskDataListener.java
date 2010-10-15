package net.sourceforge.myjorganizer.gui.model;

import java.util.EventListener;

public interface TaskDataListener extends EventListener {
	public void contentChanged(TaskDataEvent event);
}
