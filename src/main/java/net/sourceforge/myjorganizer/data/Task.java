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

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * Class representing a Task
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 */

@Entity
@Table(name = "tasks")
public class Task {

	private String name;
	private Date dueDate;
	private Date startDate;
	private TaskStatus status;
	private int id;
	private int completion;
	
	private Priority priority = Priority.factory(false, false);
	private String description;
	private String identifier;

	public Task() {
	}

	public Task(String name) {
		this();

		this.setTitle(name);
	}

	public Task(String name, Priority priority) {
		this(name);

		setPriority(priority);
	}

	/**
	 * Sets the name of the task
	 * 
	 * @param name
	 *            of the task
	 * @return Task (fluent interface)
	 */
	public Task setTitle(String name) {
		this.name = name;

		return this;
	}

	/**
	 * Gets the name of the task
	 * 
	 * @return name of the task
	 */
	@Column(nullable = false)
	public String getTitle() {
		return name;
	}

	/**
	 * Sets the task's due date
	 * 
	 * @param dueDate
	 * @return Task (fluent interface)
	 */
	public Task setDueDate(Date dueDate) {
		this.dueDate = dueDate;

		return this;
	}

	/**
	 * Due date getter
	 * 
	 * @return the task's due date
	 */
	@Column(name = "due_date")
	public Date getDueDate() {
		return this.dueDate;
	}

	/**
	 * Sets the task's start date
	 * 
	 * @param startDate
	 * @return the task (fluent interface)
	 */
	public Task setStartDate(Date startDate) {
		this.startDate = startDate;

		return this;
	}

	/**
	 * Gets the start date
	 * 
	 * @return the start date
	 */
	@Column(name = "start_date")
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Task status setter
	 * 
	 * @param status
	 * @return
	 */
	public Task setStatus(TaskStatus status) {
		this.status = status;

		return this;
	}

	/**
	 * Status getter
	 * 
	 * @return the task status
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public TaskStatus getStatus() {
		return status;
	}

	public Task setId(int id) {
		this.id = id;

		return this;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public Task setCompletion(int completion) {
		if (completion < 0 || completion > 100)
			throw new IllegalArgumentException(
					"Completion must be between 0 and 100");

		this.completion = completion;

		return this;
	}

	/**
	 * Completion getter
	 * 
	 * @return int (0 to 100)
	 */
	@Min(value=0) 
	@Max(value=100)
	public int getCompletion() {
		return this.completion;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}

	public Task setPriority(Priority priority) {
		this.priority = priority;

		return this;
	}

	@ManyToOne
	public Priority getPriority() {
		return this.priority;
	}

	public Task setDescription(String description) {
		this.description = description;

		return this;
	}

	public String getDescription() {
		return this.description;
	}

	@Column(unique=true)
	public String getIdentifier() {
		if (this.identifier == null) {
			this.identifier = "$task" + getId();
		}
		
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
