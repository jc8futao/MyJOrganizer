package net.sourceforge.myjorganizer.jpa.entities;

import javax.persistence.EntityManager;

public class BasicDataLoader {
    public static void ensurePriorities(EntityManager em) {
        for (Priority p : Priority.getAll()) {
            if (null == em.find(Priority.class, p)) {
                em.persist(p);
            }
        }
    }

    public static void ensureStatuses(EntityManager em) {
        TaskStatus[] statuses = { TaskStatus.OPEN, TaskStatus.CLOSED };

        for (TaskStatus status : statuses) {
            if (null == em.find(TaskStatus.class, status.getId())) {
                em.persist(status);
            } else {
                em.merge(status);
            }
        }
    }

    public static void ensureBasicData(EntityManager em) {
        ensurePriorities(em);
        ensureStatuses(em);
    }
}
