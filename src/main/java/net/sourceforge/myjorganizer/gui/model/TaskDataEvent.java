package net.sourceforge.myjorganizer.gui.model;

import java.util.EventObject;

public class TaskDataEvent extends EventObject {

	private final String propertyName;

	public TaskDataEvent(Object source, String property) {
		super(source);

		this.propertyName = property;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2121847106167760586L;
}
