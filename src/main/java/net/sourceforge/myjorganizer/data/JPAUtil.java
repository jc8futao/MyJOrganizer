package net.sourceforge.myjorganizer.data;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.sourceforge.myjorganizer.gui.MyJOrganizerApp;

import org.h2.tools.Server;

public class JPAUtil {
	private static Server webServer;
	private static Server h2Server;

	public static EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("myjorganizer");
	}

	public static void startServers() throws SQLException {
		if (MyJOrganizerApp.DEBUG) {
			webServer = Server.createWebServer().start();
		}
		h2Server = Server.createTcpServer().start();
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
