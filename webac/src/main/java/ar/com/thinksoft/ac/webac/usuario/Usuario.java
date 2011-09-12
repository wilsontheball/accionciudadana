package ar.com.thinksoft.ac.webac.usuario;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IPermitible;
import ar.com.thinksoft.ac.intac.IUsuario;

@SuppressWarnings("serial")
public class Usuario implements IUsuario {

	private List<IPermiso> permisos;
	private String nombreUsuario;
	private String contrasenia;
	private Long telefono;
	private String nombre;
	private String apellido;
	private String mail;
	private Long dni;

	public Usuario() {
		this.permisos = new ArrayList<IPermiso>();
		this.setApellido("");
		this.setContrasenia("");
		this.setDni(0L);
		this.setMail("");
		this.setNombre("");
		this.setNombreUsuario("");
		this.setTelefono(0L);
	}

	@Override
	public boolean tenesPermisosPara(IPermitible permitible) {
		return this.posee(permitible.getPermisoNecesario());
	}

	/**
	 * Determina si el permiso que se le pasa por parametro esta dentro de su
	 * coleccion de permisos
	 */
	private boolean posee(IPermiso permiso) {

		for (IPermiso permisoPropio : this.getPermisos()) {
			if (permisoPropio.equals(permiso))
				return true;
		}

		return false;
	}

	public void addPermiso(IPermiso permiso) {
		this.permisos.add(permiso);
	}

	// ************************* GETTERS & SETTERS ***********************

	private List<IPermiso> getPermisos() {
		return this.permisos;
	}

	@Override
	public int cantidadPermisos() {
		return this.getPermisos().size();
	}

	@Override
	public boolean hasUsuarioYContrasenia(String usuario, String contrasenia) {
		return this.getNombreUsuario().equals(usuario)
				&& this.getContrasenia().equals(contrasenia);
	}

	@Override
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	@Override
	public String getContrasenia() {
		return this.contrasenia;
	}

	@Override
	public Long getDni() {
		return this.dni;
	}

	@Override
	public String getMail() {
		return this.mail;
	}

	@Override
	public String getApellido() {
		return this.apellido;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public Long getTelefono() {
		return this.telefono;
	}

	@Override
	public void setDni(Long dni) {
		this.dni = dni;
	}

	@Override
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

}
