package ar.com.thinksoft.ac.webac.reclamo;

import java.util.*;

import com.db4o.query.Predicate;

import ar.com.thinksoft.ac.webac.repository.Repository;

/**
 * Administrador de reclamos
 * @author Matias
 *
 */
public class ReclamoManager {
	
	private static ReclamoManager instance;
	
	public ReclamoManager(){
	}
	
	public static ReclamoManager getInstance(){
		if(instance == null){
			instance = new ReclamoManager();
		}
		return instance;
	}
	
	/**
	 * @author Matias
	 */
	public void guardarReclamo(Reclamo reclamo){
		Repository.getInstance().store(reclamo);
	}
	
	/**
	 * Metodo para guardar una coleccion de Reclamos.
	 * Justificacion de este metodo: si quiero guardar una coleccion de Reclamos la guarda como tal,
	 * por eso me interesa guardar cada uno de los reclamos que conforma la coleccion.
	 * @param listaReclamos
	 * @author Matias
	 */
	public void guardarColeccionReclamos(List<Reclamo> listaReclamos){
		for(Reclamo reclamo : listaReclamos){
			guardarReclamo(reclamo);
		}
		
	}
	
	/**
	 * Devuelve una lista de Reclamos
	 * @author Matias
	 */
	public List<Reclamo> obtenerTodosReclamos(){
		List<Reclamo> listaReclamos = new ArrayList<Reclamo>();
		List<Reclamo> setReclamos = Repository.getInstance().query(Reclamo.class);
		
		for(Reclamo reclamo:setReclamos){
			listaReclamos.add(reclamo);
		}
		
		return listaReclamos;
	}
	
	/**
	 * @author Matias
	 */
	public List<Reclamo> obtenerReclamosFiltrados(Predicate<Reclamo> predicado){
		return  Repository.getInstance().query(predicado);
	}
	
	/**
	 * @author Matias
	 */
	public void eliminarTodosReclamos(){
		List<Reclamo> objs = Repository.getInstance().queryByExample(Reclamo.class);
		for(Reclamo reclamo : objs){
			Repository.getInstance().delete(reclamo);
		}
	}
	

}
