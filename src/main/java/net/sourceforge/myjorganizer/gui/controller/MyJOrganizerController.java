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

package net.sourceforge.myjorganizer.gui.controller;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.sourceforge.myjorganizer.gui.MyJOrganizerApp;
import net.sourceforge.myjorganizer.gui.task.controller.TaskController;
import net.sourceforge.myjorganizer.gui.task.view.TaskSourceView;
import net.sourceforge.myjorganizer.gui.view.AddTaskFrame;
import net.sourceforge.myjorganizer.gui.view.MainView;
import net.sourceforge.myjorganizer.jpa.entities.Task;
import net.sourceforge.myjorganizer.parser.TaskListParser;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskCommands;
import net.sourceforge.myjorganizer.parser.visitor.TaskCreatingVisitor;

import org.jdesktop.application.Action;

/**
 * <p>MyJOrganizerController class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class MyJOrganizerController {
    private MyJOrganizerApp application;
    private MainView mainView;
    private TaskController taskController;

    /**
     * <p>Constructor for MyJOrganizerController.</p>
     *
     * @param application a {@link net.sourceforge.myjorganizer.gui.MyJOrganizerApp} object.
     */
    public MyJOrganizerController(MyJOrganizerApp application) {
        this.application = application;
        this.mainView = new MainView(application, application.getContext()
                .getActionMap(this));

        this.taskController = new TaskController(
                application.getEntityManager(), mainView.getMainPanel());
        application.show(mainView);
    }

    /**
     * <p>Getter for the field <code>application</code>.</p>
     *
     * @return a {@link net.sourceforge.myjorganizer.gui.MyJOrganizerApp} object.
     */
    public MyJOrganizerApp getApplication() {
        return this.application;
    }

    /**
     * <p>exit</p>
     */
    @Action
    public void exit() {
        getApplication().exit();
    }

    /**
     * <p>newTask</p>
     */
    @Action
    public void newTask() {
        new AddTaskFrame().setVisible(true);
    }

    /**
     * <p>loadSampleData</p>
     */
    @Action
    public void loadSampleData() {
        taskController.loadSampledata();
    }

    /**
     * <p>importFile</p>
     */
    @Action
    public void parseSource() {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(mainView.getFrame());
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            JLabel statusBar = (JLabel) mainView.getStatusBar();

            String errorMessage = null;

            try {
                TaskListParser parser = new TaskListParser(new FileReader(file));
                TaskCommands list = parser.TaskCommands();

                TaskCreatingVisitor tcv = new TaskCreatingVisitor();
                tcv.visit(list);
                ArrayList<Task> tasks = tcv.getVisitedTasks();

                taskController.getTaskSetModel().addMany(tasks);

            } catch (FileNotFoundException e) {
                statusBar.setText(errorMessage = _("FILE_NOT_FOUND"));
                errorMessage += "\n" + e.toString();
            } catch (Throwable e) {
                statusBar.setText(errorMessage = _("PARSE_ERROR"));
                errorMessage += "\n" + e.toString();
            }

            if (errorMessage != null) {
                JOptionPane.showMessageDialog(null, errorMessage, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * <p>exportFile</p>
     */
    @Action
    public void exportFile() {
        JFileChooser chooser = new JFileChooser();

        int option = chooser.showSaveDialog(mainView.getFrame());

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            try {
                FileWriter fw = new FileWriter(file);
                fw.write(TaskSourceView.formatSource(taskController
                        .getTaskSetModel()));
                fw.close();
            } catch (IOException e) {
                String errorMessage = _("IO_EXCEPTION");
                JOptionPane.showMessageDialog(null, errorMessage, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
