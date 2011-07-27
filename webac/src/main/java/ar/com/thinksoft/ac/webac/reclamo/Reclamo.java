package ar.com.thinksoft.ac.webac.reclamo;

import java.awt.Image;
import java.util.Date;

import ar.com.thinksoft.ac.intac.IEstadoReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;

public class Reclamo implements IReclamo{
	
	private String calleReclamo = "";
	private int alturaReclamo = 0;
	private int latitudReclamo = 0;
	private int longitudReclamo = 0;
	private Date fechaYHoraReclamo = null;
	private String tipoReclamo = "";
	private String ciudadano = "";
	private String observaciones = "";
	private Image imagen;
	private IEstadoReclamo estado;
	

	// get ATRIBUTOS
	
	public String getCalleIncidente() {
		return this.calleReclamo;
	}

	public int getAlturaIncidente() {
		return this.alturaReclamo;
	}

	public int getLatitudIncidente() {
		return this.latitudReclamo;
	}

	public int getLongitudIncidente() {
		return this.longitudReclamo;
	}

	public String getTipoIncidente() {
		return this.tipoReclamo;
	}

	public Date getFechaYHoraReclamo() {
		return this.fechaYHoraReclamo;
	}

	public String getCiudadanoGeneradorReclamo() {
		return this.ciudadano;
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
	
		
	//SET ATRIBUTOS
	
	public void setCalleIncidente(String calle) {
		this.calleReclamo = calle;
		
	}

	public void setAlturaIncidente(int altura) {
		this.alturaReclamo = altura;
		
	}

	public void setLatitudIncidente(int latitud) {
		this.latitudReclamo = latitud;
		
	}

	public void setLongitudIncidente(int longitud) {
		this.longitudReclamo = longitud;
		
	}

	public void setTipoIncidente(String tipo) {
		this.tipoReclamo = tipo;
		
	}

	public void setFechaYHoraReclamo(Date fechaYHora) {
		this.fechaYHoraReclamo = fechaYHora;
		
	}

	public void setCiudadanoGeneradorReclamo(String ciudadano) {
		this.ciudadano = ciudadano;
		
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


}
