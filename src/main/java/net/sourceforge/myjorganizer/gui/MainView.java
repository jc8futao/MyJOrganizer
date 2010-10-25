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

import javax.swing.ActionMap;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import net.sourceforge.myjorganizer.gui.mvc.controller.TaskController;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;

public class MainView extends FrameView {
	private JTabbedPane mainPanel = new JTabbedPane();
	private JMenuBar menuBar;

	public MainView(Application application) {
		super(application);

		initComponents();
	}

	private void initComponents() {
		menuBar = new JMenuBar();

		setComponent(mainPanel);
		setMenuBar(menuBar);
		setStatusBar(new JLabel("hello world!"));

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
		
		MyJOrganizerApp application = (MyJOrganizerApp)getApplication();
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
	public void importFile()
	{
	}
	
	@Action
	public void exportFile()
	{
	}
}
