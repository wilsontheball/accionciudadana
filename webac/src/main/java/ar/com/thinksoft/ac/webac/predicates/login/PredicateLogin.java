package ar.com.thinksoft.ac.webac.predicates.login;

import ar.com.thinksoft.ac.webac.usuario.Usuario;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicateLogin extends Predicate<Usuario>{

	public Predicate<Usuario> filtrar(final String nombreUsuario, final String contrasenia){
		return new Predicate<Usuario>() {
			public boolean match(Usuario usuario) {
				return usuario.hasUsuarioYContrasenia(nombreUsuario,contrasenia);
            }
        };
	}
	
	@Override
	@Deprecated
	public boolean match(Usuario arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
