package net.sourceforge.myjorganizer.parser.visitor;

import java.io.PrintStream;

import javax.persistence.NoResultException;

import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDeleteCommand;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDoneCommand;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskInsertCommand;

/**
 * Adds console feedback to ExecutingVisitor
 * 
 * @author Davide Bellettini
 */
public class InteractiveVisitor extends ExecutingVisitor {

    private PrintStream out;

    public InteractiveVisitor(PrintStream out, TaskSetModel taskModel,
            TaskStatusModel statusModel) {
        super(taskModel, statusModel);

        this.out = out;
    }

    @Override
    public void visit(TaskInsertCommand n) {
        super.visit(n);
        out.println("Task " + n.f1.f1.tokenImage + "successfully inserted");
    }

    @Override
    public void visit(TaskDeleteCommand n) {
        try {
            super.visit(n);
            out.println("Task " + n.f2.tokenImage + " successfully deleted");
        } catch (NoResultException e) {
            out.println("Task not found");
        }
    }

    @Override
    public void visit(TaskDoneCommand n) {
        super.visit(n);
        out.println("Task " + n.f2.tokenImage + " successfully marked as done");
    }
}
