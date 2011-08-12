package ar.com.thinksoft.ac.webac.reclamo;

public enum EnumPrioridadReclamo {
	
	alta("Alta"), media("Media"), baja("Baja");
	
	private String prioridad;
	 
    private EnumPrioridadReclamo(String prioridad) {
    	this.prioridad = prioridad;
    }

    public String getPrioridad() {
    	return prioridad;
    }

};
