package net.sourceforge.myjorganizer.gui.mvc.view;

import java.awt.event.InputMethodListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.gui.mvc.model.TaskSetModel;

public class TaskSourceView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1394543402648078273L;
	private JTextArea textArea = new JTextArea();

	public TaskSourceView() {
		this.add(new JScrollPane(this.textArea));
	}

	@Override
	public void update(Observable o, Object arg) {
		TaskSetModel model = (TaskSetModel) o;

		textArea.setText("");

		for (Task task : model.getList()) {
			textArea.append("task:\n");
			textArea.append("    title: \"" + task.getTitle() + "\"\n");

			String description;
			if ((description = task.getDescription()) != null
					&& description.length() > 0) {
				textArea.append("    description: \"" + description + "\"");
			}

			textArea.append("    completion: " + task.getCompletion() + "%\n");
			textArea.append("    urgent: " + task.isUrgent() + "\n");
			textArea.append("    important: " + task.isImportant() + "\n");
			if (task.getStatus() != null)
				textArea.append("    status: " + task.getStatus() + "\n");
			textArea.append("end task\n");
		}
	}

	public void addInputMethodListener(InputMethodListener l) {
		textArea.addInputMethodListener(l);
	}
}
