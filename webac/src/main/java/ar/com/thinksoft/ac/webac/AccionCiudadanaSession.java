package ar.com.thinksoft.ac.webac;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import ar.com.thinksoft.ac.intac.IUsuario;

public class AccionCiudadanaSession extends AuthenticatedWebSession{

	private static final long serialVersionUID = 6984467993665072474L;
	private IUsuario usuario;
	
	public AccionCiudadanaSession(Request request) {
		super(request);
	}

	@Override
	public Roles getRoles() {
		return null;
	}

	public IUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(IUsuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	

}
