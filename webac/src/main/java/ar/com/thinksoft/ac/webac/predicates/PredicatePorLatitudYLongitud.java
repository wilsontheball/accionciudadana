package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.intac.IReclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicatePorLatitudYLongitud  extends Predicate<IReclamo>{

	public Predicate<IReclamo> filtrar(final int latitud, final int longitud){
		return new Predicate<IReclamo>() {
			public boolean match(IReclamo reclamo) {
                return reclamo.getLatitudIncidente() == latitud && reclamo.getLongitudIncidente() == longitud;
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
