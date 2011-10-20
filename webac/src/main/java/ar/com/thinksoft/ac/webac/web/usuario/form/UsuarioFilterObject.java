package ar.com.thinksoft.ac.webac.web.usuario.form;


public class UsuarioFilterObject{

	private String nombre;
	private String apellido;
	private String nombreUsuario;
	private String tipo;
	
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public boolean isNull() {
		return this.getApellido() == null && this.getNombre() == null && this.getNombreUsuario() == null && this.getTipo() == null;
	}
	

}
