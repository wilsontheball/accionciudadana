package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

@SuppressWarnings("serial")
/**
 * @author Matias
 */
public class PredicateReclamoPorCiudadano extends PredicateReclamo{
	
	public PredicateReclamoPorCiudadano(Reclamo reclamo) {
		super(reclamo);
	}

	public boolean match(Reclamo reclamo) {
		return super.getReclamo().getCiudadanoGeneradorReclamo().equals(reclamo.getCiudadanoGeneradorReclamo());
	}

}
