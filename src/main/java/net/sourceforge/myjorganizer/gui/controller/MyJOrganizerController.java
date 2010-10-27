package net.sourceforge.myjorganizer.gui.controller;

import java.io.File;

import javax.swing.JFileChooser;

import org.jdesktop.application.Action;

import net.sourceforge.myjorganizer.data.SampleData;
import net.sourceforge.myjorganizer.gui.MyJOrganizerApp;
import net.sourceforge.myjorganizer.gui.task.controller.TaskController;
import net.sourceforge.myjorganizer.gui.view.AddTaskFrame;
import net.sourceforge.myjorganizer.gui.view.MainView;

public class MyJOrganizerController {
	private MyJOrganizerApp application;
	private MainView mainView;

	public MyJOrganizerController(MyJOrganizerApp application) {
		this.application = application;
		this.mainView = new MainView(application, application.getContext()
				.getActionMap(this));
		
		new TaskController(application.getEntityManager(), mainView.getMainPanel());
		application.show(mainView);
	}

	public MyJOrganizerApp getApplication() {
		return this.application;
	}

	@Action
	public void exit() {
		getApplication().exit();
	}

	@Action
	public void newTask() {
		new AddTaskFrame().setVisible(true);
	}

	@Action
	public void loadSampleData() {
		SampleData.createSampleData(getApplication().getEntityManager());
	}

	@Action
	public void importFile() {
		JFileChooser chooser = new JFileChooser();
		int option = chooser.showOpenDialog(mainView.getFrame());
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
		}
	}

	@Action
	public void exportFile() {
	}
}
