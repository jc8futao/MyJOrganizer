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
package net.sourceforge.myjorganizer.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Represents a dependency between tasks
 */
@Entity
@Table(name="task_dependencies")
public class TaskDependency {

	private Task left;
	private Task right;
	private String type;
	private int id;

	private TaskDependency() {
	}

	private TaskDependency(String type, Task left, Task right) {
		this.setDependencyType(type);
		this.setLeft(left);
		this.setRight(right);
	}

	private void setDependencyType(String type) {
		this.type = type;
	}

	public static TaskDependency before(Task left, Task right) {
		return new TaskDependency("before", left, right);
	}

	@ManyToOne
	public Task getLeft() {
		return this.left;
	}

	@ManyToOne
	public Task getRight() {
		return this.right;
	}

	public String getDependencyType() {
		return this.type;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private void setLeft(Task left) {
		this.left = left;
	}

	private void setRight(Task right) {
		this.right = right;
	}

	public static TaskDependency after(Task left, Task right) {
		return before(right, left);
	}
}
