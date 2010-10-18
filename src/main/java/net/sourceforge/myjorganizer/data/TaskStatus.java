package net.sourceforge.myjorganizer.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="task_statuses")
public class TaskStatus {
	private String name;
	
	private int id;

	private TaskStatus() {
	}

	public TaskStatus(String name) {
		this();

		setName(name);
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TaskStatus other = (TaskStatus) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	private void setName(String name) {
		this.name = name;
	}

	@Id
	public int getId() {
		return id;
	}

	public TaskStatus setId(int id) {
		this.id = id;
		
		return this;
	}
}
