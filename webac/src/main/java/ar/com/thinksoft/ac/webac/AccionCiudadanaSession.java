package ar.com.thinksoft.ac.webac;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;

import ar.com.thinksoft.ac.webac.usuario.Usuario;

public class AccionCiudadanaSession extends AuthenticatedWebSession {

	public AccionCiudadanaSession(Request request) {
		super(request);
	}

	private static final long serialVersionUID = 6984467993665072474L;
	private Usuario usuario;

	@Override
	public Roles getRoles() {
		return this.usuario.getRoles();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean authenticate(String username, String password) {
		return this.usuario != null;
	}


}
