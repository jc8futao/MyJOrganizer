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

package net.sourceforge.myjorganizer.gui.model;

import java.util.EventObject;

public class TaskDataEvent extends EventObject {

	private final String propertyName;

	public TaskDataEvent(Object source, String property) {
		super(source);

		this.propertyName = property;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2121847106167760586L;
}
