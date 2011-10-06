package ar.com.thinksoft.ac.wilsond.reclamo;

import java.util.ArrayList;
import java.util.List;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.wilsond.ReclamoAndrac;
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
	
	/*
	 * Traductor de lista IReclamo a lista ReclamoAndrac
	 */
	public List<ReclamoAndrac> toReclamoAndrac(List<IReclamo> list) {
		List<ReclamoAndrac> listaDefinitiva = new ArrayList<ReclamoAndrac>();
		
		for(IReclamo reclamoInt : list){
			ReclamoAndrac reclamo = new ReclamoAndrac();
			reclamo.setAlturaIncidente(reclamoInt.getAlturaIncidente());
			reclamo.setBarrioIncidente(reclamoInt.getBarrioIncidente());
			reclamo.setCalleIncidente(reclamoInt.getCalleIncidente());
			reclamo.setCiudadanoGeneradorReclamo(reclamoInt.getCiudadanoGeneradorReclamo());
			reclamo.setMailCiudadanoGeneradorReclamo(reclamoInt.getMailCiudadanoGeneradorReclamo());
			reclamo.cambiarEstado(reclamoInt.getEstadoDescripcion());
			reclamo.setFechaReclamo(reclamoInt.getFechaReclamo());
			reclamo.setFechaUltimaModificacionReclamo(reclamoInt.getFechaUltimaModificacionReclamo());
			reclamo.setImagen(null);
			reclamo.setLatitudIncidente(reclamoInt.getLatitudIncidente());
			reclamo.setLongitudIncidente(reclamoInt.getLongitudIncidente());
			reclamo.setObservaciones(reclamoInt.getObservaciones());
			reclamo.setTipoIncidente(reclamoInt.getTipoIncidente());
			
			listaDefinitiva.add(reclamo);
		}
		return listaDefinitiva;
	}
	
	/**
	 * Almacena un reclamo en la base de datos.
	 * 
	 * @since 05-10-2011
	 * @author Paul
	 * @param reclamo
	 *            Reclamo a almacenar.
	 */
	public void guardarReclamoTest(IReclamo reclamo) {

		// XXX Solo para probar!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("Se recibio un RECLAMO:");

		if (reclamo == null) {
			System.out.println("reclamo es NULL");
		} else {
			System.out.println(reclamo.getAlturaIncidente());
			System.out.println(reclamo.getBarrioIncidente());
			System.out.println(reclamo.getCalleIncidente());
			System.out.println(reclamo.getCiudadanoGeneradorReclamo());
			System.out.println(reclamo.getComunaIncidente());
			System.out.println(reclamo.getEstadoDescripcion());
			System.out.println(reclamo.getFechaReclamo());
			System.out.println(reclamo.getFechaUltimaModificacionReclamo());
			System.out.println(reclamo.getId());
			System.out.println(reclamo.getLatitudIncidente());
			System.out.println(reclamo.getLongitudIncidente());
			System.out.println(reclamo.getMailCiudadanoGeneradorReclamo());
			System.out.println(reclamo.getObservaciones());
			System.out.println(reclamo.getTipoIncidente());
		}
		// XXX Solo para probar!!!!!!!!!!!!!!!!!!!!!!!!!
	}
	
}
