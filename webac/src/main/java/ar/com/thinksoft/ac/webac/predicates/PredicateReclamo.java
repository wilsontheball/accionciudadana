package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.webac.reclamo.Reclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
/**
 * @author Matias
 */
public abstract class PredicateReclamo extends Predicate<Reclamo>{
	
	private Reclamo reclamo;
	
	public PredicateReclamo(Reclamo reclamo){
		this.setReclamo(reclamo);
	}
	
	public abstract boolean match(Reclamo reclamo);

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	public Reclamo getReclamo() {
		return reclamo;
	}
}
