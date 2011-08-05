package ar.com.thinksoft.ac.webac.reclamo;

import java.util.*;

import com.db4o.query.Predicate;

import ar.com.thinksoft.ac.estadosReclamo.EstadoActivo;
import ar.com.thinksoft.ac.estadosReclamo.EstadoBaja;
import ar.com.thinksoft.ac.estadosReclamo.EstadoCancelado;
import ar.com.thinksoft.ac.estadosReclamo.EstadoDemorado;
import ar.com.thinksoft.ac.estadosReclamo.EstadoEnProgreso;
import ar.com.thinksoft.ac.estadosReclamo.EstadoSuspendido;
import ar.com.thinksoft.ac.estadosReclamo.EstadoTerminado;
import ar.com.thinksoft.ac.intac.IReclamo;
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
	public List<IReclamo> obtenerReclamosFiltrados(Predicate<IReclamo> predicado){
		return  Repository.getInstance().query(predicado);
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
	
	/**
	 * Setea el estado de Cancelado al Reclamo
	 * @author Matias
	 */
	public void cancelarReclamo(IReclamo reclamo){
		reclamo.setEstado(new EstadoCancelado());
		this.guardarReclamo(reclamo);
	}
	
	/**
	 * Setea el estado de Baja al Reclamo
	 * @author Matias
	 */
	public void darDeBajaReclamo(IReclamo reclamo){
		reclamo.setEstado(new EstadoBaja());
		this.guardarReclamo(reclamo);
	}
	
	/**
	 * Setea el estado de Suspendido al Reclamo
	 * @author Matias
	 */
	public void suspenderReclamo(IReclamo reclamo){
		reclamo.setEstado(new EstadoSuspendido());
		this.guardarReclamo(reclamo);
	}
	
	/**
	 * Setea el estado de Activo al Reclamo
	 * @author Matias
	 */
	public void activarReclamo(IReclamo reclamo){
		reclamo.setEstado(new EstadoActivo());
		this.guardarReclamo(reclamo);
	}
	
	/**
	 * Setea el estado de En Progreso al Reclamo
	 * @author Matias 
	 */
	public void enProgresoReclamo(IReclamo reclamo){
		reclamo.setEstado(new EstadoEnProgreso());
		this.guardarReclamo(reclamo);
	}
	
	/**
	 * Setea el estado de Demorado al Reclamo
	 * @author Matias 
	 */
	public void demorarReclamo(IReclamo reclamo){
		reclamo.setEstado(new EstadoDemorado());
		this.guardarReclamo(reclamo);
	}
	
	/**
	 * Setea el estado de Terminado al Reclamo
	 * @author Matias 
	 */
	public void terminarReclamo(IReclamo reclamo){
		reclamo.setEstado(new EstadoTerminado());
		this.guardarReclamo(reclamo);
	}
}	
