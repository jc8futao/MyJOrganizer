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

package net.sourceforge.myjorganizer.jpa.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TaskTest {
    private Task task;

    @Before
    public void setup() {
        task = new Task();
    }

    @Test
    // fluent interface
    public void testNameSetterReturnsTask() {
        assertEquals(task, task.setTitle(null));
    }

    @Test
    public void testTitleAccessors() {
        String name = "piripicchio";
        task.setTitle(name);

        assertEquals(name, task.getTitle());
    }

    @Test
    public void testDescriptionAccessors() {
        String description = "piripicchio";
        task.setDescription(description);

        assertEquals(description, task.getDescription());
    }

    @Test
    public void testConstructorNameAndPriority() {
        String name = "hello";
        TaskPriority priority = TaskPriority.factory(true, true);

        task = new Task(name, priority);

        assertEquals(name, task.getTitle());
        assertEquals(priority, task.getPriority());
    }

    @Test
    // fluent interface
    public void testStartDateSetterReturnsTask() {
        assertEquals(task, task.setStartDate(null));
    }

    @Test
    public void testStartDateAccessors() {
        Date date = new Date();
        task.setStartDate(date);

        assertEquals(date, task.getStartDate());
    }

    @Test
    // fluent interface
    public void testDueDateSetterIsFluent() {
        assertEquals(task, task.setDueDate(null));
    }

    @Test
    public void testDueDateAccessors() {
        Date date = new Date();
        task.setDueDate(date);

        assertEquals(date, task.getDueDate());
    }

    @Test
    public void testStatusAccessors() {
        TaskStatus status = new TaskStatus("test");
        task.setStatus(status);

        assertEquals(status, task.getStatus());
    }

    @Test(expected=IllegalStateException.class)
    public void testClosingWithOpenLeftDependencies() {
        Task left = new Task("left");
        left.setId("left");
        
        left.setStatus(new TaskStatus("open"));
        task.setId("teskedTask");
        
        task.getRightDependencies().add(TaskDependency.before(left, task));
        
        task.setStatus(new TaskStatus("closed"));
    }

    @Test
    public void testStatusSetterIsFluent() {
        assertEquals(task, task.setStatus(null));
    }

    @Test
    public void testIdAccessors() {
        String id = "1";
        task.setId(id);

        assertEquals(id, task.getId());
    }

    @Test
    public void testIdSetterReturnsTask() {
        assertSame(task, task.setId("1"));
    }

    @Test
    public void testCompletionAccessors() {
        for (int i = 0; i <= 100; i++) {
            task.setCompletion(i);
            assertEquals(i, task.getCompletion());
        }
    }

    @Test
    public void testCompletionSetterIsFluent() {
        assertEquals(task, task.setCompletion(10));
    }

    @Test
    public void testToString() {

        task.setId("donald");
        task.setTitle("DonaldDuck");
        assertEquals("donald: DonaldDuck", task.toString());
    }

    @Test
    public void testPriorityAccessors() {
        task.setPriority(TaskPriority.factory(true, true));

        assertEquals(TaskPriority.factory(true, true), task.getPriority());
    }

    @Test
    public void testPrioritySetterIsFluent() {
        assertEquals(task, task.setPriority(null));
    }

    @Test
    public void testIdentifierAccessors() {
        task.setId("$pippo");

        assertEquals("$pippo", task.getId());
    }

    @Test
    public void testUrgentAccessors() {
        assertFalse(task.isUrgent());
        task.setUrgent(true);
        assertTrue(task.isUrgent());
    }

    @Test
    public void testSetUrgentReturnsTask() {
        assertEquals(task, task.setUrgent(true));
    }

    @Test
    public void testImportantAccessors() {
        assertFalse(task.isImportant());
        task.setImportant(true);
        assertTrue(task.isImportant());
    }

    @Test
    public void testSetImportantReturnsTask() {
        assertEquals(task, task.setImportant(true));
    }

    @Test
    public void testEquals() {
        assertEquals(new Task("Hello"), new Task("Hello"));
    }

    @Test
    public void testHashCode() {
        assertEquals(new Task("Hello").hashCode(), new Task("Hello").hashCode());
    }
}
