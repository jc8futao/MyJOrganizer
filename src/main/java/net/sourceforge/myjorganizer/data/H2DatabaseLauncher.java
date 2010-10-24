package net.sourceforge.myjorganizer.data;

import java.sql.SQLException;

import org.h2.tools.Server;

public class H2DatabaseLauncher implements DatabaseLauncher {
	private Server webServer;
	private Server h2Server;

	private boolean debug;

	public H2DatabaseLauncher(boolean debug) {
		this.debug = debug;
	}

	public H2DatabaseLauncher() {
		this(false);
	}

	@Override
	public void start() throws SQLException {
		if (debug) {
			webServer = Server.createWebServer();
		}
		h2Server = Server.createTcpServer();
	}

	@Override
	public void stop() {
		if (webServer != null)
			webServer.stop();

		if (h2Server != null)
			h2Server.stop();
	}
}
