package net.sourceforge.myjorganizer.data;

import java.util.ArrayList;
import java.util.Collection;

public final class Priority {

	private final boolean urgent;
	private final boolean important;

	private static ArrayList<Priority> instances = new ArrayList<Priority>();

	static {
		instances.add(new Priority(false, false));
		instances.add(new Priority(false, true));
		instances.add(new Priority(true, false));
		instances.add(new Priority(true, true));
	}

	private Priority(boolean urgent, boolean important) {
		this.urgent = urgent;
		this.important = important;
	}

	public boolean isUrgent() {
		return this.urgent;
	}

	public boolean isImportant() {
		return this.important;
	}

	public static Priority factory(boolean urgent, boolean important) {
		for (Priority priority : instances) {
			if ((priority.isImportant() == important)
					&& (priority.isUrgent() == urgent)) {
				return priority;
			}
		}

		return null;
	}

	public static Collection<Priority> getAll() {
		return instances;
	}
}
