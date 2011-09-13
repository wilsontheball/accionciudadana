package ar.com.thinksoft.ac.webac.predicates.registro;

import ar.com.thinksoft.ac.intac.IUsuario;

import com.db4o.query.Predicate;

public class PredicateUsuarioExistente{

	public Predicate<IUsuario> exist(final String nombreUsuario){
		return new Predicate<IUsuario>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 8973645613479714340L;

			@Override
			public boolean match(IUsuario usuario) {
				return usuario.getNombreUsuario().equals(nombreUsuario);
			}
		};
	}
		

}
