package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.intac.IReclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicatePorLatitudYLongitud  extends Predicate<IReclamo>{

	public Predicate<IReclamo> filtrar(final String latitud, final String longitud){
		return new Predicate<IReclamo>() {
			public boolean match(IReclamo reclamo) {
                return reclamo.getLatitudIncidente().equals(latitud) && reclamo.getLongitudIncidente().equals(longitud);
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
