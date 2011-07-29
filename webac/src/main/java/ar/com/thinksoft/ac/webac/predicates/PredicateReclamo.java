package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
/**
 * @author Matias
 */
public abstract class PredicateReclamo extends Predicate<Reclamo>{
	
	public abstract boolean match(Reclamo reclamo);

}
