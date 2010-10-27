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

package net.sourceforge.myjorganizer.parser;

import net.sourceforge.myjorganizer.data.Task;

public class TaskSourceFormatter {

	public static String formatSource(Iterable<Task> tasks) {
		StringBuffer sb = new StringBuffer();

		for (Task task : tasks) {
			if (task.getId() > 0) {
				sb.append("task " + task.getIdentifier());

				if (task.getParent() != null) {
					sb.append(" childof ");
					sb.append(task.getParent().getIdentifier());
				}

				sb.append(":\n");
			} else {
				sb.append("task:\n");
			}
			sb.append("    title: \"" + task.getTitle() + "\"\n");

			String description;
			if ((description = task.getDescription()) != null
					&& description.length() > 0) {
				sb.append("    description: \"" + description + "\"\n");
			}

			sb.append("    completion: " + task.getCompletion() + "%\n");
			sb.append("    urgent: " + task.getPriority().isUrgent() + "\n");
			sb.append("    important: " + task.getPriority().isImportant()
					+ "\n");
			if (task.getStatus() != null)
				sb.append("    status: \"" + task.getStatus() + "\"\n");
			sb.append("end task\n\n");
		}

		return sb.toString();
	}
}
