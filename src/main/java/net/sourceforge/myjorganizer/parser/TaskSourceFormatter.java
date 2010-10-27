package net.sourceforge.myjorganizer.parser;

import net.sourceforge.myjorganizer.data.Task;

public class TaskSourceFormatter {

	public static String formatSource(Iterable<Task> tasks) {
		StringBuffer sb = new StringBuffer();

		for (Task task : tasks) {
			if (task.getId() > 0) {
				sb.append("task " + task.getIdentifier() + ":\n");
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
			sb.append("    urgent: " + task.getPriority().isUrgent()
					+ "\n");
			sb.append("    important: "
					+ task.getPriority().isImportant() + "\n");
			if (task.getStatus() != null)
				sb.append("    status: \"" + task.getStatus() + "\"\n");
			sb.append("end task\n\n");
		}

		return sb.toString();
	}
}
