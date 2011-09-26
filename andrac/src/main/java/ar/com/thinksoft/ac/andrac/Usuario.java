package ar.com.thinksoft.ac.andrac;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IPermitible;
import ar.com.thinksoft.ac.intac.utils.classes.UsuarioMovil;

/**
 * DTO que contiene datos de usuario.
 * 
 * @since 25-09-2011
 * @author Paul
 */
public class Usuario extends UsuarioMovil {

	private static final long serialVersionUID = 1L;

	public boolean tenesPermisosPara(IPermitible permitible) {
		// TODO Auto-generated method stub
		return false;
	}

	public void addPermiso(IPermiso permiso) {
		// TODO Auto-generated method stub

	}

	public int cantidadPermisos() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean hasUsuarioYContrasenia(String nombreUsuario,
			String contrasenia) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getContrasenia() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombreUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getDni() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getApellido() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getTelefono() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setContrasenia(String contrasenia) {
		// TODO Auto-generated method stub

	}

	public void setNombreUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub

	}

	public void setDni(Long DNI) {
		// TODO Auto-generated method stub

	}

	public void setMail(String mail) {
		// TODO Auto-generated method stub

	}

	public void setApellido(String apellido) {
		// TODO Auto-generated method stub

	}

	public void setNombre(String nombre) {
		// TODO Auto-generated method stub

	}

	public void setTelefono(Long telefono) {
		// TODO Auto-generated method stub

	}

}
