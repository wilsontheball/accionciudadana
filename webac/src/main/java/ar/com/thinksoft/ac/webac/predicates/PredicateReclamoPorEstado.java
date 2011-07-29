package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

/**
 * @author Matias
 */
@SuppressWarnings("serial")
public class PredicateReclamoPorEstado extends PredicateReclamo{

	public PredicateReclamoPorEstado(Reclamo reclamo) {
		super(reclamo);
	}

	public boolean match(Reclamo reclamo) {
		return super.getReclamo().getEstado().getDescripcionEstado().equals(reclamo.getEstado().getDescripcionEstado());
	}
}
