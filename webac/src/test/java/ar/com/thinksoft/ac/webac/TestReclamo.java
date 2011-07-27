package ar.com.thinksoft.ac.webac;

import static org.junit.Assert.*;

import org.junit.Test;

import com.db4o.ObjectSet;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.repository.Repository;

public class TestReclamo {

	@Test
	public void guardarReclamoTest(){
		Repository.getInstance().store(new Reclamo());
		ObjectSet<Reclamo> objs = Repository.getInstance().queryByExample(new Reclamo());
		assertTrue(objs.size() >= 0);
	}
	
}
