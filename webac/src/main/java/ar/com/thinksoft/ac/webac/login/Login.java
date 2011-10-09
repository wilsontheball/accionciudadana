package ar.com.thinksoft.ac.webac.login;

import ar.com.thinksoft.ac.webac.login.exceptions.UserNotFoundException;
import ar.com.thinksoft.ac.webac.predicates.login.PredicateLogin;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;

public class Login {

	private String nombreUsuario;
	private String contrasenia;

	public Login(String nombreUsuario,String contrasenia) {
		this.setNombreUsuario(nombreUsuario);
		this.setContarsenia(contrasenia);
	}
	
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public void setContarsenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Usuario login() {
		try {
			Usuario usuario = Repository
					.getInstance()
					.query(new PredicateLogin().filtrar(this.nombreUsuario,
							this.contrasenia)).get(0);
			return usuario;
		} catch (Exception e) {
			throw new UserNotFoundException(this.nombreUsuario);
		}

	}

	public boolean isUsuarioExistente() {
		try {
			Usuario usuario = Repository
					.getInstance()
					.query(new PredicateLogin().filtrar(this.nombreUsuario,
							this.contrasenia)).get(0);
			return usuario.getNombreUsuario().equals(this.nombreUsuario);
		} catch (Exception e) {
			return false;
		}
	}

}
