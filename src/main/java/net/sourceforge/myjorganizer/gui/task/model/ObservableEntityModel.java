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

package net.sourceforge.myjorganizer.gui.task.model;

import java.util.Observable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * <p>Abstract ObservableEntityModel class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public abstract class ObservableEntityModel extends Observable {

    private final EntityManager entityManager;

    /**
     * <p>Constructor for ObservableEntityModel.</p>
     *
     * @param entityManager a {@link javax.persistence.EntityManager} object.
     */
    public ObservableEntityModel(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * <p>Getter for the field <code>entityManager</code>.</p>
     *
     * @return a {@link javax.persistence.EntityManager} object.
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * <p>beginTransaction</p>
     *
     * @return a {@link javax.persistence.EntityTransaction} object.
     */
    protected EntityTransaction beginTransaction() {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        return tx;
    }

    /**
     * <p>commitAndNotify</p>
     *
     * @param tx a {@link javax.persistence.EntityTransaction} object.
     */
    protected void commitAndNotify(EntityTransaction tx) {
        tx.commit();
        setChanged();
        notifyObservers();
    }
}
