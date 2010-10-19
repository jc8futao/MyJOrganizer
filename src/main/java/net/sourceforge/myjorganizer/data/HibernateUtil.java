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

package net.sourceforge.myjorganizer.data;

import java.sql.SQLException;

import net.sourceforge.myjorganizer.gui.MyJOrganizerApp;

import org.h2.tools.Server;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	private static Server webServer;
	private static Server h2Server;

	static {
		try {
			try {
				if (MyJOrganizerApp.DEBUG) {
					webServer = Server.createWebServer().start();
				}
				h2Server = Server.createTcpServer().start();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			sessionFactory = new Configuration().configure()
					.buildSessionFactory();

		} catch (Throwable ex) {
			// Log exception!
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdownServers() {
		if (h2Server != null) {
			h2Server.shutdown();
		}

		if (webServer != null) {
			webServer.shutdown();
		}
	}
}
