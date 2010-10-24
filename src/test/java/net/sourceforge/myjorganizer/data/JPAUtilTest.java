package net.sourceforge.myjorganizer.data;

import java.sql.SQLException;

import org.junit.*;
import static org.junit.Assert.*;

public class JPAUtilTest {

	@Before
	public void setUp() throws SQLException {
		JPAUtil.startServers();
	}

	@After
	public void tearDown() {
		JPAUtil.shutdownServers();
	}

	@Test
	public void testCreateSession() {
		assertNotNull(JPAUtil.createEntityManagerFactory());
	}
}
