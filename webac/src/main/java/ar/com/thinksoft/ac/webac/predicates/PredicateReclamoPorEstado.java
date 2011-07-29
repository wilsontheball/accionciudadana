package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

/**
 * @author Matias
 */
@SuppressWarnings("serial")
public class PredicateReclamoPorEstado extends PredicateReclamo{

	private String descripcionEstado = "";
	
	public PredicateReclamoPorEstado(String estado) {
		this.descripcionEstado = estado;
	}

	public boolean match(Reclamo reclamo) {
		return this.descripcionEstado.equals(reclamo.getEstado().getDescripcionEstado());
	}
}
