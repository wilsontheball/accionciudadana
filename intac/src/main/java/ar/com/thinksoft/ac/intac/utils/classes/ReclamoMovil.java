package ar.com.thinksoft.ac.intac.utils.classes;

import ar.com.thinksoft.ac.intac.IReclamo;

/**
 * Es un DTO para intercambiar los datos de reclamo entre Andrac y Wilsond.
 * Cumpliendo con GSON la clase asegura que los objetos de ambos extremos tengan
 * los mismos nombres de atributos.
 * 
 * @since 24-09-2011
 * @author Paul
 */
public abstract class ReclamoMovil implements IReclamo {

	private static final long serialVersionUID = 1L;

	protected String alturaCalle;
	protected String nombreBarrio;
	protected String nombreCalle;
	protected String nombreCiudadano;
	protected String mailCiudadano;
	protected String estadoReclamo;
	protected String fechaCreacion;
	protected String fechaModificacion;
	protected String latitudIncidente;
	protected String longitudIncidente;
	protected String observaciones;
	protected String tipoIncidente;
}
