package net.sourceforge.myjorganizer.gui;

import java.io.InputStream;
import java.io.PrintStream;

import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel;
import net.sourceforge.myjorganizer.parser.TaskCommandsParser;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskCommand;
import net.sourceforge.myjorganizer.parser.visitor.InteractiveVisitor;

/**
 * This thread reads input from the console parsing given commands
 * 
 * @author davide
 * 
 */
public class ConsoleThread extends Thread {

    private TaskCommandsParser parser;
    private PrintStream err;
    private InteractiveVisitor visitor;
    private InputStream in;

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

    public ConsoleThread(TaskSetModel taskModel, TaskStatusModel statusModel) {
        this(System.in, System.out, System.err, taskModel, statusModel);
    }

    public ConsoleThread(InputStream in, PrintStream out, PrintStream err,
            TaskSetModel taskModel, TaskStatusModel statusModel) {
        this.in = in;
        this.parser = new TaskCommandsParser(in);
        this.err = err;

        this.visitor = new InteractiveVisitor(out, taskModel, statusModel);
    }
}
