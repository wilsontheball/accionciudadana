package ar.com.thinksoft.ac.webac.predicates.registro;

import ar.com.thinksoft.ac.intac.IUsuario;

import com.db4o.query.Predicate;

public class PredicateTodosLosUsuarios extends Predicate<IUsuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1154916017595668898L;

	@Override
	public boolean match(IUsuario arg0) {
		return true;
	}

}
