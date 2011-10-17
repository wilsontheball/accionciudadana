package ar.com.thinksoft.ac.webac.predicates;

import ar.com.thinksoft.ac.intac.IReclamo;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicatePorCiudadano extends Predicate<IReclamo>{

	public Predicate<IReclamo> filtrar(final String filtroParam){
		return new Predicate<IReclamo>() {
			public boolean match(IReclamo reclamo) {
                return reclamo.getCiudadanoGeneradorReclamo().equals(filtroParam) || isAsociado(reclamo, filtroParam);
            }
        };
	}
	
	private boolean isAsociado(IReclamo reclamo, String ciudadano){
		for(IReclamo r : reclamo.getReclamosAsociados()){
			if(ciudadano.equals(r.getCiudadanoGeneradorReclamo()))
				return true;
		}
		return false;
		
	}

	/**
	 * @deprecated
	 */
	@Override
	public boolean match(IReclamo arg0) {
		return false;
	}
}
