package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicatePorEstado extends Predicate<Reclamo>{

	public Predicate<Reclamo> filtrar(final String filtroParam){
		return new Predicate<Reclamo>() {
			public boolean match(Reclamo reclamo) {
                return reclamo.getEstado().getDescripcionEstado().equals(filtroParam);
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
