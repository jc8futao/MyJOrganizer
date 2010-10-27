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

package net.sourceforge.myjorganizer.gui.view;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import javax.swing.ActionMap;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import net.sourceforge.myjorganizer.gui.MyJOrganizerApp;

import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;

public class MainView extends FrameView {
	private JTabbedPane mainPanel = new JTabbedPane();
	private JMenuBar menuBar = new JMenuBar();
	private JLabel statusBar = new JLabel(_("MSG_READY"));
	private ActionMap actionMap;

	public MainView(Application application, ActionMap actionMap) {
		super(application);
		this.actionMap = actionMap;

		initComponents();
	}

	private void initComponents() {
		setComponent(mainPanel);
		setMenuBar(menuBar);
		setStatusBar(statusBar);

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
		taskNewMenuItem.setAction(actionMap.get("newTask"));
		taskNewMenuItem.setText(_("NEW_TASK"));
		taskMenu.add(taskNewMenuItem);

		if (MyJOrganizerApp.DEBUG) {
			JMenuItem taskLoadSampleDataItem = new JMenuItem();
			taskLoadSampleDataItem.setAction(actionMap.get("loadSampleData"));
			taskLoadSampleDataItem.setText(_("TASK_SAMPLE_DATA"));
			taskMenu.add(taskLoadSampleDataItem);
		}

		menuBar.add(taskMenu);
		getFrame().pack();
	}

	public JTabbedPane getMainPanel() {
		return mainPanel;
	}
}
