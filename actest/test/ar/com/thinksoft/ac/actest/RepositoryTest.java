package ar.com.thinksoft.ac.actest;

import static org.junit.Assert.*;

import org.junit.Ignore;
import ar.com.thinksoft.ac.webac.repository.Repository;
import com.db4o.ObjectSet;

public class RepositoryTest {

	@Ignore
	public void testConexion() {
		ObjectSet<Object> objs = Repository.getInstance().queryByExample(new Object());
		assertTrue(objs.size() >= 0);
	}
	
}
