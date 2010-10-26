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

package net.sourceforge.myjorganizer.gui;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.io.File;

import javax.swing.ActionMap;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import net.sourceforge.myjorganizer.gui.mvc.controller.TaskController;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;

public class MainView extends FrameView {
	private JTabbedPane mainPanel = new JTabbedPane();
	private JMenuBar menuBar = new JMenuBar();
	private JLabel statusBar = new JLabel(_("MSG_READY"));

	public MainView(Application application) {
		super(application);

		initComponents();
	}

	private void initComponents() {
		setComponent(mainPanel);
		setMenuBar(menuBar);
		setStatusBar(statusBar);

		ActionMap actionMap = getApplication().getContext().getActionMap(this);

		JMenu fileMenu = new JMenu(_("FILE_MENU"));

		JMenuItem importMenuItem = new JMenuItem();
		importMenuItem.setAction(actionMap.get("importFile"));
		importMenuItem.setText(_("FILE_IMPORT"));
		fileMenu.add(importMenuItem);

		JMenuItem exportMenuItem = new JMenuItem();
		exportMenuItem.setAction(actionMap.get("exportFile"));
		exportMenuItem.setText(_("FILE_EXPORT"));
		fileMenu.add(exportMenuItem);

		fileMenu.add(new JSeparator());

		JMenuItem exitMenuItem = new JMenuItem();
		exitMenuItem.setAction(actionMap.get("exit"));
		exitMenuItem.setText(_("EXIT"));
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		JMenu taskMenu = new JMenu(_("TASK_MENU"));

		JMenuItem taskNewMenuItem = new JMenuItem();
		taskMenu.add(taskNewMenuItem);

		taskNewMenuItem.setAction(actionMap.get("newTask"));
		taskNewMenuItem.setText(_("NEW_TASK"));

		menuBar.add(taskMenu);

		MyJOrganizerApp application = (MyJOrganizerApp) getApplication();
		new TaskController(application.getEntityManager(), mainPanel);

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

	@Action
	public void importFile() {
		JFileChooser chooser = new JFileChooser();
		int option = chooser.showOpenDialog(getFrame());
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
		}
	}

	@Action
	public void exportFile() {
	}
}
