package net.sourceforge.myjorganizer.gui.task.model;

import javax.persistence.EntityManager;

/**
 * <p>TaskModels class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class TaskModels {

    private final TaskSetModel taskModel;
    private final TaskStatusModel statusModel;
    private final TaskDependencyModel dependencyModel;

    /**
     * <p>Constructor for TaskModels.</p>
     *
     * @param taskModel a {@link net.sourceforge.myjorganizer.gui.task.model.TaskSetModel} object.
     * @param statusModel a {@link net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel} object.
     * @param dependencyModel a {@link net.sourceforge.myjorganizer.gui.task.model.TaskDependencyModel} object.
     */
    public TaskModels(TaskSetModel taskModel, TaskStatusModel statusModel,
            TaskDependencyModel dependencyModel) {

        this.taskModel = taskModel;
        this.statusModel = statusModel;
        this.dependencyModel = dependencyModel;
    }

    /**
     * <p>Constructor for TaskModels.</p>
     *
     * @param em a {@link javax.persistence.EntityManager} object.
     */
    public TaskModels(EntityManager em) {
        this(new TaskSetModel(em), new TaskStatusModel(em),
                new TaskDependencyModel(em));
    }

    /**
     * <p>Getter for the field <code>dependencyModel</code>.</p>
     *
     * @return a {@link net.sourceforge.myjorganizer.gui.task.model.TaskDependencyModel} object.
     */
    public TaskDependencyModel getDependencyModel() {
        return dependencyModel;
    }

    /**
     * <p>Getter for the field <code>statusModel</code>.</p>
     *
     * @return a {@link net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel} object.
     */
    public TaskStatusModel getStatusModel() {
        return statusModel;
    }

    /**
     * <p>Getter for the field <code>taskModel</code>.</p>
     *
     * @return a {@link net.sourceforge.myjorganizer.gui.task.model.TaskSetModel} object.
     */
    public TaskSetModel getTaskModel() {
        return taskModel;
    }
}
