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
		usuario.addRole("OPERADOR");
		return usuario;
	}

	public Usuario construirAdministrador() {
		Usuario usuario = this.crearUsuario();
		usuario.addRole("ADMIN");
		return usuario;
	}
	
	public Usuario construidGodMode(){
		Usuario usuario = this.crearUsuario();
		usuario.setNombre("god");
		usuario.setApellido("god");
		usuario.setContrasenia("god");
		usuario.setNombreUsuario("god");
		usuario.addRole("ADMIN");
		usuario.addRole("CIUDADANO");
		usuario.addRole("OPERADOR");
		return usuario;
		
	}

	// ********************** METODOS PRIVADOS *********************

	private Usuario crearUsuario() {
		return new Usuario();
	}

	
}
