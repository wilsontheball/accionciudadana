package ar.com.thinksoft.ac.intac;

import java.util.ArrayList;
import java.util.List;

public enum EnumPrioridadReclamo {
	
	noAsignada("No asignada"),alta("Alta"), media("Media"), baja("Baja");
	
	private String prioridad;
	private static List<String> listaPrioridadReclamo = new ArrayList<String>();
	 
    private EnumPrioridadReclamo(String prioridad) {
    	this.prioridad = prioridad;
    }
    
    public static List<String> getlistaPrioridadReclamo(){
    	listaPrioridadReclamo.clear();
    	listaPrioridadReclamo.add(EnumPrioridadReclamo.noAsignada.getPrioridad());
    	listaPrioridadReclamo.add(EnumPrioridadReclamo.alta.getPrioridad());
    	listaPrioridadReclamo.add(EnumPrioridadReclamo.media.getPrioridad());
    	listaPrioridadReclamo.add(EnumPrioridadReclamo.baja.getPrioridad());
    	
    	return listaPrioridadReclamo;
    }

    public String getPrioridad() {
    	return prioridad;
    }

};
