package ar.com.thinksoft.ac.webac.predicates.login;

import ar.com.thinksoft.ac.intac.IUsuario;

import com.db4o.query.Predicate;

@SuppressWarnings("serial")
public class PredicateLogin extends Predicate<IUsuario>{

	public Predicate<IUsuario> filtrar(final String nombreUsuario, final String contrasenia){
		return new Predicate<IUsuario>() {
			public boolean match(IUsuario usuario) {
				return usuario.hasUsuarioYContrasenia(nombreUsuario,contrasenia);
            }
        };
	}
	
	@Override
	@Deprecated
	public boolean match(IUsuario arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
