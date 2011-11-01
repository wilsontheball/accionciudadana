package ar.com.thinksoft.ac.andrac.dominio;

import java.util.List;

import android.util.Log;
import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.IImagen;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.intac.utils.classes.ReclamoMovil;

/**
 * Representa un reclamo de usuario.
 * 
 * @since 07-10-2011
 * @author Paul
 */
public class Reclamo extends ReclamoMovil {
	private static final long serialVersionUID = 1L;

	// XXX Se sobreescribe atributo del padre. Habria que hacerlo bien.
	protected Imagen fotoIncidente;

	public Reclamo(String calle, String altura, String latitud,
			String longitud, String tipo, String fecha,
			String fechaModificacion, String ciudadano, String observaciones,
			String barrio, Imagen imagen) {
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
		this.setComunaIncidentePorBarrio(barrio);
		this.setImagen(imagen);
	}

	// Para evitar CAST
	public void setImagen(Imagen imagen) {
		this.fotoIncidente = imagen;
	}

	// Metodos de la interfaz IReclamo

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
		return this.fotoIncidente;
	}

	public String getEstadoDescripcion() {
		return this.estadoReclamo;
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
		try {
			this.setComunaIncidente(EnumBarriosReclamo
					.getComunaDeBarrio(barrio));
		} catch (Exception e) {
			Log.e(this.getClass().getName(), e.toString());
			this.setComunaIncidente("");
		}
	}

	public void setComunaIncidente(String comuna) {
		// TODO Auto-generated method stub

	}

	public void setImagen(IImagen imagen) {
		this.fotoIncidente = (Imagen) imagen;
	}

	public void setPrioridad(String prioridad) {
		// TODO Auto-generated method stub

	}

	public void clone(IReclamo reclamoOriginal) {
		// TODO Auto-generated method stub

	}

	public void cambiarEstado(String estadoDescripcion) {
		this.estadoReclamo = estadoDescripcion;
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

	public String getMailCiudadanoGeneradorReclamo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMailCiudadanoGeneradorReclamo(String mail) {
		// TODO Auto-generated method stub

	}

	public void setEstadoDescripcion(String estado) {
		// TODO Auto-generated method stub
		
	}

}
