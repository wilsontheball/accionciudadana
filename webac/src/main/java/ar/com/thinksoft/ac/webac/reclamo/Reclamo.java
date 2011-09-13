package ar.com.thinksoft.ac.webac.reclamo;

import java.io.Serializable;

import ar.com.thinksoft.ac.estadosReclamo.EstadoActivo;
import ar.com.thinksoft.ac.estadosReclamo.EstadoBaja;
import ar.com.thinksoft.ac.estadosReclamo.EstadoCancelado;
import ar.com.thinksoft.ac.estadosReclamo.EstadoDemorado;
import ar.com.thinksoft.ac.estadosReclamo.EstadoEnProgreso;
import ar.com.thinksoft.ac.estadosReclamo.EstadoSuspendido;
import ar.com.thinksoft.ac.estadosReclamo.EstadoTerminado;
import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.IEstadoReclamo;
import ar.com.thinksoft.ac.intac.IImagen;
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
	private String fechaUltimaModificacion;
	private String tipoReclamo;
	private String ciudadanoReclamo;
	private String observaciones;
	private String barrioIncidente;
	private String comunaIncidente;
	private IImagen imagen;
	private IEstadoReclamo estado;
	private String estadoDescripcion;
	private String prioridad;
	
	public Reclamo(){
	}
	
	public Reclamo (String calle,String altura,String latitud,String longitud,String fecha, String tipo, String ciudadano,
					String observaciones,String barrioIncidente, IImagen imagen, IEstadoReclamo estado, String prioridad){
		
		this.setId();
		this.setCalleIncidente(calle);
		this.setAlturaIncidente(altura);
		this.setLatitudIncidente(latitud);
		this.setLongitudIncidente(longitud);
		this.setFechaReclamo(fecha);
		this.setTipoIncidente(tipo);
		this.setCiudadanoGeneradorReclamo(ciudadano);
		this.setObservaciones(observaciones);
		this.setBarrioIncidente(barrioIncidente);
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
		ReclamoManager.getInstance().guardarReclamo(this);
	}
	
	/**
	 * Setea el estado de Baja al Reclamo
	 * @author Matias
	 */
	public void darDeBajaReclamo(){
		this.setEstado(new EstadoBaja());
		ReclamoManager.getInstance().guardarReclamo(this);
	}
	
	/**
	 * Setea el estado de Suspendido al Reclamo
	 * @author Matias
	 */
	public void suspender(){
		this.setEstado(new EstadoSuspendido());
		ReclamoManager.getInstance().guardarReclamo(this);
	}
	
	/**
	 * Setea el estado de Activo al Reclamo
	 * @author Matias
	 */
	public void activar(){
		this.setEstado(new EstadoActivo());
		ReclamoManager.getInstance().guardarReclamo(this);
	}
	
	/**
	 * Setea el estado de En Progreso al Reclamo
	 * @author Matias 
	 */
	public void enProgreso(){
		this.setEstado(new EstadoEnProgreso());
		ReclamoManager.getInstance().guardarReclamo(this);
	}
	
	/**
	 * Setea el estado de Demorado al Reclamo
	 * @author Matias 
	 */
	public void demorar(){
		this.setEstado(new EstadoDemorado());
		ReclamoManager.getInstance().guardarReclamo(this);
	}
	
	/**
	 * Setea el estado de Terminado al Reclamo
	 * @author Matias 
	 */
	public void terminar(){
		this.setEstado(new EstadoTerminado());
		ReclamoManager.getInstance().guardarReclamo(this);
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
	
	public String getFechaUltimaModificacionReclamo() {
		return this.fechaUltimaModificacion;
	}
	
	public String getCiudadanoGeneradorReclamo() {
		return this.ciudadanoReclamo;
	}

	public String getObservaciones() {
		return this.observaciones;
	}
	
	public String getBarrioIncidente(){
		return this.barrioIncidente;
	}
	
	public String getComunaIncidente(){
		return this.comunaIncidente;
	}

	public IImagen getImagen() {
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
	
	public String getEstadoDescripcionDefault(){
		return this.estadoDescripcion;
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
	
	public void setFechaUltimaModificacionReclamo(String fecha) {
		this.fechaUltimaModificacion = fecha;
	}

	public void setCiudadanoGeneradorReclamo(String ciudadano) {
		this.ciudadanoReclamo = ciudadano;
		
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		
	}
	
	public void setBarrioIncidente(String barrioIncidente){
		this.barrioIncidente = barrioIncidente;
		setComunaIncidente();
	}

	public void setImagen(IImagen imagen) {
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

	public void setComunaIncidente(){
			try {
				this.comunaIncidente = EnumBarriosReclamo.getComunaDeBarrio(this.barrioIncidente);
			} catch (Exception e) {
				this.comunaIncidente = "error";
			}
		
	}
	
	public void cambiarEstado(String estado){
		if(estado.equals(EnumEstadosReclamo.activo.getEstado()))
			this.activar();
		
		if(estado.equals(EnumEstadosReclamo.baja.getEstado()))
			this.darDeBajaReclamo();
		
		if(estado.equals(EnumEstadosReclamo.cancelado.getEstado()))
			this.cancelarReclamo();
		
		if(estado.equals(EnumEstadosReclamo.demorado.getEstado()))
			this.demorar();
		
		if(estado.equals(EnumEstadosReclamo.enProgreso.getEstado()))
			this.enProgreso();
		
		if(estado.equals(EnumEstadosReclamo.suspendido.getEstado()))
			this.suspender();
		
		if(estado.equals(EnumEstadosReclamo.terminado.getEstado()))
			this.terminar();
		
		if(estado.equals(EnumEstadosReclamo.activo.getEstado()))
			this.activar();
	}
	
	public void clone(IReclamo reclamo){
		this.alturaReclamo = reclamo.getAlturaIncidente();
		this.barrioIncidente = reclamo.getBarrioIncidente();
		this.calleReclamo = reclamo.getCalleIncidente();
		this.ciudadanoReclamo = reclamo.getCiudadanoGeneradorReclamo();
		this.comunaIncidente = reclamo.getComunaIncidente();
		this.estado = reclamo.getEstado();
		this.estadoDescripcion = reclamo.getEstadoDescripcion();
		this.fechaReclamo = reclamo.getFechaReclamo();
		this.fechaUltimaModificacion = reclamo.getFechaUltimaModificacionReclamo();
		this.id = reclamo.getId();
		this.imagen = reclamo.getImagen();
		this.latitudReclamo = reclamo.getLatitudIncidente();
		this.longitudReclamo = reclamo.getLongitudIncidente();
		this.observaciones = reclamo.getObservaciones();
		this.prioridad = reclamo.getPrioridad();
		this.tipoReclamo = reclamo.getTipoIncidente();
	}

}
