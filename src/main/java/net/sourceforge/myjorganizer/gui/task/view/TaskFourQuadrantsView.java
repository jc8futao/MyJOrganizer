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

package net.sourceforge.myjorganizer.gui.task.view;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.jpa.entities.TaskPriority;
import net.sourceforge.myjorganizer.jpa.entities.Task;

/**
 * <p>
 * TaskFourQuadrantsView class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id: TaskFourQuadrantsView.java 111 2010-10-28 12:24:09Z dbellettini$
 */
public class TaskFourQuadrantsView extends AbstractTaskView implements
        Observer{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1944351748661553463L;
    HashMap<TaskPriority, JList> lists = new HashMap<TaskPriority, JList>();
    HashMap<TaskPriority, List<Task>> listData = new HashMap<TaskPriority, List<Task>>();

    /**
     * <p>
     * Constructor for TaskFourQuadrantsView.
     * </p>
     */
    public TaskFourQuadrantsView() {
        Collection<TaskPriority> priorities = TaskPriority.getAll();

        int cols = priorities.size() / 2 + priorities.size() % 2;
        setLayout(new GridLayout(cols, 2));

        for (TaskPriority priority : priorities) {
            JList currentList = new JList();
            currentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lists.put(priority, currentList);
            listData.put(priority, new ArrayList<Task>());

            String title = _((priority.isUrgent() ? "" : "NOT_") + "URGENT_"
                    + (priority.isImportant() ? "" : "NOT_") + "IMPORTANT");
            add(new TitledJList(new JLabel(title), currentList));
        }
    }

    /** {@inheritDoc} */
    @Override
    public void update(Observable o, Object arg) {
        TaskSetModel model = (TaskSetModel) o;

        for (List<Task> tasks : listData.values()) {
            tasks.clear();
        }

        for (Task task : model.getList()) {
            listData.get(task.getPriority()).add(task);
        }

        for (Entry<TaskPriority, List<Task>> entry : listData.entrySet()) {
            lists.get(entry.getKey()).setListData(entry.getValue().toArray());
        }
    }

    /** {@inheritDoc} */
    @Override
    public Observer getTaskSetModelObserver() {
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Observer getTaskStatusModelObserver() {
        return null;
    }
}