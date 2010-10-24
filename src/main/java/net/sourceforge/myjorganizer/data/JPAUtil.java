package net.sourceforge.myjorganizer.data;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static DatabaseLauncher databaseLauncher;

	static {
		databaseLauncher = new DummyDatabaseLauncher();
	}

	public static EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("myjorganizer");
	}

	public static void startServers() throws SQLException {
		databaseLauncher.start();
	}

	public static void shutdownServers() {
		databaseLauncher.stop();
	}
}
