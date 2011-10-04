package ar.com.thinksoft.ac.webac;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import ar.com.thinksoft.ac.webac.usuario.Usuario;

public class AccionCiudadanaSession extends AuthenticatedWebSession{

	private static final long serialVersionUID = 6984467993665072474L;
	private Usuario usuario;
	
	public AccionCiudadanaSession(Request request) {
		super(request);
	}

	@Override
	public Roles getRoles() {
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	

}
