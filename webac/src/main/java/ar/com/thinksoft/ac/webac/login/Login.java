package ar.com.thinksoft.ac.webac.login;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.predicates.login.PredicateLogin;
import ar.com.thinksoft.ac.webac.repository.Repository;

public class Login {
	
	private String nombreUsuario;
	private String contrasenia;
	
	public Login(String nombreUsuario,String contrasenia) {
		this.setNombreUsuario(nombreUsuario);
		this.setContarsenia(contrasenia);
	}

	private void setContarsenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	private void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public IUsuario login(){
		IUsuario usuario = Repository.getInstance().query(new PredicateLogin().filtrar(this.nombreUsuario, this.contrasenia)).get(0);
		return usuario;
	}
	
	

}
