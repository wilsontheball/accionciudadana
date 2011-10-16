package ar.com.thinksoft.ac.intac;

import java.io.Serializable;
import java.util.List;

/**
 * Interfaz a implementar para reclamo tanto en la parte Android como en la parte de Dominio Web
 * @author Matias
 *
 */
public interface IReclamo extends Serializable{

	// Obtencion de atributos que deben tener los reclamos.
	
	public String getReclamoPadreId();
	
	public String getId();
	
	public String getCalleIncidente();
	
	public String getAlturaIncidente();
	
	public String getLatitudIncidente();
	
	public String getLongitudIncidente();
	
	public String getTipoIncidente();
	
	public String getFechaReclamo();
	
	public String getFechaUltimaModificacionReclamo();
	
	public String getCiudadanoGeneradorReclamo();
	
	public String getMailCiudadanoGeneradorReclamo();
	
	public  String getObservaciones();
	
	public  String getBarrioIncidente();
	
	public  String getComunaIncidente();
	
	public  IImagen getImagen();
	
	public  String getEstadoDescripcion();
	
	public  String getPrioridad();
	
	public  List<IReclamo> getReclamosAsociados();
	
	// Fin obtencion de atributos
	
	// Setear atributos
	
	public void setReclamoPadreId(String id);
	
	public  void setId();
	
	public  void setCalleIncidente(String calle);
	
	public  void setAlturaIncidente(String altura);
	
	public  void setLatitudIncidente(String latitud);
	
	public  void setLongitudIncidente(String longitud);
	
	public  void setTipoIncidente(String tipo);
	
	public  void setFechaReclamo(String fechaYHora);
	
	public  void setFechaUltimaModificacionReclamo(String fecha);
	
	public  void setCiudadanoGeneradorReclamo(String ciudadano);
	
	public  void setMailCiudadanoGeneradorReclamo(String mail);
	
	public  void setObservaciones(String observaciones);
	
	public  void setBarrioIncidente(String barrio);
	
	public  void setComunaIncidentePorBarrio(String barrio);
	
	public  void setComunaIncidente(String comuna);
	
	public  void setImagen(IImagen imagen);
	
	public  void setPrioridad(String prioridad) throws Exception;

	public  void clone(IReclamo reclamoOriginal);

	public  void cambiarEstado(String estadoDescripcion) throws Exception;

	// Fin seteo atributos
	
	//Metodos que no hace falta implementar en Android.
	 
	public  boolean isNotDown();
	
	public  boolean isIgual(IReclamo reclamo);
	
	public  void unificar(IReclamo reclamo);
	
	public  void cancelarReclamo() throws Exception;

	public  void setAsociadoReclamo() throws Exception;
	
	public  void suspender() throws Exception;

	public  void activar() throws Exception;
	
	public  void enProgreso() throws Exception;
	
	public  void demorar() throws Exception;
	
	public  void terminar() throws Exception;

	public void setEstadoDescripcion(String estado);
	
}

