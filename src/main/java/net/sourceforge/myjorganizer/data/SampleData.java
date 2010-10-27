package net.sourceforge.myjorganizer.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SampleData {

	public static void createSampleData(EntityManager em) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Task[] tasks = { new Task("Task 1"), new Task("Task 2"),
				new Task("Task 3"), new Task("Task 4") };

		tasks[1].setUrgent(true);
		tasks[2].setImportant(true);
		tasks[3].setUrgent(true);
		tasks[3].setImportant(true);

		int i = 0;
		for (Task task : tasks) {
			task.setIdentifier("$task" + ++i);
			em.persist(task.getPriority());
			em.persist(task);
		}

		tx.commit();
	}
}
