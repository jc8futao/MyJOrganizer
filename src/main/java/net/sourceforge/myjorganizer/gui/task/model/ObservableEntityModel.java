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

package net.sourceforge.myjorganizer.gui.task.model;

import java.util.Observable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class ObservableEntityModel extends Observable {

	private final EntityManager entityManager;

	public ObservableEntityModel(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected EntityTransaction beginTransaction() {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		return tx;
	}

	protected void commitAndNotify(EntityTransaction tx) {
		tx.commit();
		setChanged();
		notifyObservers();
	}
}