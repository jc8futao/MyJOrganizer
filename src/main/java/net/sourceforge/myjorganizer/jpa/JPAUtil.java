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

package net.sourceforge.myjorganizer.jpa;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * <p>JPAUtil class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class JPAUtil {
    private static DatabaseLauncher databaseLauncher;

    static {
        databaseLauncher = new DummyDatabaseLauncher();
    }

    /**
     * <p>createEntityManagerFactory</p>
     *
     * @return a {@link javax.persistence.EntityManagerFactory} object.
     */
    public static EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("myjorganizer");
    }

    /**
     * <p>startServers</p>
     *
     * @throws java.sql.SQLException if any.
     */
    public static void startServers() throws SQLException {
        databaseLauncher.start();
    }

    /**
     * <p>shutdownServers</p>
     */
    public static void shutdownServers() {
        databaseLauncher.stop();
    }
}
