package ar.com.wilson.ac.repository;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;

public class DataBase {
	
	private final int port = 5555;

	private ObjectServer server;
	
	/**
	 * Arranca el motor del repositorio
	 */
	public void start() {
		this.server = Db4oClientServer.openServer("repository.db", port);
		this.server.grantAccess("webac", "webac");
		this.server.grantAccess("wilsond", "wilsond");
		
	}
	
	public void stop(){
		this.server.close();
	}
}
