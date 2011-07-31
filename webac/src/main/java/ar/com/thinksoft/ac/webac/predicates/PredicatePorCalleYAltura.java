package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicatePorCalleYAltura extends Predicate<Reclamo>{

	public Predicate<Reclamo> filtrar(final String calle, final int altura){
		return new Predicate<Reclamo>() {
			public boolean match(Reclamo reclamo) {
                return (reclamo.getCalleIncidente().equals(calle) && reclamo.getAlturaIncidente() == altura);
            }
        };
	}

	/**
	 * @deprecated
	 */
	@Override
	public boolean match(Reclamo arg0) {
		return false;
	}

}
