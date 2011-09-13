package ar.com.thinksoft.ac.intac;

import java.util.ArrayList;
import java.util.List;

public enum EnumEstadosReclamo {
	
	activo("Activo"), 
	baja("Baja"), 
	cancelado("Cancelado"), 
	demorado("Demorado"),
	enProgreso("En progreso"),
	suspendido("Suspendido"),
	terminado("Terminado");
	
	private String estado;
	private static List<String> listaEstadoReclamo = new ArrayList<String>();
	 
    private EnumEstadosReclamo(String estado) {
    	this.estado = estado;
    }
    
    public static List<String> getlistaEstadosReclamo(){
    	listaEstadoReclamo.clear();
    	listaEstadoReclamo.add(EnumEstadosReclamo.activo.getEstado());
    	listaEstadoReclamo.add(EnumEstadosReclamo.baja.getEstado());
    	listaEstadoReclamo.add(EnumEstadosReclamo.cancelado.getEstado());
    	listaEstadoReclamo.add(EnumEstadosReclamo.demorado.getEstado());
    	listaEstadoReclamo.add(EnumEstadosReclamo.enProgreso.getEstado());
    	listaEstadoReclamo.add(EnumEstadosReclamo.suspendido.getEstado());
    	listaEstadoReclamo.add(EnumEstadosReclamo.terminado.getEstado());
    	
    	return listaEstadoReclamo;
    }

    public String getEstado() {
    	return estado;
    }

};
