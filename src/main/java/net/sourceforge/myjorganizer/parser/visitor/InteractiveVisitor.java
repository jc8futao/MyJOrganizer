package net.sourceforge.myjorganizer.parser.visitor;

import java.io.PrintStream;

import javax.persistence.NoResultException;

import net.sourceforge.myjorganizer.gui.task.model.TaskModels;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDeleteCommand;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDoneCommand;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskInsertCommand;

/**
 * Adds console feedback to ExecutingVisitor
 *
 * @author Davide Bellettini
 * @version $Id$
 */
public class InteractiveVisitor extends ExecutingVisitor {

    private PrintStream out;

    /**
     * <p>Constructor for InteractiveVisitor.</p>
     *
     * @param out a {@link java.io.PrintStream} object.
     * @param models a {@link net.sourceforge.myjorganizer.gui.task.model.TaskModels} object.
     */
    public InteractiveVisitor(PrintStream out, TaskModels models) {
        super(models);

        this.out = out;
    }

    /** {@inheritDoc} */
    @Override
    public void visit(TaskInsertCommand n) {
        super.visit(n);
        out.println("Task " + n.f1.f1.tokenImage + " successfully inserted");
    }

    /** {@inheritDoc} */
    @Override
    public void visit(TaskDeleteCommand n) {
        try {
            super.visit(n);
            out.println("Task " + n.f2.tokenImage + " successfully deleted");
        } catch (NoResultException e) {
            out.println("Task not found");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void visit(TaskDoneCommand n) {
        super.visit(n);
        out.println("Task " + n.f2.tokenImage + " successfully marked as done");
    }
}
