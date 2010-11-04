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

package net.sourceforge.myjorganizer.gui.task.controller;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.persistence.EntityManager;
import javax.swing.JTabbedPane;

import net.sourceforge.myjorganizer.gui.task.model.TaskDependencyModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskEvent;
import net.sourceforge.myjorganizer.gui.task.model.TaskEventListener;
import net.sourceforge.myjorganizer.gui.task.model.TaskModels;
import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel;
import net.sourceforge.myjorganizer.gui.task.view.AbstractTaskView;
import net.sourceforge.myjorganizer.gui.task.view.TaskSingleView;
import net.sourceforge.myjorganizer.gui.task.view.TaskFourQuadrantsView;
import net.sourceforge.myjorganizer.gui.task.view.TaskSourceView;
import net.sourceforge.myjorganizer.gui.task.view.TaskStatView;
import net.sourceforge.myjorganizer.gui.task.view.TaskTableView;
import net.sourceforge.myjorganizer.jpa.entities.SampleData;
import net.sourceforge.myjorganizer.jpa.entities.Task;
import net.sourceforge.myjorganizer.jpa.entities.TaskStatus;

/**
 * <p>
 * TaskController class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskController implements TaskEventListener {
    private TaskTableView jTableView;
    private TaskSourceView sourceView;
    private TaskFourQuadrantsView fourQuadrantsView;
    private TaskStatView statView;
    private JTabbedPane pane;
    private final TaskModels taskModels;
    private TaskSingleView taskAddView = new TaskSingleView();

    /**
     * <p>
     * getDependencyModel
     * </p>
     * 
     * @return a
     *         {@link net.sourceforge.myjorganizer.gui.task.model.TaskDependencyModel}
     *         object.
     */
    public TaskDependencyModel getDependencyModel() {
        return taskModels.getDependencyModel();
    }

    /**
     * <p>
     * getStatusModel
     * </p>
     * 
     * @return a
     *         {@link net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel}
     *         object.
     */
    public TaskStatusModel getStatusModel() {
        return taskModels.getStatusModel();
    }

    /**
     * <p>
     * getTaskModel
     * </p>
     * 
     * @return a
     *         {@link net.sourceforge.myjorganizer.gui.task.model.TaskSetModel}
     *         object.
     */
    public TaskSetModel getTaskModel() {
        return taskModels.getTaskModel();
    }

    /**
     * <p>
     * Constructor for TaskController.
     * </p>
     * 
     * @param entityManager
     *            a {@link javax.persistence.EntityManager} object.
     * @param pane
     *            a {@link javax.swing.JTabbedPane} object.
     */
    public TaskController(EntityManager entityManager, JTabbedPane pane) {
        this.taskModels = new TaskModels(entityManager);

        this.pane = pane;

        jTableView = new TaskTableView();
        sourceView = new TaskSourceView();
        fourQuadrantsView = new TaskFourQuadrantsView();
        statView = new TaskStatView();

        TaskStatus[] taskStatuses = new TaskStatus[0];
        taskStatuses = getStatusModel().getList().toArray(taskStatuses);

        addView(sourceView);
        addView(jTableView);
        addView(fourQuadrantsView);
        addView(statView);

        initTaskAddView();

        int i = 0;
        pane.setTitleAt(i++, _("TASK_SOURCE"));
        pane.setTitleAt(i++, _("TASK_LIST"));
        pane.setTitleAt(i++, _("TASK_QUADRANTS"));
        pane.setTitleAt(i++, _("TASK_STATS"));
    }

    /** {@inheritDoc} */
    @Override
    public void tasksChanged(TaskEvent e) {
        getTaskModel().updateMany(e.getChangedTasks());
    }

    private void addView(AbstractTaskView view) {
        pane.add(view);

        view.addTaskEventListener(this);

        Observer taskSetModelObserver = view.getTaskSetModelObserver();
        Observer taskStatusModelObserver = view.getTaskStatusModelObserver();

        if (taskSetModelObserver != null) {
            taskSetModelObserver.update(getTaskModel(), null);
            getTaskModel().addObserver(taskSetModelObserver);
        }

        if (taskStatusModelObserver != null) {
            taskStatusModelObserver.update(getStatusModel(), null);
            getStatusModel().addObserver(taskStatusModelObserver);
        }
    }

    /**
     * <p>
     * loadSampledata
     * </p>
     */
    public void loadSampledata() {
        SampleData.loadSampleTaskData(getTaskModel(), getStatusModel());
    }

    /**
     * <p>
     * getModels
     * </p>
     * 
     * @return a {@link net.sourceforge.myjorganizer.gui.task.model.TaskModels}
     *         object.
     */
    public TaskModels getModels() {
        return this.taskModels;
    }

    public void showNewTask() {
        this.taskAddView.reset();
        
        pane.add(this.taskAddView);
        pane.setSelectedComponent(this.taskAddView);
        pane.setTitleAt(pane.getSelectedIndex(), _("NEW_TASK"));
    }

    private void initTaskAddView()
    {
        addView(taskAddView);
        pane.remove(taskAddView);
        
        taskAddView.addSaveActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = taskAddView.getTask();
                taskModels.getTaskModel().add(task);
                pane.remove(taskAddView);
                pane.setSelectedIndex(1);
                taskAddView.reset();
            }
        });
        
        taskAddView.addCancelActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.remove(taskAddView);
                taskAddView.reset();
            }
        });
    }
}
