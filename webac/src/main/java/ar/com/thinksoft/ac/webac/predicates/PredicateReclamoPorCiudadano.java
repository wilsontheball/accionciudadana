package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;


@SuppressWarnings("serial")
/**
 * @author Matias
 */
public class PredicateReclamoPorCiudadano extends PredicateReclamo{
	
	private String nombreCiudadano;
	
	public PredicateReclamoPorCiudadano(String ciudadano) {
		this.nombreCiudadano = ciudadano;
	}

	public boolean match(Reclamo reclamo) {
		return reclamo.getCiudadanoGeneradorReclamo().equals(this.nombreCiudadano);
	}
	
	public String getCiudadano(){
		return this.nombreCiudadano;
		
	}

}
