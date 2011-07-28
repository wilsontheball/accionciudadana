package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

@SuppressWarnings("serial")
/**
 * @author Matias
 */
public class PredicateReclamoPorCalle extends PredicateReclamo{
	
	public PredicateReclamoPorCalle(Reclamo reclamo) {
		super(reclamo);
	}

	public boolean match(Reclamo reclamo) {
		return (super.getReclamo().getCalleIncidente().equals(reclamo.getCalleIncidente()));
	}

}
