package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

@SuppressWarnings("serial")
/**
 * @author Matias
 */
public class PredicateReclamoPorLatitudYLongitud extends PredicateReclamo{
	
	private int latitud = 0;
	private int longitud = 0;
	
	public PredicateReclamoPorLatitudYLongitud(int latitud, int longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public boolean match(Reclamo reclamo) {
		return (this.latitud == reclamo.getLatitudIncidente() && this.longitud == reclamo.getLongitudIncidente());
	}

}
