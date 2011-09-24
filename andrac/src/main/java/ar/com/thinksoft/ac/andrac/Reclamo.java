package ar.com.thinksoft.ac.andrac;

import java.util.List;

import ar.com.thinksoft.ac.intac.IImagen;
import ar.com.thinksoft.ac.intac.IReclamo;

/**
 * Representa un reclamo de usuario.
 * 
 * @since 18-09-2011
 * @author Hernan
 */
public class Reclamo implements IReclamo {
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
		this.setComunaIncidente(comuna);
		this.setImagen(imagen);
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCalleIncidente() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAlturaIncidente() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLatitudIncidente() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLongitudIncidente() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTipoIncidente() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFechaReclamo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFechaUltimaModificacionReclamo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCiudadanoGeneradorReclamo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getObservaciones() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBarrioIncidente() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

	public void setAlturaIncidente(String altura) {
		// TODO Auto-generated method stub

	}

	public void setLatitudIncidente(String latitud) {
		// TODO Auto-generated method stub

	}

	public void setLongitudIncidente(String longitud) {
		// TODO Auto-generated method stub

	}

	public void setTipoIncidente(String tipo) {
		// TODO Auto-generated method stub

	}

	public void setFechaReclamo(String fechaYHora) {
		// TODO Auto-generated method stub

	}

	public void setFechaUltimaModificacionReclamo(String fecha) {
		// TODO Auto-generated method stub

	}

	public void setCiudadanoGeneradorReclamo(String ciudadano) {
		// TODO Auto-generated method stub

	}

	public void setObservaciones(String observaciones) {
		// TODO Auto-generated method stub

	}

	public void setBarrioIncidente(String barrio) {
		// TODO Auto-generated method stub

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
