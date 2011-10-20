package ar.com.thinksoft.ac.webac.usuario;


import org.apache.wicket.authorization.strategies.role.Roles;
import ar.com.thinksoft.ac.intac.IUsuario;

@SuppressWarnings("serial")
public class Usuario implements IUsuario {

	private Roles permisos;
	private String nombreUsuario;
	private String contrasenia;
	private String telefono;
	private String nombre;
	private String apellido;
	private String mail;
	private String dni;
	private String tipo;

	public Usuario() {
		this.permisos = new Roles();
		this.setApellido("");
		this.setContrasenia("");
		this.setDni("");
		this.setMail("");
		this.setNombre("");
		this.setNombreUsuario("");
		this.setTelefono("");
		this.setTipo("");
	}

	public void addRole(String role) {
		this.permisos.add(role);
		
		if("ADMIN".equals(role)){
			this.setTipo(EnumTiposUsuario.Administrador.getTipoUsuario());
		}else{
			if("OPERADOR".equals(role)){
				this.setTipo(EnumTiposUsuario.Operador.getTipoUsuario());
			}else{
				if("CIUDADANO".equals(role)){
					this.setTipo(EnumTiposUsuario.Ciudadano.getTipoUsuario());
				}
			}
		}
		
	}
	
	// ************************* GETTERS & SETTERS ***********************

	public Roles getRoles() {
		return this.permisos;
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
	public String getDni() {
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
	public String getTelefono() {
		return this.telefono;
	}

	@Override
	public void setDni(String dni) {
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
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}


}
