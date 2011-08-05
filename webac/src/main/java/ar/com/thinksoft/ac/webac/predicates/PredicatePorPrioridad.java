package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.intac.IReclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicatePorPrioridad extends Predicate<IReclamo>{

	public Predicate<IReclamo> filtrar(final String filtroParam){
		return new Predicate<IReclamo>() {
			public boolean match(IReclamo reclamo) {
                return reclamo.getPrioridad().equals(filtroParam);
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
