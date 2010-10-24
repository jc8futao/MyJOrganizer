package net.sourceforge.myjorganizer.data;

import java.sql.SQLException;

public class DummyDatabaseLauncher implements DatabaseLauncher {

	@Override
	public void start() throws SQLException {
	}

	@Override
	public void stop() {
	}
}
