package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

@SuppressWarnings("serial")
/**
 * @author Matias
 */
public class PredicateReclamoPorLatitudYLongitud extends PredicateReclamo{
	
	public PredicateReclamoPorLatitudYLongitud(Reclamo reclamo) {
		super(reclamo);
	}

	public boolean match(Reclamo reclamo) {
		return (super.getReclamo().getLatitudIncidente() == reclamo.getLatitudIncidente() && super.getReclamo().getLongitudIncidente() == reclamo.getLongitudIncidente());
	}

}
