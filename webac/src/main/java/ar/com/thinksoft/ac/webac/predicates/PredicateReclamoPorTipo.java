package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

@SuppressWarnings("serial")
/**
 * @author Matias
 */
public class PredicateReclamoPorTipo extends PredicateReclamo{

	private String tipo = "";
	
	public PredicateReclamoPorTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean match(Reclamo reclamo) {
		return this.tipo.equals(reclamo.getTipoIncidente());
	}
}
