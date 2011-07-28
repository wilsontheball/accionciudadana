package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

@SuppressWarnings("serial")
/**
 * @author Matias
 */
public class PredicateReclamoPorTipo extends PredicateReclamo{

	public PredicateReclamoPorTipo(Reclamo reclamo) {
		super(reclamo);
	}

	public boolean match(Reclamo reclamo) {
		return super.getReclamo().getTipoIncidente().equals(reclamo.getTipoIncidente());
	}
}
