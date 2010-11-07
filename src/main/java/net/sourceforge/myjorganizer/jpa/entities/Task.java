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

package net.sourceforge.myjorganizer.jpa.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.davidebellettini.gui.utils.ShowInTable;

/**
 * Class representing a Task
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */

@Entity
@Table(name = "tasks")
public class Task {

    private String name;
    private Date dueDate;
    private Date startDate;
    private TaskStatus status;
    private int completion;

    private TaskPriority priority = TaskPriority.factory(false, false);
    private String description;
    private String id;
    private Set<TaskDependency> rightDeps = new HashSet<TaskDependency>();
    private Set<TaskDependency> leftDeps;

    /**
     * <p>
     * Constructor for Task.
     * </p>
     */
    public Task() {
    }

    /**
     * <p>
     * Constructor for Task.
     * </p>
     * 
     * @param name
     *            a {@link java.lang.String} object.
     */
    public Task(String name) {
        this(name, TaskPriority.factory(false, false));
    }

    /**
     * <p>
     * Constructor for Task.
     * </p>
     * 
     * @param name
     *            a {@link java.lang.String} object.
     * @param priority
     *            a
     *            {@link net.sourceforge.myjorganizer.jpa.entities.TaskPriority}
     *            object.
     */
    public Task(String name, TaskPriority priority) {
        setTitle(name);
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
    @ShowInTable(position = 2)
    public String getTitle() {
        return name;
    }

    /**
     * Sets the task's due date
     * 
     * @param dueDate
     *            a {@link java.util.Date} object.
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
    @ShowInTable
    public Date getDueDate() {
        return this.dueDate;
    }

    /**
     * Sets the task's start date
     * 
     * @param startDate
     *            a {@link java.util.Date} object.
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
    @ShowInTable
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * Task status setter
     * 
     * @param status
     *            a {@link net.sourceforge.myjorganizer.jpa.entities.TaskStatus}
     *            object.
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.Task} object.
     */
    public Task setStatus(TaskStatus status) {
        if (status != null && "closed".equals(status.getId())) {
            for (TaskDependency dep : getRightDependencies()) {
                if (!"closed".equals(dep.getLeft().getStatus().getId())) {
                    throw new IllegalStateException("Task "
                            + dep.getLeft().getId()
                            + " must be completed before " + getId());
                }
            }
        }

        this.status = status;

        return this;
    }

    /**
     * Status getter
     * 
     * @return the task status
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @ShowInTable
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * <p>
     * Setter for the field <code>completion</code>.
     * </p>
     * 
     * @param completion
     *            a int.
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.Task} object.
     */
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
    @Min(value = 0)
    @Max(value = 100)
    @ShowInTable(position = 6)
    public int getCompletion() {
        return this.completion;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.getId() + ": " + this.getTitle();
    }

    /**
     * <p>
     * Setter for the field <code>priority</code>.
     * </p>
     * 
     * @param priority
     *            a
     *            {@link net.sourceforge.myjorganizer.jpa.entities.TaskPriority}
     *            object.
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.Task} object.
     */
    public Task setPriority(TaskPriority priority) {
        this.priority = priority;

        return this;
    }

    /**
     * <p>
     * Getter for the field <code>priority</code>.
     * </p>
     * 
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.TaskPriority}
     *         object.
     */
    @ManyToOne
    public TaskPriority getPriority() {
        return this.priority;
    }

    /**
     * <p>
     * Setter for the field <code>description</code>.
     * </p>
     * 
     * @param description
     *            a {@link java.lang.String} object.
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.Task} object.
     */
    public Task setDescription(String description) {
        this.description = description;

        return this;
    }

    /**
     * <p>
     * Getter for the field <code>description</code>.
     * </p>
     * 
     * @return a {@link java.lang.String} object.
     */
    @ShowInTable(position = 3)
    public String getDescription() {
        return this.description;
    }

    /**
     * <p>
     * Getter for the field <code>identifier</code>.
     * </p>
     * 
     * @return a {@link java.lang.String} object.
     */
    @Id
    @Pattern(regexp = "^[a-zA-Z][A-Za-z0-9]*$")
    @Size(min = 1)
    @ShowInTable(position = 1, editable = false)
    public String getId() {
        return this.id;
    }

    /**
     * <p>
     * Setter for the field <code>identifier</code>.
     * </p>
     * 
     * @param identifier
     *            a {@link java.lang.String} object.
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.Task} object.
     */
    public Task setId(String identifier) {
        this.id = identifier;
        return this;
    }

    /**
     * <p>
     * isUrgent
     * </p>
     * 
     * @return a boolean.
     */
    @Transient
    @ShowInTable(position = 4)
    public boolean isUrgent() {
        return this.getPriority().isUrgent();
    }

    /**
     * <p>
     * setUrgent
     * </p>
     * 
     * @param urgent
     *            a boolean.
     * @return
     */
    public Task setUrgent(boolean urgent) {
        setPriority(TaskPriority.factory(urgent, isImportant()));

        return this;
    }

    /**
     * <p>
     * isImportant
     * </p>
     * 
     * @return a boolean.
     */
    @Transient
    @ShowInTable(position = 5)
    public boolean isImportant() {
        return getPriority().isImportant();
    }

    /**
     * <p>
     * setImportant
     * </p>
     * 
     * @param important
     *            a boolean.
     * @return
     */
    public Task setImportant(boolean important) {
        setPriority(TaskPriority.factory(isUrgent(), important));

        return this;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "right")
    public Set<TaskDependency> getRightDependencies() {
        return this.rightDeps;
    }

    @SuppressWarnings("unused")
    private void setRightDependencies(Set<TaskDependency> dependencies) {
        this.rightDeps = dependencies;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "left")
    public Set<TaskDependency> getLeftDependencies() {
        return this.leftDeps;
    }

    @SuppressWarnings("unused")
    private void setLeftDependencies(Set<TaskDependency> dependencies) {
        this.leftDeps = dependencies;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + completion;
        result = prime * result
                + ((rightDeps == null) ? 0 : rightDeps.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (rightDeps == null) {
            if (other.rightDeps != null)
                return false;
        } else if (!rightDeps.equals(other.rightDeps))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (dueDate == null) {
            if (other.dueDate != null)
                return false;
        } else if (!dueDate.equals(other.dueDate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
