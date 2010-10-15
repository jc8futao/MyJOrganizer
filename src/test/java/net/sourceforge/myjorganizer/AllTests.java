package net.sourceforge.myjorganizer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	net.sourceforge.myjorganizer.data.AllTests.class,
	net.sourceforge.myjorganizer.gui.AllTests.class,
	net.sourceforge.myjorganizer.i18n.AllTests.class
})
public class AllTests {

}
