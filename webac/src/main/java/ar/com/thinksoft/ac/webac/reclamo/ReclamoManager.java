package ar.com.thinksoft.ac.webac.reclamo;

import java.util.*;

import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.predicates.PredicateReclamo;
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
	public void guardarReclamo(IReclamo reclamo){
		Repository.getInstance().store(reclamo);
	}
	
	/**
	 * Método para guardar una colección de Reclamos.
	 * Justificación de este método: si quiero guardar una colección de Reclamos la guarda como tal,
	 * por eso me interesa guardar cada uno de los reclamos que conforma la colección.
	 * @param listaReclamos
	 * @author Matias
	 */
	public void guardarColeccionReclamos(List<IReclamo> listaReclamos){
		for(IReclamo reclamo : listaReclamos){
			guardarReclamo(reclamo);
		}
	}
	
	/**
	 * Devuelve una lista de Reclamos
	 * @author Matias
	 */
	public List<IReclamo> obtenerTodosReclamos(){
		List<IReclamo> listaReclamos = new ArrayList<IReclamo>();
		List<IReclamo> setReclamos = Repository.getInstance().query(IReclamo.class);
		
		for(IReclamo reclamo:setReclamos){
			listaReclamos.add(reclamo);
		}
		
		return listaReclamos;
	}
	/**
	 * Se le pasa un predicate por parametro y devuelve una coleccion de reclamos que cumplen con dicho predicate.
	 * @param predicate
	 * @return
	 */
	public List<Reclamo> obtenerReclamosFiltrados(PredicateReclamo predicate){
		return Repository.getInstance().query(predicate);
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
