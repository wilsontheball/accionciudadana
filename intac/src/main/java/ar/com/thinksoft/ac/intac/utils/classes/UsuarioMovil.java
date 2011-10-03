package ar.com.thinksoft.ac.intac.utils.classes;

import ar.com.thinksoft.ac.intac.IUsuario;

/**
 * Es un DTO para intercambiar los datos de usuario entre Andrac y Wilsond .
 * Cumpliendo con GSON la clase asegura que los objetos de ambos extremos tengan
 * los mismos nombres de atributos.
 * 
 * @since 25-09-2011
 * @author Paul
 */
public abstract class UsuarioMovil implements IUsuario {

	private static final long serialVersionUID = 1L;

	protected String apellido;
	protected String dni;
	protected String mail;
	protected String nombre;
	protected String nick;
	protected String password;
	protected String telefono;
}
