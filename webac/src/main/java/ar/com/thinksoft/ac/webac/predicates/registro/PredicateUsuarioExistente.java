package ar.com.thinksoft.ac.webac.predicates.registro;

import ar.com.thinksoft.ac.webac.usuario.Usuario;

import com.db4o.query.Predicate;

public class PredicateUsuarioExistente{

	public Predicate<Usuario> exist(final String nombreUsuario){
		return new Predicate<Usuario>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 8973645613479714340L;

			@Override
			public boolean match(Usuario usuario) {
				return usuario.getNombreUsuario().equals(nombreUsuario);
			}
		};
	}
		

}
