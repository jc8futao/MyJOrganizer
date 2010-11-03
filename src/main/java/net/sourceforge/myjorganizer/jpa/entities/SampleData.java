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
package net.sourceforge.myjorganizer.jpa.entities;

import java.util.ArrayList;

import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel;

/**
 * <p>
 * SampleData class.
 * </p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class SampleData {

    /**
     * <p>
     * loadSampleTaskData
     * </p>
     *
     * @param taskSetModel
     *            a
     *            {@link net.sourceforge.myjorganizer.gui.task.model.TaskSetModel}
     *            object.
     * @param statusModel a {@link net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel} object.
     */
    public static void loadSampleTaskData(TaskSetModel taskSetModel, TaskStatusModel statusModel) {
        TaskStatus open = statusModel.getById("open");
        
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("Task 1"));
        tasks.add(new Task("Task 2"));
        tasks.add(new Task("Task 3"));
        tasks.add(new Task("Task 4"));

        tasks.get(1).setUrgent(true);
        tasks.get(2).setImportant(true);
        tasks.get(3).setUrgent(true);
        tasks.get(3).setImportant(true);

        int i = 0;
        for (Task t : tasks) {
            t.setId("demotask" + ++i);
            t.setStatus(open);
        }

        taskSetModel.addMany(tasks);
    }
}
