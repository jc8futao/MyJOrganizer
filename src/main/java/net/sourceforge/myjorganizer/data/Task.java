package net.sourceforge.myjorganizer.data;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Class representing a Task
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 */

@Entity
@Table(name="tasks")
public class Task {

	private String name;
	private Date dueDate;
	private Date startDate;
	private TaskStatus status;
	private int id;
	private int completion;
	private Priority priority = Priority.factory(false, false);

	public Task() {
	}

	public Task(String name) {
		this();

		this.setName(name);
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
	public Task setName(String name) {
		this.name = name;

		return this;
	}

	/**
	 * Gets the name of the task
	 * 
	 * @return name of the task
	 */
	public String getName() {
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
	@Column(name="due_date")
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
	@Column(name="start_date")
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
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	public TaskStatus getStatus() {
		return status;
	}

	public Task setId(int id) {
		this.id = id;

		return this;
	}

	@Id
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
	 * @return int (0 to 100)
	 */
	public int getCompletion() {
		return this.completion;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public boolean isUrgent() {
		return getPriority().isUrgent();
	}

	public Task setUrgent(boolean urgent) {
		this.priority = Priority.factory(urgent, isImportant());
		
		return this;
	}

	public boolean isImportant() {
		return priority.isImportant();
	}

	public Task setImportant(boolean important) {
		this.priority = Priority.factory(isUrgent(), important);
		
		return this;
	}

	public Task setPriority(Priority priority) {
		this.priority = priority;
		
		return this;
	}

	@Transient
	public Priority getPriority() {
		return this.priority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + completion;
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (completion != other.completion)
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
