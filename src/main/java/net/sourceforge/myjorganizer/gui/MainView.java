package net.sourceforge.myjorganizer.gui;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import javax.swing.ActionMap;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import net.sourceforge.myjorganizer.data.Priority;
import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.gui.model.TaskModel;
import net.sourceforge.myjorganizer.gui.model.TaskSquaredPanelModel;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;

public class MainView extends FrameView {
	private JPanel mainPanel;
	private JMenuBar menuBar;

	public MainView(Application application) {
		super(application);

		initComponents();
	}

	private void initComponents() {
		mainPanel = new JPanel();
		menuBar = new JMenuBar();

		setComponent(mainPanel);
		setMenuBar(menuBar);

		JMenu fileMenu = new JMenu(_("FILE_MENU"));
		JMenuItem exitMenuItem = new JMenuItem();
		fileMenu.add(exitMenuItem);

		ActionMap actionMap = getApplication().getContext().getActionMap(this);
		exitMenuItem.setAction(actionMap.get("exit"));
		exitMenuItem.setText(_("EXIT"));

		JMenu taskMenu = new JMenu(_("TASK_MENU"));
		JMenuItem taskNewMenuItem = new JMenuItem();
		taskMenu.add(taskNewMenuItem);
		taskNewMenuItem.setAction(actionMap.get("newTask"));
		taskNewMenuItem.setText(_("NEW_TASK"));

		menuBar.add(fileMenu);
		menuBar.add(taskMenu);
		
		TaskSquaredPanelModel model;
		mainPanel.add(new TaskSquaredPanel(model = new TaskSquaredPanelModel(
				Priority.getAll())));

		model.add(new TaskModel(new Task("ciao1", Priority.factory(false, false))));
		model.add(new TaskModel(new Task("ciao2", Priority.factory(false, true))));
		model.add(new TaskModel(new Task("ciao3", Priority.factory(true, false))));
		model.add(new TaskModel(new Task("ciao4", Priority.factory(true, true))));

		getFrame().pack();
	}

	@Action
	public void exit() {
		getApplication().exit();
	}

	@Action
	public void newTask() {
		new AddTaskFrame().setVisible(true);
	}
}
