/*
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

import org.h2.tools.Server;

/**
 * <p>H2DatabaseLauncher class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class H2DatabaseLauncher implements DatabaseLauncher {
    private Server webServer;
    private Server h2Server;

    private boolean debug;

    /**
     * <p>Constructor for H2DatabaseLauncher.</p>
     *
     * @param debug a boolean.
     */
    public H2DatabaseLauncher(boolean debug) {
        this.debug = debug;
    }

    /**
     * <p>Constructor for H2DatabaseLauncher.</p>
     */
    public H2DatabaseLauncher() {
        this(false);
    }

    /** {@inheritDoc} */
    @Override
    public void start() throws SQLException {
        if (debug) {
            webServer = Server.createWebServer();
        }
        h2Server = Server.createTcpServer();
    }

    /** {@inheritDoc} */
    @Override
    public void stop() {
        if (webServer != null)
            webServer.stop();

        if (h2Server != null)
            h2Server.stop();
    }
}
