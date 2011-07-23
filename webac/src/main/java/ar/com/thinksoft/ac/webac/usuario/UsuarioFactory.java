package ar.com.thinksoft.ac.webac.usuario;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.HomePagePermiso;

/**
 * 
 * @author Wilson
 * 
 *         Objeto encargado de crear un nuevo usuario con los permisos standards
 * 
 */
public class UsuarioFactory {

	public IUsuario construirCiudadano() {
		IUsuario usuario = this.construirUsuarioComun();
		return usuario;
	}

	public IUsuario construirOperador() {
		IUsuario usuario = this.construirAministrativo();
		return usuario;
	}

	public IUsuario construirAdministrador() {
		IUsuario usuario = this.construirAministrativo();
		return usuario;
	}

	// ********************** METODOS PRIVADOS *********************

	/**
	 * Construye un administrativo que es un usuario comun con los permisos
	 * propios de un administrativo
	 */
	private IUsuario construirAministrativo() {
		IUsuario usuario = this.construirUsuarioComun();
		return usuario;
	}

	private IUsuario crearUsuario() {
		return new Usuario();
	}

	/**
	 * Construye un usuario base con los permisos comunes
	 */
	private IUsuario construirUsuarioComun() {

		IUsuario usuario = this.crearUsuario();

		usuario.addPermiso(new HomePagePermiso());

		return usuario;
	}
}
