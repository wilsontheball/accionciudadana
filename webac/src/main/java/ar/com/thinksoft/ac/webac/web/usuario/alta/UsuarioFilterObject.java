package ar.com.thinksoft.ac.webac.web.usuario.alta;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IPermitible;
import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.usuario.Usuario;

@SuppressWarnings("serial")
public class UsuarioFilterObject extends Usuario{

	private String nombre = "";
	private String apellido = "";
	private String nombreUsuario = "";
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public boolean tenesPermisosPara(IPermitible permitible) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPermiso(IPermiso permiso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int cantidadPermisos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasUsuarioYContrasenia(String nombreUsuario,
			String contrasenia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getContrasenia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getDni() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTelefono() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContrasenia(String contrasenia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDni(Long DNI) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMail(String mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTelefono(Long telefono) {
		// TODO Auto-generated method stub
		
	}

	

}
