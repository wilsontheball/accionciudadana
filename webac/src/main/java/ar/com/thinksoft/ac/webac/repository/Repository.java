package ar.com.thinksoft.ac.webac.repository;

import com.db4o.ObjectContainer;
import com.db4o.cs.Db4oClientServer;

public class Repository {
	
	private static Repository repository;
	private static ObjectContainer objectContainer;
	
	public Repository(){
		objectContainer = Db4oClientServer.openClient("localhost", 5555, "wilson", "wilson");
	}
	
	public Repository getInstance(){
		
		if(repository == null){
			repository = new Repository();
		}
		return repository;
	}
	
	public ObjectContainer getDB(){
		return this.objectContainer;
	}
	
}
