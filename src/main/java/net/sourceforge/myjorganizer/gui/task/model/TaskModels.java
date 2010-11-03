package net.sourceforge.myjorganizer.gui.task.model;

import javax.persistence.EntityManager;

public class TaskModels {

    private final TaskSetModel taskModel;
    private final TaskStatusModel statusModel;
    private final TaskDependencyModel dependencyModel;

    public TaskModels(TaskSetModel taskModel, TaskStatusModel statusModel,
            TaskDependencyModel dependencyModel) {

        this.taskModel = taskModel;
        this.statusModel = statusModel;
        this.dependencyModel = dependencyModel;
    }

    public TaskModels(EntityManager em) {
        this(new TaskSetModel(em), new TaskStatusModel(em),
                new TaskDependencyModel(em));
    }

    public TaskDependencyModel getDependencyModel() {
        return dependencyModel;
    }

    public TaskStatusModel getStatusModel() {
        return statusModel;
    }

    public TaskSetModel getTaskModel() {
        return taskModel;
    }
}
