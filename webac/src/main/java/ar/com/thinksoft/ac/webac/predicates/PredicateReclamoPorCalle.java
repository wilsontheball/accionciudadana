package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

@SuppressWarnings("serial")
/**
 * @author Matias
 */
public class PredicateReclamoPorCalle extends PredicateReclamo{
	
	private String calle = "";
	
	public PredicateReclamoPorCalle(String calle){
		this.calle = calle;		
	}
	
	public boolean match(Reclamo reclamo) {
		return (this.calle.equals(reclamo.getCalleIncidente()));
	}

}
