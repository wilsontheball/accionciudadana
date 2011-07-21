package ar.com.thinksoft.ac.webac.usuario;

import ar.com.thinksoft.ac.intac.IUsuario;

/**
 * 
 * @author Wilson
 * 
 * Objeto encargado de crear un nuevo usuario con los permisos standards
 *
 */
public class UsuarioFactory {
	
	
	public IUsuario construirCiudadano(){
		//TODO: terminar
		return this.crearUsuario();
	}
	
	public IUsuario construirOperador(){
		//TODO: terminar
		return this.crearUsuario();
	}
	
	public IUsuario construirAdministrador(){
		//TODO: terminar
		return this.crearUsuario();
	}
	
	private IUsuario construidAministrativo(){
		//TODO: terminar
		return this.crearUsuario();
	}
	
	
	private IUsuario crearUsuario() {
		return new Usuario();
	}

}
