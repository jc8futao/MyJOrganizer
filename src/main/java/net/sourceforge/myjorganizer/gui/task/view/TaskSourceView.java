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

package net.sourceforge.myjorganizer.gui.task.view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.parser.TaskSourceFormatter;

public class TaskSourceView extends AbstractTaskView implements Observer {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1394543402648078273L;
    private JTextArea textArea = new JTextArea();

    public TaskSourceView() {
        super(new GridLayout(1, 1));
        this.add(new JScrollPane(textArea));
        textArea.setEditable(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        textArea.setText(formatSource((TaskSetModel) o));
    }

    @Override
    public Observer getTaskSetModelObserver() {
        return this;
    }

    @Override
    public Observer getTaskStatusModelObserver() {
        return null;
    }

    public static String formatSource(TaskSetModel model) {
        return TaskSourceFormatter.formatSource(model.getList());
    }
}
