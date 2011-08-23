package ar.com.thinksoft.ac.webac.reclamo;

import java.util.Date;

import ar.com.thinksoft.ac.estadosReclamo.EstadoActivo;
import ar.com.thinksoft.ac.estadosReclamo.EstadoEnProgreso;
import ar.com.thinksoft.ac.estadosReclamo.EstadoSuspendido;
import ar.com.thinksoft.ac.intac.EnumPrioridadReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;

public class cargaDeReclamos {
	
	public cargaDeReclamos(){
		
		IReclamo reclamoPrueba = new Reclamo("Avellaneda","3905","50","40",new Date().toString(),EnumTipoReclamo.bache.getTipo(),"Matias","Observaciones vacias",null,new EstadoActivo(), EnumPrioridadReclamo.alta.getPrioridad());
		ReclamoManager.getInstance().guardarReclamo(reclamoPrueba);
		
		IReclamo reclamoPrueba1 = new Reclamo("Beiro","4000","40","60",new Date().toString(),EnumTipoReclamo.caidaObjetos.getTipo(),"Rocio","Rompio la vereda",null,new EstadoEnProgreso(), EnumPrioridadReclamo.media.getPrioridad());
		ReclamoManager.getInstance().guardarReclamo(reclamoPrueba1);
		
		IReclamo reclamoPrueba2 = new Reclamo("Segurola","300","10","20",new Date().toString(),EnumTipoReclamo.caidaObjetos.getTipo(),"Matias","Se cayo el balcon", null, new EstadoSuspendido(), EnumPrioridadReclamo.baja.getPrioridad());
		ReclamoManager.getInstance().guardarReclamo(reclamoPrueba2);
		
		IReclamo reclamoPrueba3 = new Reclamo("Rivadavia","9905","50","40",new Date().toString(),EnumTipoReclamo.accesibilidad.getTipo(),"Matias","Observaciones vacias",null,new EstadoActivo(), EnumPrioridadReclamo.alta.getPrioridad());
		ReclamoManager.getInstance().guardarReclamo(reclamoPrueba3);
		
		IReclamo reclamoPrueba4 = new Reclamo("Elcano","4040","50","60",new Date().toString(),EnumTipoReclamo.elementosViales.getTipo(),"Rocio","No hay señalizacion",null,new EstadoEnProgreso(), EnumPrioridadReclamo.media.getPrioridad());
		ReclamoManager.getInstance().guardarReclamo(reclamoPrueba4);
		
		IReclamo reclamoPrueba5 = new Reclamo("Simbron","500","34","20",new Date().toString(),EnumTipoReclamo.roturaElementosPublicos.getTipo(),"Matias","Se rompio asiento de la plaza", null, new EstadoSuspendido(), EnumPrioridadReclamo.baja.getPrioridad());
		ReclamoManager.getInstance().guardarReclamo(reclamoPrueba5);
		
	}

}
