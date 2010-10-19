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
