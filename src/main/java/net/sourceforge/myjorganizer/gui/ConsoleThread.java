package net.sourceforge.myjorganizer.gui;

import java.io.InputStream;
import java.io.PrintStream;

import net.sourceforge.myjorganizer.gui.task.model.TaskModels;
import net.sourceforge.myjorganizer.parser.TaskCommandsParser;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskCommand;
import net.sourceforge.myjorganizer.parser.visitor.InteractiveVisitor;

/**
 * This thread reads input from the console parsing given commands
 *
 * @author davide
 * @version $Id$
 */
public class ConsoleThread extends Thread {

    private TaskCommandsParser parser;
    private PrintStream err;
    private InteractiveVisitor visitor;
    private InputStream in;

    /** {@inheritDoc} */
    @Override
    public void run() {
        while (true) {
            try {
                TaskCommand command = parser.TaskCommand();
                visitor.visit(command);
            } catch (Throwable t) {
                if (MyJOrganizerApp.DEBUG) {
                    t.printStackTrace(err);
                } else {
                    err.println(t);
                }

                parser.ReInit(in);
            }
        }
    }

    /**
     * <p>Constructor for ConsoleThread.</p>
     *
     * @param models a {@link net.sourceforge.myjorganizer.gui.task.model.TaskModels} object.
     */
    public ConsoleThread(TaskModels models) {
        this(System.in, System.out, System.err, models);
    }

    /**
     * <p>Constructor for ConsoleThread.</p>
     *
     * @param in a {@link java.io.InputStream} object.
     * @param out a {@link java.io.PrintStream} object.
     * @param err a {@link java.io.PrintStream} object.
     * @param models a {@link net.sourceforge.myjorganizer.gui.task.model.TaskModels} object.
     */
    public ConsoleThread(InputStream in, PrintStream out, PrintStream err,
            TaskModels models) {
        this.in = in;
        this.parser = new TaskCommandsParser(in);
        this.err = err;

        this.visitor = new InteractiveVisitor(out, models);
    }
}
