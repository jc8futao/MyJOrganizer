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

import java.util.Arrays;
import java.util.EventObject;

import javax.swing.JOptionPane;

import net.sourceforge.myjorganizer.data.HibernateUtil;

import org.hibernate.SessionFactory;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

public class MyJOrganizerApp extends SingleFrameApplication {
	public final static boolean DEBUG = true;

	public MyJOrganizerApp() {
		getContext()
				.getResourceManager()
				.setApplicationBundleNames(
						Arrays
								.asList("net.sourceforge.myjorganizer.gui.resources.MyJOrganizerApp"));
	}

	public static void main(String args[]) {
		launch(MyJOrganizerApp.class, args);
	}

	public static MyJOrganizerApp getApplication() {
		return Application.getInstance(MyJOrganizerApp.class);
	}

	private String language;

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	protected void startup() {
		registerExitListener();

		this.sessionFactory = HibernateUtil.getSessionFactory();

		show(new MainView(this));
	}

	private void registerExitListener() {
		ExitListener maybeExit = new ExitListener() {
			@Override
			public boolean canExit(EventObject e) {
				int option = JOptionPane.showConfirmDialog(null,
						_("REALLY_EXIT"), _("EXIT"), JOptionPane.YES_NO_OPTION);
				return option == JOptionPane.YES_OPTION;
			}

			@Override
			public void willExit(EventObject e) {
				HibernateUtil.shutdownServers();
			}
		};
		addExitListener(maybeExit);
	}

	public String getLanguage() {
		return this.language;
	}
}
