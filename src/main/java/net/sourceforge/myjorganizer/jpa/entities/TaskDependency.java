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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Represents a dependency between tasks
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
@Entity
@Table(name = "task_dependencies", uniqueConstraints=@UniqueConstraint(columnNames={"left_id","right_id"}))
public class TaskDependency {

    private Task left;
    private Task right;
    private int id;

    private TaskDependency() {
    }

    private TaskDependency(Task left, Task right) {
        this.setLeft(left);
        this.setRight(right);
    }

    /**
     * <p>
     * before
     * </p>
     * 
     * @param left
     *            a {@link net.sourceforge.myjorganizer.jpa.entities.Task}
     *            object.
     * @param right
     *            a {@link net.sourceforge.myjorganizer.jpa.entities.Task}
     *            object.
     * @return a
     *         {@link net.sourceforge.myjorganizer.jpa.entities.TaskDependency}
     *         object.
     */
    public static TaskDependency before(Task left, Task right) {
        return new TaskDependency(left, right);
    }

    /**
     * <p>
     * Getter for the field <code>left</code>.
     * </p>
     * 
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.Task} object.
     */
    @ManyToOne
    public Task getLeft() {
        return this.left;
    }

    /**
     * <p>
     * Getter for the field <code>right</code>.
     * </p>
     * 
     * @return a {@link net.sourceforge.myjorganizer.jpa.entities.Task} object.
     */
    @ManyToOne
    public Task getRight() {
        return this.right;
    }

    /**
     * <p>
     * Getter for the field <code>id</code>.
     * </p>
     * 
     * @return a int.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * <p>
     * Setter for the field <code>id</code>.
     * </p>
     * 
     * @param id
     *            a int.
     */
    public void setId(int id) {
        this.id = id;
    }

    private void setLeft(Task left) {
        this.left = left;
    }

    private void setRight(Task right) {
        this.right = right;
    }

    /**
     * <p>
     * after
     * </p>
     * 
     * @param left
     *            a {@link net.sourceforge.myjorganizer.jpa.entities.Task}
     *            object.
     * @param right
     *            a {@link net.sourceforge.myjorganizer.jpa.entities.Task}
     *            object.
     * @return a
     *         {@link net.sourceforge.myjorganizer.jpa.entities.TaskDependency}
     *         object.
     */
    public static TaskDependency after(Task left, Task right) {
        return before(right, left);
    }

    public String toString() {
        return left.getId() + " before " + right.getId();
    }
}
