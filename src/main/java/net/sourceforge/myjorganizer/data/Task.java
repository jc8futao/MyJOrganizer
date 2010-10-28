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
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
    private int id;
    private int completion;

    private Priority priority = Priority.factory(false, false);
    private String description;
    private String identifier;
    private Task parent;

    /**
     * <p>Constructor for Task.</p>
     */
    public Task() {
    }

    /**
     * <p>Constructor for Task.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public Task(String name) {
        this();

        this.setTitle(name);
    }

    /**
     * <p>Constructor for Task.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param priority a {@link net.sourceforge.myjorganizer.data.Priority} object.
     */
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
    @ShowInTable(position = 2)
    public String getTitle() {
        return name;
    }

    /**
     * Sets the task's due date
     *
     * @param dueDate a {@link java.util.Date} object.
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
     * @param startDate a {@link java.util.Date} object.
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
     * @param status a {@link net.sourceforge.myjorganizer.data.TaskStatus} object.
     * @return a {@link net.sourceforge.myjorganizer.data.Task} object.
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
    @ShowInTable
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id a int.
     * @return a {@link net.sourceforge.myjorganizer.data.Task} object.
     */
    public Task setId(int id) {
        this.id = id;

        return this;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a int.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ShowInTable(position = 0, editable = false)
    public int getId() {
        return this.id;
    }

    /**
     * <p>Setter for the field <code>completion</code>.</p>
     *
     * @param completion a int.
     * @return a {@link net.sourceforge.myjorganizer.data.Task} object.
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
        return this.getTitle();
    }

    /**
     * <p>Setter for the field <code>priority</code>.</p>
     *
     * @param priority a {@link net.sourceforge.myjorganizer.data.Priority} object.
     * @return a {@link net.sourceforge.myjorganizer.data.Task} object.
     */
    public Task setPriority(Priority priority) {
        this.priority = priority;

        return this;
    }

    /**
     * <p>Getter for the field <code>priority</code>.</p>
     *
     * @return a {@link net.sourceforge.myjorganizer.data.Priority} object.
     */
    @ManyToOne
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * <p>Setter for the field <code>description</code>.</p>
     *
     * @param description a {@link java.lang.String} object.
     * @return a {@link net.sourceforge.myjorganizer.data.Task} object.
     */
    public Task setDescription(String description) {
        this.description = description;

        return this;
    }

    /**
     * <p>Getter for the field <code>description</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @ShowInTable(position = 3)
    public String getDescription() {
        return this.description;
    }

    /**
     * <p>Getter for the field <code>identifier</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @Column(unique = true)
    @ShowInTable(position = 1)
    public String getIdentifier() {
        if (this.identifier == null && getId() != 0) {
            this.identifier = "$task" + getId();
        }

        return this.identifier;
    }

    /**
     * <p>Setter for the field <code>identifier</code>.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * <p>isUrgent</p>
     *
     * @return a boolean.
     */
    @Transient
    @ShowInTable(position = 4)
    public boolean isUrgent() {
        return this.getPriority().isUrgent();
    }

    /**
     * <p>setUrgent</p>
     *
     * @param urgent a boolean.
     */
    public void setUrgent(boolean urgent) {
        setPriority(Priority.factory(urgent, isImportant()));

    }

    /**
     * <p>isImportant</p>
     *
     * @return a boolean.
     */
    @Transient
    @ShowInTable(position = 5)
    public boolean isImportant() {
        return getPriority().isImportant();
    }

    /**
     * <p>setImportant</p>
     *
     * @param important a boolean.
     */
    public void setImportant(boolean important) {
        setPriority(Priority.factory(isUrgent(), important));
    }

    /**
     * <p>Setter for the field <code>parent</code>.</p>
     *
     * @param parent a {@link net.sourceforge.myjorganizer.data.Task} object.
     */
    public void setParent(Task parent) {
        this.parent = parent;
    }

    /**
     * <p>Getter for the field <code>parent</code>.</p>
     *
     * @return a {@link net.sourceforge.myjorganizer.data.Task} object.
     */
    @ManyToOne
    public Task getParent() {
        return this.parent;
    }
}
