package ar.com.thinksoft.ac.wilsond.usuario;

import java.util.List;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IPermitible;
import ar.com.thinksoft.ac.intac.utils.classes.UsuarioMovil;

/**
 * DTO que contiene datos de usuario.
 * 
 * @since 27-09-2011
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
		return this.password;
	}

	public String getNombreUsuario() {
		return this.nick;
	}

	public String getDni() {
		return this.dni.toString();
	}

	public String getMail() {
		return this.mail;
	}

	public String getApellido() {
		return this.apellido;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getTelefono() {
		return this.telefono.toString();
	}

	public void setContrasenia(String contrasenia) {
		this.password = contrasenia;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nick = nombreUsuario;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setPermisos(List<IPermiso> permisosAdministrativos) {
		// TODO Auto-generated method stub
		
	}

}
