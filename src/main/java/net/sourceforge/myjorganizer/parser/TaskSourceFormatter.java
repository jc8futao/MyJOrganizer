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

package net.sourceforge.myjorganizer.parser;

import static net.sourceforge.myjorganizer.parser.StringUtils.escape;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

import net.sourceforge.myjorganizer.jpa.entities.Task;
import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;

/**
 * <p>
 * TaskSourceFormatter class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskSourceFormatter {
    private final static String INDENT = "    ";
    private final static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * <p>
     * formatSource
     * </p>
     * 
     * @param tasks
     *            a {@link java.lang.Iterable} object.
     * @return a {@link java.lang.String} object.
     */
    public static String formatSource(Iterable<Task> tasks) {
        StringBuffer sb = new StringBuffer();

        for (Task task : tasks) {
            sb.append("insert task " + task.getId());

            sb.append(":\n");

            sb.append(INDENT + "title: " + escape(task.getTitle()) + "\n");

            String description;
            if ((description = task.getDescription()) != null
                    && description.length() > 0) {
                sb.append(INDENT + "description: " + escape(description) + "\n");
            }

            sb.append(INDENT + "completion: " + task.getCompletion() + "%\n");
            sb.append(INDENT + "urgent: " + task.getPriority().isUrgent()
                    + "\n");
            sb.append(INDENT + "important: " + task.getPriority().isImportant()
                    + "\n");
            if (task.getStatus() != null)
                sb.append(INDENT + "status: " + escape(task.getStatus()) + "\n");

            if (task.getStartDate() != null) {
                sb.append(INDENT + "startdate: "
                        + format.format(task.getStartDate()) + "\n");
            }

            if (task.getDueDate() != null) {
                sb.append(INDENT + "duedate: "
                        + format.format(task.getDueDate()) + "\n");
            }

            Set<TaskDependency> dependencies = task.getLeftDependencies();
            if (dependencies != null && dependencies.size() > 0) {
                sb.append(INDENT + "dependencies: \n");

                for (TaskDependency dep : dependencies) {
                    sb.append(INDENT + INDENT);
                    sb.append("before ");
                    sb.append(dep.getRight().getId());
                    sb.append("\n");
                }

                sb.append(INDENT + "end dependencies \n");
            }

            sb.append("end task\n\n");
        }

        return sb.toString();
    }
}
