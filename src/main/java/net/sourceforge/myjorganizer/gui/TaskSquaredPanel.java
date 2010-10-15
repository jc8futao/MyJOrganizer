package net.sourceforge.myjorganizer.gui;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.TransferHandler;

import net.sourceforge.myjorganizer.data.Priority;
import net.sourceforge.myjorganizer.gui.model.TaskListModel;
import net.sourceforge.myjorganizer.gui.model.TaskSquaredPanelModel;

public class TaskSquaredPanel extends JPanel {
	private final HashMap<Priority, TaskList> taskLists = new HashMap<Priority, TaskList>();
	private final TaskSquaredPanelModel model;
	private final TransferHandler transferHandler = new TaskTransferHandler();

	public TaskSquaredPanel(TaskSquaredPanelModel model) {
		super(new GridLayout(2, 2));
		this.model = model;

		initialize();
	}

	private void initialize() {
		Map<Priority, TaskListModel> models = model.getListModels();

		for (Entry<Priority, TaskListModel> entry : models.entrySet()) {
			Priority priority = entry.getKey();
			TaskListModel model = entry.getValue();

			TaskList taskList = new TaskList(getPriorityI18nString(priority),
					model, transferHandler );
			taskLists.put(priority, taskList);
			this.add(taskList);
		}
	}

	private String getPriorityI18nKey(Priority priority) {
		return (priority.isUrgent() ? "" : "NOT_") + "URGENT_"
				+ (priority.isImportant() ? "" : "NOT_") + "IMPORTANT";
	}

	private String getPriorityI18nString(Priority priority) {
		return _(getPriorityI18nKey(priority));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1366354102406219657L;
}
