package ar.com.thinksoft.ac.webac.web;

import ar.com.thinksoft.ac.intac.IUsuario;

public class Context {

	private static Context instance;

	public static Context getInstance() {
		if (instance == null)
			instance = new Context();
		return instance;
	}

	
	public void invalidate(){
		Context.instance = new Context();
	}
	
	private IUsuario usuario;

	public void setUsuario(IUsuario usuario) {
		this.usuario = usuario;
	}
	
	public IUsuario getUsuario(){
		return this.usuario;
	}

	// mejor crear un null user
	public boolean isUserSignedIn() {
		return this.getUsuario() != null;
	}

}
