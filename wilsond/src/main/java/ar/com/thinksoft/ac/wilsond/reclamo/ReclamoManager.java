package ar.com.thinksoft.ac.wilsond.reclamo;

import java.util.List;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.wilsond.Repositorio.Repositorio;

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
		Repositorio.getInstancia().store(reclamo);
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
		return Repositorio.getInstancia().query(IReclamo.class);
	}
	
	/**
	 * Devuelve un conjunto de reclamos que cumplen con los atributos seteados en el reclamo
	 * que se pasa por parametro
	 * @param reclamo (type NullReclamo)
	 * @return
	 */
	public List<IReclamo> obtenerReclamosFiltrados(IReclamo reclamo){
		return  Repositorio.getInstancia().queryByExample(reclamo);
	}
}
