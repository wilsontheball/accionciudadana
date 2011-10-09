package ar.com.thinksoft.ac.intac;

import java.io.Serializable;

public interface IUsuario extends Serializable {

	/**
	 * Verifica si ese codido de seguridad se encuentra en alguno de los
	 * permisos seteados.
	 * 
	 * @param codigoSeguridad
	 */
	boolean hasUsuarioYContrasenia(String nombreUsuario, String contrasenia);

	public String getContrasenia();

	public String getNombreUsuario();

	public String getDni();

	public String getMail();

	public String getApellido();

	public String getNombre();

	public String getTelefono();

	public void setContrasenia(String contrasenia);

	public void setNombreUsuario(String nombreUsuario);

	public void setDni(String DNI);

	public void setMail(String mail);

	public void setApellido(String apellido);

	public void setNombre(String nombre);

	public void setTelefono(String telefono);

}
