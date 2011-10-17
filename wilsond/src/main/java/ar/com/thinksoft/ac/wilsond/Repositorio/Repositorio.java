package ar.com.thinksoft.ac.wilsond.Repositorio;

import java.util.Comparator;

import ar.com.thinksoft.ac.wilsond.configuracion.ConfiguracionWilsonD;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.cs.Db4oClientServer;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.ext.ExtObjectContainer;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import com.db4o.query.QueryComparator;

/**
 * Representa un repositorio de reclamos. Implementa Singleton. Internamente se
 * comunica con la DB.
 * 
 * @since 24-09-2011
 * @author Paul
 */
public class Repositorio implements ObjectContainer{

	// Variable de clase que almacena la unica instancia (Singleton).
	private static Repositorio instancia;
	private static ObjectContainer objectContainer;

	/**
	 * Constructor privado redefiniendo el que viene por defecto (Singleton).
	 */
	private Repositorio() {
		try {
			ConfiguracionWilsonD.getInstance().cargarConfiguracion();
			String ip = ConfiguracionWilsonD.getInstance().getIpBD();
			int port = Integer.valueOf(ConfiguracionWilsonD.getInstance().getPortBD());
			objectContainer = Db4oClientServer.openClient(ip, port, "webac", "webac");
		} catch (Exception e) {
			objectContainer = Db4oClientServer.openClient("localhost", 5555, "webac", "webac");
		}
		
	}

	/**
	 * Devuelve la unica instancia de Repositorio (Singleton).
	 * 
	 * @since 24-09-2011
	 * @author Paul
	 * @return Instancia de Repositorio.
	 */
	public static Repositorio getInstancia() {

		if (instancia == null) {
			instancia = new Repositorio();
		}
		return instancia;
	}

	public void activate(Object arg0, int arg1) throws Db4oIOException, DatabaseClosedException {
		objectContainer.activate(arg0, arg1);
	}

	public boolean close() throws Db4oIOException {
		return objectContainer.close();
	}

	public void commit() throws Db4oIOException, DatabaseClosedException, DatabaseReadOnlyException {
		objectContainer.commit();
		
	}

	public void deactivate(Object arg0, int arg1) throws DatabaseClosedException {
		objectContainer.deactivate(arg0, arg1);
	}

	public void delete(Object arg0) throws Db4oIOException, DatabaseClosedException, DatabaseReadOnlyException {
		objectContainer.delete(arg0);
		this.commit();
	}

	public ExtObjectContainer ext() {
		return objectContainer.ext();
	}

	public Query query() throws DatabaseClosedException {
		return objectContainer.query();
	}

	public <TargetType> ObjectSet<TargetType> query(Class<TargetType> arg0)	throws Db4oIOException, DatabaseClosedException {
		return objectContainer.query(arg0);
	}

	public <TargetType> ObjectSet<TargetType> query(Predicate<TargetType> arg0)	throws Db4oIOException, DatabaseClosedException {
		return objectContainer.query(arg0);
	}

	public <TargetType> ObjectSet<TargetType> query(Predicate<TargetType> arg0, QueryComparator<TargetType> arg1) 
			throws Db4oIOException,	DatabaseClosedException {
		return objectContainer.query(arg0, arg1);
	}

	public <TargetType> ObjectSet<TargetType> query(Predicate<TargetType> arg0,	Comparator<TargetType> arg1) 
			throws Db4oIOException,	DatabaseClosedException {
		return objectContainer.query(arg0, arg1);
	}

	public <T> ObjectSet<T> queryByExample(Object arg0) throws Db4oIOException,	DatabaseClosedException {
		return objectContainer.queryByExample(arg0);
	}

	public void rollback() throws Db4oIOException, DatabaseClosedException,	DatabaseReadOnlyException {
		objectContainer.rollback();
	}

	public void store(Object arg0) throws DatabaseClosedException, DatabaseReadOnlyException {
		objectContainer.store(arg0);
		objectContainer.commit();
	}

	public void truncate() {
		objectContainer.delete(new Object());
	}
	
	public void storeOnly(Object arg0) throws DatabaseClosedException, DatabaseReadOnlyException {
		objectContainer.store(arg0);
	}
}
