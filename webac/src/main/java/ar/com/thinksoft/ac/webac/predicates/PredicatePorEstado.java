package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.intac.IReclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicatePorEstado extends Predicate<IReclamo>{

	public Predicate<IReclamo> filtrar(final String filtroParam){
		return new Predicate<IReclamo>() {
			public boolean match(IReclamo reclamo) {
                return reclamo.getEstadoDescripcion().equals(filtroParam);
            }
        };
	}
	
	/*
	 * Se filtra por los que no tienen ese estado
	 */
	public Predicate<IReclamo> filtrarNot(final String filtroParam){
		return new Predicate<IReclamo>() {
			public boolean match(IReclamo reclamo) {
                return (!reclamo.getEstadoDescripcion().equals(filtroParam));
            }
        };
	}
	
	public Predicate<IReclamo> isNotDownFiltro(){
		return new Predicate<IReclamo>() {
			public boolean match(IReclamo reclamo) {
                return (reclamo.isNotDown());
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
