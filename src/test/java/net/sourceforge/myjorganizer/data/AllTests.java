package net.sourceforge.myjorganizer.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	HibernateUtilTest.class,
	PriorityTest.class,
	TaskTest.class,
	TaskStatusTest.class
})
public class AllTests {

}
