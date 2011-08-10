package ar.com.thinksoft.ac.webac.web;

import ar.com.thinksoft.ac.intac.IUsuario;

public class Context {

	private static Context instance;

	public static Context getInstance() {
		if (instance == null)
			instance = new Context();
		return instance;
	}

	private IUsuario usuario;

	public void setUsuario(IUsuario usuario) {
		this.usuario = usuario;
	}
	
	public IUsuario getUsuario(){
		return this.usuario;
	}

}
