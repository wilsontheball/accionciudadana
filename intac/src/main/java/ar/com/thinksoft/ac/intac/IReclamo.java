package ar.com.thinksoft.ac.intac;

import java.awt.Image;
import java.io.Serializable;
import java.util.Date;

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
	
	public Date getFechaYHoraReclamo();
	
	public String getCiudadanoGeneradorReclamo();
	
	public String getObservaciones();
	
	public Image getImagen();
	
	public IEstadoReclamo getEstado();
	
	public String getPrioridad();
	
	// Fin obtencion de atributos
	
	// Setear atributos
	
	public void setId();
	
	public void setCalleIncidente(String calle);
	
	public void setAlturaIncidente(String altura);
	
	public void setLatitudIncidente(String latitud);
	
	public void setLongitudIncidente(String longitud);
	
	public void setTipoIncidente(String tipo);
	
	public void setFechaYHoraReclamo(Date fechaYHora);
	
	public void setCiudadanoGeneradorReclamo(String ciudadano);
	
	public void setObservaciones(String observaciones);
	
	public void setImagen(Image imagen);
	
	public void setEstado(IEstadoReclamo estado);
	
	public void setPrioridad(String prioridad);
	
	// Fin seteo atributos
	
	
}

