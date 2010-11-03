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

package net.sourceforge.myjorganizer.gui;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.EventObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

import net.sourceforge.myjorganizer.gui.controller.MyJOrganizerController;
import net.sourceforge.myjorganizer.jpa.JPAUtil;
import net.sourceforge.myjorganizer.jpa.entities.BasicDataLoader;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * <p>MyJOrganizerApp class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public class MyJOrganizerApp extends SingleFrameApplication {
    /** Constant <code>DEBUG=true</code> */
    public final static boolean DEBUG = true;

    private String language;
    private EntityManagerFactory emFactory;
    private EntityManager entityManager;

    /**
     * <p>Constructor for MyJOrganizerApp.</p>
     */
    public MyJOrganizerApp() {
        getContext()
                .getResourceManager()
                .setApplicationBundleNames(
                        Arrays.asList("net.sourceforge.myjorganizer.gui.resources.MyJOrganizerApp"));
    }

    /**
     * <p>main</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String args[]) {
        launch(MyJOrganizerApp.class, args);
    }

    /**
     * <p>getApplication</p>
     *
     * @return a {@link net.sourceforge.myjorganizer.gui.MyJOrganizerApp} object.
     */
    public static MyJOrganizerApp getApplication() {
        return Application.getInstance(MyJOrganizerApp.class);
    }

    /**
     * <p>getEntityManagerFactory</p>
     *
     * @return a {@link javax.persistence.EntityManagerFactory} object.
     */
    public EntityManagerFactory getEntityManagerFactory() {
        return emFactory;
    }

    /**
     * <p>Getter for the field <code>language</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * <p>Getter for the field <code>entityManager</code>.</p>
     *
     * @return a {@link javax.persistence.EntityManager} object.
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /** {@inheritDoc} */
    @Override
    protected void startup() {
        registerExitListener();

        try {
            JPAUtil.startServers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.emFactory = JPAUtil.createEntityManagerFactory();
        this.entityManager = emFactory.createEntityManager();
        
        BasicDataLoader.ensureBasicData(this.entityManager);

        new MyJOrganizerController(this);
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
                entityManager.close();
                emFactory.close();
                JPAUtil.shutdownServers();
            }
        };
        addExitListener(maybeExit);
    }
}
