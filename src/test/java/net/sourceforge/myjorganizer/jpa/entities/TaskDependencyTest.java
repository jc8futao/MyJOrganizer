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

import net.sourceforge.myjorganizer.jpa.entities.Task;
import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;

import org.junit.Before;
import org.junit.Test;

public class TaskDependencyTest {

    private Task left;
    private Task right;

    @Before
    public void setUp() {
        left = new Task("Left");
        left.setId("left");
        right = new Task("Right");
        right.setId("right");
    }

    @Test
    public void testLeftAccessors() {

        TaskDependency td = TaskDependency.before(left, right);

        assertEquals(left, td.getLeft());
    }

    @Test
    public void testRightAccessors() {
        TaskDependency td = TaskDependency.before(left, right);

        assertEquals(right, td.getRight());
    }

    @Test
    public void testTypeGetter() {
        TaskDependency td = TaskDependency.before(left, right);

        assertEquals("before", td.getDependencyType());
    }

    @Test
    public void testAfter() {
        TaskDependency td = TaskDependency.after(left, right);

        assertEquals("before", td.getDependencyType());
        assertEquals(right, td.getLeft());
        assertEquals(left, td.getRight());
    }

    @Test
    public void testToString() {
        TaskDependency td = TaskDependency.before(left, right);
        
        assertEquals("left before right", td.toString());
    }
}
