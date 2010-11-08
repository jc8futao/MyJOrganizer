package net.sourceforge.myjorganizer.jpa.validator;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.sourceforge.myjorganizer.jpa.entities.Task;
import net.sourceforge.myjorganizer.jpa.entities.TaskStatus;

public class TaskValidator implements
        ConstraintValidator<TaskConstraintCheck, Task> {

    @Override
    public void initialize(TaskConstraintCheck constraintAnnotation) {
    }

    @Override
    public boolean isValid(Task value, ConstraintValidatorContext context) {
        TaskStatus status = value.getStatus();

        if (status != null && "closed".equals(status.getId())
                && value.getCompletion() != 100)
            return false;

        Date startDate = value.getStartDate();
        Date dueDate = value.getDueDate();

        if (startDate != null && dueDate != null && startDate.after(dueDate)) {
            return false;
        }

        return true;
    }
}
