package net.sourceforge.myjorganizer.jpa.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;

public class TaskDependencyValidator implements ConstraintValidator<TaskDependencyCheck, TaskDependency> {

    @Override
    public void initialize(TaskDependencyCheck constraintAnnotation) {
    }

    @Override
    public boolean isValid(TaskDependency value,
            ConstraintValidatorContext context) {

        return value.getLeft() != value.getRight();
    }

}
