package ar.com.thinksoft.ac.intac;

import java.util.ArrayList;
import java.util.List;

public enum EnumTipoReclamo {
	
		bache("Bache"),
		abl("ABL"),
		roturaElementosPublicos("Rotura Elem. Publicos"),
		elementosViales("Elementos Viales"),
		inundaciones("Inundaciones"),
		transportePublico("Transporte Publico"),
		accesibilidad("Accesibilidad");
		
		private String tipo;
		private static List<String> listaTiposReclamo = new ArrayList<String>();
		 
	    private EnumTipoReclamo(String tipo) {
	    	this.tipo = tipo;
	    }
	    
	    public static List<String> getListaTiposReclamo(){
	    	listaTiposReclamo.clear();
	    	listaTiposReclamo.add(EnumTipoReclamo.abl.getTipo());
	    	listaTiposReclamo.add(EnumTipoReclamo.bache.getTipo());
	    	listaTiposReclamo.add(EnumTipoReclamo.roturaElementosPublicos.getTipo());
	    	listaTiposReclamo.add(EnumTipoReclamo.elementosViales.getTipo());
	    	listaTiposReclamo.add(EnumTipoReclamo.inundaciones.getTipo());
	    	listaTiposReclamo.add(EnumTipoReclamo.transportePublico.getTipo());
	    	listaTiposReclamo.add(EnumTipoReclamo.accesibilidad.getTipo());
	    	
	    	return listaTiposReclamo;
	    }

	    public String getTipo() {
	    	return tipo;
	    }
}
