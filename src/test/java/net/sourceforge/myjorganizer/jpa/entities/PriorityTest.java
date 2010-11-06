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
import static org.junit.Assert.assertSame;

import net.sourceforge.myjorganizer.jpa.entities.TaskPriority;

import org.junit.Test;

public class PriorityTest {

    @Test
    public void testUrgentGetter() {
        boolean urgent = true;

        TaskPriority priority = TaskPriority.factory(urgent, true);
        assertEquals(urgent, priority.isUrgent());

        urgent = !urgent;

        priority = TaskPriority.factory(urgent, true);
        assertEquals(urgent, priority.isUrgent());
    }

    @Test
    public void testImportantGetter() {
        boolean important = true;

        TaskPriority priority = TaskPriority.factory(true, important);
        assertEquals(important, priority.isImportant());

        important = !important;

        priority = TaskPriority.factory(true, important);
        assertEquals(important, priority.isImportant());
    }

    @Test
    public void testFlyweight() {

        boolean[][] values = { { false, false }, { false, true },
                { true, false }, { true, true } };

        for (boolean[] currentValues : values)
            assertSame(TaskPriority.factory(currentValues[0], currentValues[1]),
                    TaskPriority.factory(currentValues[0], currentValues[1]));
    }
}
