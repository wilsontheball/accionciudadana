package ar.com.thinksoft.ac.intac;

import java.awt.Image;
import java.util.Date;

/**
 * Interfaz a implementar para reclamo tanto en la parte Android como en la parte de Dominio Web
 * @author Matias
 *
 */
public interface IReclamo {

	// Obtención de atributos que deben tener los reclamos.
	
	public String getCalleIncidente();
	
	public int getAlturaIncidente();
	
	public int getLatitudIncidente();
	
	public int getLongitudIncidente();
	
	public String getTipoIncidente();
	
	public Date getFechaYHoraReclamo();
	
	public String getCiudadanoGeneradorReclamo();
	
	public String getObservaciones();
	
	public Image getImagen();
	
	public IEstadoReclamo getEstado();
	
	public String getPrioridad();
	
	// Fin obtención de atributos
	
	// Setear atributos
	public void setCalleIncidente(String calle);
	
	public void setAlturaIncidente(int altura);
	
	public void setLatitudIncidente(int latitud);
	
	public void setLongitudIncidente(int longitud);
	
	public void setTipoIncidente(String tipo);
	
	public void setFechaYHoraReclamo(Date fechaYHora);
	
	public void setCiudadanoGeneradorReclamo(String ciudadano);
	
	public void setObservaciones(String observaciones);
	
	public void setImagen(Image imagen);
	
	public void setEstado(IEstadoReclamo estado);
	
	public void setPrioridad(String prioridad);
	
	// Fin seteo atributos
	
	
}

