package ar.com.thinksoft.ac.webac.predicates.registro;

import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.web.usuario.form.UsuarioFilterObject;

import com.db4o.query.Predicate;

public class PredicateFiltroUsuario extends Predicate<Usuario> {

	private UsuarioFilterObject filterObject;

	public PredicateFiltroUsuario(UsuarioFilterObject filterObject) {
		this.filterObject = filterObject;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7637062900088508968L;

	@Override
	public boolean match(Usuario usuario) {
		return usuario.getApellido().contains(filterObject.getApellido())
				|| usuario.getNombre().contains(filterObject.getNombre())
				|| usuario.getNombreUsuario().contains(filterObject.getNombreUsuario());
	}

}
