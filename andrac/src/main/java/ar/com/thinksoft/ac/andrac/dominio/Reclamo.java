package ar.com.thinksoft.ac.andrac.dominio;

import java.util.List;

import ar.com.thinksoft.ac.intac.IImagen;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.intac.utils.classes.ReclamoMovil;

/**
 * Representa un reclamo de usuario.
 * 
 * @since 24-09-2011
 * @author Paul
 */
public class Reclamo extends ReclamoMovil {
	private static final long serialVersionUID = 1L;

	public Reclamo(String calle, String altura, String latitud,
			String longitud, String tipo, String fecha,
			String fechaModificacion, String ciudadano, String observaciones,
			String barrio, String comuna, IImagen imagen) {
		this.setCalleIncidente(calle);
		this.setAlturaIncidente(altura);
		this.setLatitudIncidente(latitud);
		this.setLongitudIncidente(longitud);
		this.setTipoIncidente(tipo);
		this.setFechaReclamo(fecha);
		this.setFechaUltimaModificacionReclamo(fechaModificacion);
		this.setCiudadanoGeneradorReclamo(ciudadano);
		this.setObservaciones(observaciones);
		this.setBarrioIncidente(barrio);
		this.setImagen(imagen);
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCalleIncidente() {
		return this.nombreCalle;
	}

	public String getAlturaIncidente() {
		return this.alturaCalle;
	}

	public String getLatitudIncidente() {
		return this.latitudIncidente;
	}

	public String getLongitudIncidente() {
		return longitudIncidente;
	}

	public String getTipoIncidente() {
		return this.tipoIncidente;
	}

	public String getFechaReclamo() {
		return this.fechaCreacion;
	}

	public String getFechaUltimaModificacionReclamo() {
		return this.fechaModificacion;
	}

	public String getCiudadanoGeneradorReclamo() {
		return this.nombreCiudadano;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public String getBarrioIncidente() {
		return this.nombreBarrio;
	}

	public String getComunaIncidente() {
		// TODO Auto-generated method stub
		return null;
	}

	public IImagen getImagen() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEstadoDescripcion() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPrioridad() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IReclamo> getReclamosAsociados() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId() {
		// TODO Auto-generated method stub

	}

	public void setCalleIncidente(String calle) {
		this.nombreCalle = calle;
	}

	public void setAlturaIncidente(String altura) {
		this.alturaCalle = altura;
	}

	public void setLatitudIncidente(String latitud) {
		this.latitudIncidente = latitud;
	}

	public void setLongitudIncidente(String longitud) {
		this.longitudIncidente = longitud;
	}

	public void setTipoIncidente(String tipo) {
		this.tipoIncidente = tipo;
	}

	public void setFechaReclamo(String fechaYHora) {
		this.fechaCreacion = fechaYHora;
	}

	public void setFechaUltimaModificacionReclamo(String fecha) {
		this.fechaModificacion = fecha;
	}

	public void setCiudadanoGeneradorReclamo(String ciudadano) {
		this.nombreCiudadano = ciudadano;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void setBarrioIncidente(String barrio) {
		this.nombreBarrio = barrio;
	}

	public void setComunaIncidentePorBarrio(String barrio) {
		// TODO Auto-generated method stub

	}

	public void setComunaIncidente(String comuna) {
		// TODO Auto-generated method stub

	}

	public void setImagen(IImagen imagen) {
		// TODO Auto-generated method stub

	}

	public void setPrioridad(String prioridad) {
		// TODO Auto-generated method stub

	}

	public void clone(IReclamo reclamoOriginal) {
		// TODO Auto-generated method stub

	}

	public void cambiarEstado(String estadoDescripcion) {
		// TODO Auto-generated method stub

	}

	public boolean isNotDown() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isIgual(IReclamo reclamo) {
		// TODO Auto-generated method stub
		return false;
	}

	public void unificar(IReclamo reclamo) {
		// TODO Auto-generated method stub

	}

	public void cancelarReclamo() {
		// TODO Auto-generated method stub

	}

	public void setAsociadoReclamo() {
		// TODO Auto-generated method stub

	}

	public void suspender() {
		// TODO Auto-generated method stub

	}

	public void activar() {
		// TODO Auto-generated method stub

	}

	public void enProgreso() {
		// TODO Auto-generated method stub

	}

	public void demorar() {
		// TODO Auto-generated method stub

	}

	public void terminar() {
		// TODO Auto-generated method stub

	}

}
