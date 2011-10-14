package ar.com.thinksoft.ac.webac.reclamo;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.intac.IImagen;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.exceptions.MailException;
import ar.com.thinksoft.ac.webac.mail.MailManager;

/**
 * Implementacion de IReclamo en intac
 * @author Matias
 *
 */
public class Reclamo implements IReclamo{
	
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
	private String mailCiudadanoReclamo;
	private String observaciones;
	private String barrioIncidente;
	private String comunaIncidente;
	private IImagen imagen;
	private String estadoDescripcion;
	private String prioridad;
	private List<IReclamo> reclamosAsociados = new ArrayList<IReclamo>();
	
	public Reclamo(){
	}
	
	public Reclamo (String calle,String altura,String latitud,String longitud,String fecha, String tipo, String ciudadano,
					String observaciones,String barrioIncidente, IImagen imagen, String prioridad){
		
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
		try {
			this.setPrioridad(prioridad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Setea el estado de Cancelado al Reclamo
	 * @author Matias
	 * @throws MailException 
	 */
	public void cancelarReclamo() throws Exception{
		this.estadoDescripcion = EnumEstadosReclamo.cancelado.getEstado();
		ReclamoManager.getInstance().guardarReclamo(this);
		MailManager.getInstance().enviarMail(this.mailCiudadanoReclamo, "Accion Ciudadana - Cancelacion de reclamo", MailManager.getInstance().armarTextoCambioEstados(this.estadoDescripcion,this));
	}
	
	/**
	 * Setea el estado de Baja al Reclamo
	 * @author Matias
	 * @throws MailException 
	 */
	public void setAsociadoReclamo() throws Exception{
		this.estadoDescripcion = EnumEstadosReclamo.asociado.getEstado();
		ReclamoManager.getInstance().guardarReclamo(this);
		MailManager.getInstance().enviarMail(this.mailCiudadanoReclamo, "Accion Ciudadana - Asociacion de reclamo", MailManager.getInstance().armarTextoCambioEstados(this.estadoDescripcion,this));
	}
	
	/**
	 * Setea el estado de Suspendido al Reclamo
	 * @author Matias
	 * @throws MailException 
	 */
	public void suspender() throws Exception{
		this.estadoDescripcion = EnumEstadosReclamo.suspendido.getEstado();
		ReclamoManager.getInstance().guardarReclamo(this);
		MailManager.getInstance().enviarMail(this.mailCiudadanoReclamo, "Accion Ciudadana - Suspension de reclamo", MailManager.getInstance().armarTextoCambioEstados(this.estadoDescripcion,this));
	}
	
	/**
	 * Setea el estado de Activo al Reclamo
	 * @author Matias
	 * @throws MailException 
	 */
	public void activar() throws Exception{
		this.estadoDescripcion = EnumEstadosReclamo.activo.getEstado();
		ReclamoManager.getInstance().guardarReclamo(this);
		MailManager.getInstance().enviarMail(this.mailCiudadanoReclamo, "Accion Ciudadana - Activacion de reclamo", MailManager.getInstance().armarTextoCambioEstados(this.estadoDescripcion,this));
	}
	
	/**
	 * Setea el estado de En Progreso al Reclamo
	 * @author Matias 
	 * @throws MailException 
	 */
	public void enProgreso() throws Exception{
		this.estadoDescripcion = EnumEstadosReclamo.enProgreso.getEstado();
		ReclamoManager.getInstance().guardarReclamo(this);
		MailManager.getInstance().enviarMail(this.mailCiudadanoReclamo, "Accion Ciudadana - Solucion en progreso del reclamo", MailManager.getInstance().armarTextoCambioEstados(this.estadoDescripcion,this));
	}
	
	/**
	 * Setea el estado de Demorado al Reclamo
	 * @author Matias 
	 */
	public void demorar() throws Exception{
		this.estadoDescripcion = EnumEstadosReclamo.demorado.getEstado();
		ReclamoManager.getInstance().guardarReclamo(this);
		MailManager.getInstance().enviarMail(this.mailCiudadanoReclamo, "Accion Ciudadana - Demora del reclamo", MailManager.getInstance().armarTextoCambioEstados(this.estadoDescripcion,this));
	}
	
	/**
	 * Setea el estado de Terminado al Reclamo
	 * @author Matias 
	 */
	public void terminar() throws Exception{
		this.estadoDescripcion = EnumEstadosReclamo.terminado.getEstado();
		ReclamoManager.getInstance().guardarReclamo(this);
		MailManager.getInstance().enviarMail(this.mailCiudadanoReclamo, "Accion Ciudadana - Finalizacion de reclamo", MailManager.getInstance().armarTextoCambioEstados(this.estadoDescripcion,this));
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
	
	public String getMailCiudadanoGeneradorReclamo(){
		return this.mailCiudadanoReclamo;
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

	public String getEstadoDescripcion(){
		return this.estadoDescripcion;
	}
	
	public String getPrioridad(){
		return this.prioridad;
	}
	
	public List<IReclamo> getReclamosAsociados() {
		return this.reclamosAsociados;
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
	
	public void setMailCiudadanoGeneradorReclamo(String mail){
		this.mailCiudadanoReclamo = mail;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		
	}
	
	public void setBarrioIncidente(String barrioIncidente){
		this.barrioIncidente = barrioIncidente;
		setComunaIncidentePorBarrio(this.barrioIncidente);
	}

	public void setImagen(IImagen imagen) {
		this.imagen = imagen;
		
	}

	public void setEstadoDescripcion(String estadoDesc){
		this.estadoDescripcion = estadoDesc;
	}
	
	public void setPrioridad(String prioridad) throws Exception{
		this.prioridad = prioridad;
		
	}

	public void setComunaIncidentePorBarrio(String barrio){
			try {
				this.comunaIncidente = EnumBarriosReclamo.getComunaDeBarrio(barrio);
			} catch (Exception e) {
				this.comunaIncidente = "error";
			}
	}
	
	public void setComunaIncidente(String comuna){
		this.comunaIncidente = comuna;
	}
	
	public void cambiarEstado(String estado) throws Exception{
		if(estado.equals(EnumEstadosReclamo.activo.getEstado()))
			this.activar();
		
		if(estado.equals(EnumEstadosReclamo.asociado.getEstado()))
			this.setAsociadoReclamo();
		
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
		this.mailCiudadanoReclamo = reclamo.getMailCiudadanoGeneradorReclamo();
		this.comunaIncidente = reclamo.getComunaIncidente();
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
		this.reclamosAsociados = reclamo.getReclamosAsociados();
	}
	
	// METODOS PARA UNIFICACION
	
	public void unificar(IReclamo reclamo) throws Exception{
		if(this.isIgual(reclamo) && this.isNotDown() && reclamo.isNotDown()){
			reclamo.setAsociadoReclamo();
			this.getReclamosAsociados().add(reclamo);
			definirPrioridadUnificado(reclamo);
			ReclamoManager.getInstance().guardarReclamo(this);
			ReclamoManager.getInstance().guardarReclamo(reclamo);
		}
	}
	
	public boolean isNotDown(){
		return  (!this.getEstadoDescripcion().equals(EnumEstadosReclamo.cancelado.getEstado())) &&
				(!this.getEstadoDescripcion().equals(EnumEstadosReclamo.terminado.getEstado())) &&
				(!this.getEstadoDescripcion().equals(EnumEstadosReclamo.asociado.getEstado()));
	}
	
	public boolean isIgual(IReclamo reclamo){
		return 	this.getComunaIncidente().equals(reclamo.getComunaIncidente()) &&
				(!this.getId().equals(reclamo.getId())) && //ID distinto
				this.getTipoIncidente().equals(reclamo.getTipoIncidente()) &&
				this.getCalleIncidente().equals(reclamo.getCalleIncidente()) &&
				diferenciaAlturasMenorCienMetros(this.getAlturaIncidente(),reclamo.getAlturaIncidente());
	}
	
	private boolean diferenciaAlturasMenorCienMetros(String alturaIncidente, String alturaIncidente2) {
		int altura1 = Integer.parseInt(alturaIncidente);
		int altura2 = Integer.parseInt(alturaIncidente2);
		
		if(altura1>altura2)
			return altura1 - altura2 <= 100;
		else
			return altura2 - altura1 <= 100;
	}
	
	private void definirPrioridadUnificado(IReclamo reclamo) throws Exception{
		if(this.getPrioridad()!=reclamo.getPrioridad() && (!reclamo.getPrioridad().equals(EnumPrioridadReclamo.noAsignada.getPrioridad()))){
			if(this.getPrioridad().equals(EnumPrioridadReclamo.noAsignada.getPrioridad()))
				this.setPrioridad(reclamo.getPrioridad());
			
			if(reclamo.getPrioridad().equals(EnumPrioridadReclamo.alta.getPrioridad()))
				this.setPrioridad(reclamo.getPrioridad());
			
			if(this.getPrioridad().equals(EnumPrioridadReclamo.baja.getPrioridad()) && reclamo.getPrioridad().equals(EnumPrioridadReclamo.media.getPrioridad()))
				this.setPrioridad(EnumPrioridadReclamo.media.getPrioridad());
		}
	}
	
}
