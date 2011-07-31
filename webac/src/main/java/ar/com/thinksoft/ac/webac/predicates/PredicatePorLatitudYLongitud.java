package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicatePorLatitudYLongitud  extends Predicate<Reclamo>{

	public Predicate<Reclamo> filtrar(final int latitud, final int longitud){
		return new Predicate<Reclamo>() {
			public boolean match(Reclamo reclamo) {
                return reclamo.getLatitudIncidente() == latitud && reclamo.getLongitudIncidente() == longitud;
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
