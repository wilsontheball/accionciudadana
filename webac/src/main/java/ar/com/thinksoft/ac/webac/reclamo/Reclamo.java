package ar.com.thinksoft.ac.webac.reclamo;

import java.awt.Image;
import java.io.Serializable;

import ar.com.thinksoft.ac.estadosReclamo.EstadoActivo;
import ar.com.thinksoft.ac.estadosReclamo.EstadoBaja;
import ar.com.thinksoft.ac.estadosReclamo.EstadoCancelado;
import ar.com.thinksoft.ac.estadosReclamo.EstadoDemorado;
import ar.com.thinksoft.ac.estadosReclamo.EstadoEnProgreso;
import ar.com.thinksoft.ac.estadosReclamo.EstadoSuspendido;
import ar.com.thinksoft.ac.estadosReclamo.EstadoTerminado;
import ar.com.thinksoft.ac.intac.IEstadoReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;

/**
 * Implementacion de IReclamo en intac
 * @author Matias
 *
 */
public class Reclamo implements IReclamo,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String calleReclamo;
	private String alturaReclamo;
	private String latitudReclamo;
	private String longitudReclamo;
	private String fechaReclamo;
	private String tipoReclamo;
	private String ciudadanoReclamo;
	private String observaciones;
	private Image imagen;
	private IEstadoReclamo estado;
	private String estadoDescripcion;
	private String prioridad;
	
	
	public Reclamo(){
	}
	
	public Reclamo (String calle,String altura,String latitud,String longitud,String fecha, String tipo, String ciudadano,
					String observaciones, Image imagen, IEstadoReclamo estado, String prioridad){
		
		this.setId();
		this.setCalleIncidente(calle);
		this.setAlturaIncidente(altura);
		this.setLatitudIncidente(latitud);
		this.setLongitudIncidente(longitud);
		this.setFechaReclamo(fecha);
		this.setTipoIncidente(tipo);
		this.setCiudadanoGeneradorReclamo(ciudadano);
		this.setObservaciones(observaciones);
		this.setImagen(imagen);
		this.setEstado(estado);
		this.setPrioridad(prioridad);
		
	}
	
	/**
	 * Setea el estado de Cancelado al Reclamo
	 * @author Matias
	 */
	public void cancelarReclamo(){
		this.setEstado(new EstadoCancelado());
	}
	
	/**
	 * Setea el estado de Baja al Reclamo
	 * @author Matias
	 */
	public void darDeBajaReclamo(){
		this.setEstado(new EstadoBaja());
	}
	
	/**
	 * Setea el estado de Suspendido al Reclamo
	 * @author Matias
	 */
	public void suspender(){
		this.setEstado(new EstadoSuspendido());
	}
	
	/**
	 * Setea el estado de Activo al Reclamo
	 * @author Matias
	 */
	public void activar(){
		this.setEstado(new EstadoActivo());
	}
	
	/**
	 * Setea el estado de En Progreso al Reclamo
	 * @author Matias 
	 */
	public void enProgreso(){
		this.setEstado(new EstadoEnProgreso());
	}
	
	/**
	 * Setea el estado de Demorado al Reclamo
	 * @author Matias 
	 */
	public void demorar(){
		this.setEstado(new EstadoDemorado());
	}
	
	/**
	 * Setea el estado de Terminado al Reclamo
	 * @author Matias 
	 */
	public void terminar(){
		this.setEstado(new EstadoTerminado());
	}

	// get ATRIBUTOS
	
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

	public String getFechaReclamo() {
		return this.fechaReclamo;
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
	
	public String getEstadoDescripcion(){
		
		if(this.estado != null){
			this.estadoDescripcion = this.estado.getDescripcionEstado();
			return this.estadoDescripcion;
		}
		if(this.estadoDescripcion != null && this.estadoDescripcion != "")
			return this.estadoDescripcion;
		else
			return "";
	}
	
	public String getPrioridad(){
		return this.prioridad;
	}
	
		
	//SET ATRIBUTOS
	
	public void setId() {
		this.id = java.util.UUID.randomUUID().toString();;
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

	public void setFechaReclamo(String fechaYHora) {
		this.fechaReclamo = fechaYHora;
		
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
		this.estadoDescripcion = estado.getDescripcionEstado();
		
	}
	
	public void setEstadoDescripcion(String estadoDesc){
		this.estadoDescripcion = estadoDesc;
	}
	
	public void setPrioridad(String prioridad){
		this.prioridad = prioridad;
	}

}
