package ar.com.thinksoft.ac.webac.reclamo;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.repository.Repository;

import com.db4o.query.Predicate;

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
	 * Guardar un unico reclamo, ya sea nuevo o para actualizacion en la Base de Datos
	 * @author Matias
	 */
	public void guardarReclamo(IReclamo reclamo){
		Repository.getInstance().store(reclamo);
	}
	
	/**
	 * Metodo para guardar una coleccion de Reclamos.
	 * Justificacion de este metodo: si quiero guardar una coleccion de Reclamos la guarda como tal,
	 * por eso me interesa guardar cada uno de los reclamos que conforma la coleccion.
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
	 * Devuelve un conjunto de reclamos que cumplen con el filtro especificado
	 * @author Matias
	 * @param predicado = Filtro por un campo que cumple con una condicion
	 */
	public List<IReclamo> obtenerReclamosFiltradosConPredicates(Predicate<IReclamo> predicado){
		return  Repository.getInstance().query(predicado);
	}
	
	/**
	 * Devuelve un conjunto de reclamos que cumplen con los atributos seteados en el reclamo
	 * que se pasa por parametro
	 * @param reclamo (type NullReclamo)
	 * @return
	 */
	public List<IReclamo> obtenerReclamosFiltrados(IReclamo reclamo){
		return  Repository.getInstance().queryByExample(reclamo);
	}
	
	/**
	 * Vacia la Base de Datos de objetos IReclamo
	 * @author Matias
	 */
	public void eliminarTodosReclamos(){
		List<IReclamo> objs = Repository.getInstance().queryByExample(IReclamo.class);
		for(IReclamo reclamo : objs){
			Repository.getInstance().delete(reclamo);
		}
	}
	
	public void eliminarReclamo(IReclamo reclamo){
		Repository.getInstance().delete(reclamo);
		Repository.getInstance().commit();
	}
	
}	
