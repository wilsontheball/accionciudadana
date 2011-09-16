package ar.com.thinksoft.ac.webac.usuario;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.IPermiso;
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
	
	private List<IPermiso> getPermisosAdministrativos(){
		return new ArrayList<IPermiso>();
	}

	public void toAdministrador(IUsuario usuario) {
		usuario.setPermisos(this.getPermisosAdministrativos());
	}

	public void toCiudadano(IUsuario usuario) {
		usuario.setPermisos(this.getPermisosCiudadano());
		
	}

	private List<IPermiso> getPermisosCiudadano() {
		return new ArrayList<IPermiso>();
	}

	public void toOperario(IUsuario usuario) {
		usuario.setPermisos(this.getPermisosOperario());
	}

	private List<IPermiso> getPermisosOperario() {
		return new ArrayList<IPermiso>();
	}
}
