package net.sourceforge.myjorganizer.data;

import java.sql.SQLException;

public interface DatabaseLauncher {
	public void start() throws SQLException;
	public void stop();
}
