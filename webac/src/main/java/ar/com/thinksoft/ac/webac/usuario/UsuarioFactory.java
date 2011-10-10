package ar.com.thinksoft.ac.webac.usuario;

/**
 * 
 * @author Wilson
 * 
 *         Objeto encargado de crear un nuevo usuario con los permisos standards
 * 
 */
public class UsuarioFactory {

	public Usuario construirCiudadano() {
		Usuario usuario = this.crearUsuario();
		usuario.addRole("CIUDADANO");
		return usuario;
	}

	public Usuario construirOperador() {
		Usuario usuario = this.crearUsuario();
		usuario.addRole("OPERARIO");
		return usuario;
	}

	public Usuario construirAdministrador() {
		Usuario usuario = this.crearUsuario();
		usuario.addRole("ADMIN");
		return usuario;
	}

	// ********************** METODOS PRIVADOS *********************

	private Usuario crearUsuario() {
		return new Usuario();
	}

	
}
