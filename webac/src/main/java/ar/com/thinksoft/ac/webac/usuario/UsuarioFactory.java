package ar.com.thinksoft.ac.webac.usuario;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.IPermiso;

/**
 * 
 * @author Wilson
 * 
 *         Objeto encargado de crear un nuevo usuario con los permisos standards
 * 
 */
public class UsuarioFactory {

	public Usuario construirCiudadano() {
		Usuario usuario = this.construirUsuarioComun();
		return usuario;
	}

	public Usuario construirOperador() {
		Usuario usuario = this.construirAministrativo();
		return usuario;
	}

	public Usuario construirAdministrador() {
		Usuario usuario = this.construirAministrativo();
		return usuario;
	}

	// ********************** METODOS PRIVADOS *********************

	/**
	 * Construye un administrativo que es un usuario comun con los permisos
	 * propios de un administrativo
	 */
	private Usuario construirAministrativo() {
		Usuario usuario = this.construirUsuarioComun();
		return usuario;
	}

	private Usuario crearUsuario() {
		return new Usuario();
	}

	/**
	 * Construye un usuario base con los permisos comunes
	 */
	private Usuario construirUsuarioComun() {

		Usuario usuario = this.crearUsuario();
		return usuario;
	}
	
	private List<IPermiso> getPermisosAdministrativos(){
		return new ArrayList<IPermiso>();
	}

	public void toAdministrador(Usuario usuario) {
	}

	public void toCiudadano(Usuario usuario) {
		
	}

	private List<IPermiso> getPermisosCiudadano() {
		return new ArrayList<IPermiso>();
	}

	public void toOperario(Usuario usuario) {
	}

	private List<IPermiso> getPermisosOperario() {
		return new ArrayList<IPermiso>();
	}

	public void bloquear(Usuario usuario) {
	}

	private List<IPermiso> getPermisosBloqueados() {
		return new ArrayList<IPermiso>();
	}
}
