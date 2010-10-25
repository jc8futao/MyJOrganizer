/**
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

package net.sourceforge.myjorganizer.gui.mvc.view;

import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.gui.mvc.model.TaskSetModel;

public class TaskSourceView extends AbstractTaskView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1394543402648078273L;
	private JTextArea textArea = new JTextArea();

	public TaskSourceView() {
		super(new GridLayout(1,1));
		this.add(new JScrollPane(textArea));
	}

	@Override
	public void update(Observable o, Object arg) {
		TaskSetModel model = (TaskSetModel) o;
		
		textArea.setText("");
		
		for (Task task : model.getList()) {

			if (task.getId() > 0) {
				textArea.append("task " + task.getIdentifier() + ":\n");
			} else {
				textArea.append("task:\n");
			}
			textArea.append("    title: \"" + task.getTitle() + "\"\n");

			String description;
			if ((description = task.getDescription()) != null
					&& description.length() > 0) {
				textArea.append("    description: \"" + description + "\"\n");
			}

			textArea.append("    completion: " + task.getCompletion() + "%\n");
			textArea.append("    urgent: " + task.getPriority().isUrgent() + "\n");
			textArea.append("    important: " + task.getPriority().isImportant() + "\n");
			if (task.getStatus() != null)
				textArea.append("    status: " + task.getStatus() + "\n");
			textArea.append("end task\n\n");
		}
	}
}
