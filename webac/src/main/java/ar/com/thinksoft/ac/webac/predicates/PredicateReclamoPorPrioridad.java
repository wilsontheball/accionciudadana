package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

@SuppressWarnings("serial")
public class PredicateReclamoPorPrioridad extends PredicateReclamo{

	private String prioridad = "";
	
	public PredicateReclamoPorPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public boolean match(Reclamo reclamo) {
		return this.prioridad.equals(reclamo.getPrioridad());
	}
}
