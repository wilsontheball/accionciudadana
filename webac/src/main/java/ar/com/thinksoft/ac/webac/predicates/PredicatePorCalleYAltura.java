package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.intac.IReclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicatePorCalleYAltura extends Predicate<IReclamo>{

	public Predicate<IReclamo> filtrar(final String calle, final String altura){
		return new Predicate<IReclamo>() {
			public boolean match(IReclamo reclamo) {
                return (reclamo.getCalleIncidente().equals(calle) && reclamo.getAlturaIncidente().equals(altura));
            }
        };
	}

	/**
	 * @deprecated
	 */
	@Override
	public boolean match(IReclamo arg0) {
		return false;
	}

}
