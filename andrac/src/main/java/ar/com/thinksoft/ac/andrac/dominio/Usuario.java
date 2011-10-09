package ar.com.thinksoft.ac.andrac.dominio;

import ar.com.thinksoft.ac.intac.utils.classes.UsuarioMovil;

/**
 * DTO que contiene datos de usuario.
 * 
 * @since 25-09-2011
 * @author Paul
 */
public class Usuario extends UsuarioMovil {

	private static final long serialVersionUID = 1L;

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
		return this.dni;
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
		return this.telefono;
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

}
