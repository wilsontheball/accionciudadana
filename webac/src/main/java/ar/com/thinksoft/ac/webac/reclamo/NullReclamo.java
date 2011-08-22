package ar.com.thinksoft.ac.webac.reclamo;

import java.awt.Image;
import java.util.Date;

import ar.com.thinksoft.ac.intac.IEstadoReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;


/**
 * @deprecated
 * Reclamo solo para filtro
 * @author mtarrio
 *
 */
public class NullReclamo extends Reclamo implements IReclamo{

	private String id;
	private String calleReclamo;
	private String alturaReclamo;
	private String latitudReclamo;
	private String longitudReclamo;
	private Date fechaYHoraReclamo;
	private String tipoReclamo;
	private String ciudadanoReclamo;
	private String observaciones;
	private Image imagen;
	private IEstadoReclamo estado;
	private String prioridad;
	
// get ATRIBUTOS
	
	@Override
	public String getId() {
		return id;
	}
	
	public String getCalleIncidente() {
		return this.calleReclamo;
	}

	public String getAlturaIncidente() {
		return this.alturaReclamo;
	}

	public String getLatitudIncidente() {
		return this.latitudReclamo;
	}

	public String getLongitudIncidente() {
		return this.longitudReclamo;
	}

	public String getTipoIncidente() {
		return this.tipoReclamo;
	}

	public Date getFechaYHoraReclamo() {
		return this.fechaYHoraReclamo;
	}

	public String getCiudadanoGeneradorReclamo() {
		return this.ciudadanoReclamo;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public Image getImagen() {
		return this.imagen;
	}

	public IEstadoReclamo getEstado() {
		return this.estado;
	}	
	
	public String getPrioridad(){
		return this.prioridad;
	}
	
		
	//SET ATRIBUTOS
	public void setId(String id) {
		this.id = id;
	}
	
	public void setCalleIncidente(String calle) {
		this.calleReclamo = calle;
		
	}

	public void setAlturaIncidente(String altura) {
		this.alturaReclamo = altura;
		
	}

	public void setLatitudIncidente(String latitud) {
		this.latitudReclamo = latitud;
		
	}

	public void setLongitudIncidente(String longitud) {
		this.longitudReclamo = longitud;
		
	}

	public void setTipoIncidente(String tipo) {
		this.tipoReclamo = tipo;
		
	}

	public void setFechaYHoraReclamo(Date fechaYHora) {
		this.fechaYHoraReclamo = fechaYHora;
		
	}

	public void setCiudadanoGeneradorReclamo(String ciudadano) {
		this.ciudadanoReclamo = ciudadano;
		
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
		
	}

	public void setEstado(IEstadoReclamo estado) {
		this.estado = estado;
		
	}
	
	public void setPrioridad(String prioridad){
		this.prioridad = prioridad;
	}

	public void setId() {
	}
	

}
