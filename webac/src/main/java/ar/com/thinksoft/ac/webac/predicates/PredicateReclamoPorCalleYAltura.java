package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;


@SuppressWarnings("serial")
/**
 * @author Matias
 */
public class PredicateReclamoPorCalleYAltura extends PredicateReclamo {
	
	private String calle = "";
	private int altura = 0;
	
	public PredicateReclamoPorCalleYAltura(String calle, int altura) {
		this.calle = calle;
		this.altura = altura;
	}

	public boolean match(Reclamo reclamo) {
		return (this.calle.equals(reclamo.getCalleIncidente()) && this.altura == reclamo.getAlturaIncidente());
	}

}
