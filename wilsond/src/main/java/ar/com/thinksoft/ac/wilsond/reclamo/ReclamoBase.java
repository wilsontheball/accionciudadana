package ar.com.thinksoft.ac.wilsond.reclamo;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.IImagen;
import ar.com.thinksoft.ac.intac.IReclamo;

public class ReclamoBase implements IReclamo{
	
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
	
	public ReclamoBase(){
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
	
	public void setPrioridad(String prioridad){
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


	public void clone(ReclamoBase reclamo) {
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


	public void cambiarEstado(String estadoDescripcion){
	}


	public boolean isNotDown() {
		return false;
	}


	public boolean isIgual(IReclamo reclamo) {
		return false;
	}


	public void unificar(IReclamo reclamo){
	}
	
	
	public void cancelarReclamo(){
	}
	
	public void setAsociadoReclamo(){
	}
	
	public void suspender(){
	}
	
	public void activar(){
	}
	
	public void enProgreso(){
	}
	
	public void demorar(){
	}
	
	public void terminar(){
	}

	public void clone(IReclamo reclamoOriginal) {
		// TODO Auto-generated method stub
		
	}
}
