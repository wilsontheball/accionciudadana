package ar.com.thinksoft.ac.webac.usuario;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IPermitible;
import ar.com.thinksoft.ac.intac.IUsuario;

public class Usuario implements IUsuario {

	private List<IPermiso> permisos;
	private String nombreUsuario;
	private String contrasenia;

	public Usuario() {
		this.permisos = new ArrayList<IPermiso>();
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

}
