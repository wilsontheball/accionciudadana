package ar.com.thinksoft.ac.webac.predicates.registro;

import ar.com.thinksoft.ac.webac.usuario.Usuario;

import com.db4o.query.Predicate;

public class PredicateTodosLosUsuarios extends Predicate<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1154916017595668898L;

	@Override
	public boolean match(Usuario arg0) {
		return true;
	}

}
