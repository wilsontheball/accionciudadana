package ar.com.thinksoft.ac.actest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.cs.Db4oClientServer;

public class RepositoryTest {

	@Test
	public void testConexion() {
		ObjectContainer db = Db4oClientServer.openClient("localhost", 5555, "wilson", "wilson");
		ObjectSet<Object> objs = db.queryByExample(new Object());
		assertTrue(objs.size() >= 0);
	}
	
}
