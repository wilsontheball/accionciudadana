package ar.com.thinksoft.ac.intac;

import java.io.Serializable;

/**
 * Interfaz a implementar para reclamo tanto en la parte Android como en la parte de Dominio Web
 * @author Matias
 *
 */
public interface IReclamo extends Serializable{

	// Obtencion de atributos que deben tener los reclamos.
	
	public String getId();
	
	public String getCalleIncidente();
	
	public String getAlturaIncidente();
	
	public String getLatitudIncidente();
	
	public String getLongitudIncidente();
	
	public String getTipoIncidente();
	
	public String getFechaReclamo();
	
	public String getCiudadanoGeneradorReclamo();
	
	public String getObservaciones();
	
	public IImagen getImagen();
	
	public IEstadoReclamo getEstado();
	
	public String getEstadoDescripcion();
	
	public String getPrioridad();
	
	// Fin obtencion de atributos
	
	// Setear atributos
	
	public void setId();
	
	public void setCalleIncidente(String calle);
	
	public void setAlturaIncidente(String altura);
	
	public void setLatitudIncidente(String latitud);
	
	public void setLongitudIncidente(String longitud);
	
	public void setTipoIncidente(String tipo);
	
	public void setFechaReclamo(String fechaYHora);
	
	public void setCiudadanoGeneradorReclamo(String ciudadano);
	
	public void setObservaciones(String observaciones);
	
	public void setImagen(IImagen imagen);
	
	public void setEstado(IEstadoReclamo estado);
	
	public void setPrioridad(String prioridad);
	
	// Fin seteo atributos
	
	
}

