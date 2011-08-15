package ar.com.thinksoft.ac.andrac;

import android.graphics.Bitmap;
import ar.com.thinksoft.ac.intac.IUsuario;

/**
 * Abstrae la conexion remota a la base de datos
 * 
 * @since 22-07-2011
 * @author Paul
 */
public class Repositorio {

	Bitmap imagen = null;
	private IUsuario usuarioActual = null;
	private String nombreUsuario = null;

	/**
	 * Devuelve un objeto Usuario desde la base de datos
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @param nick
	 *            nombre del usuario
	 * @param pass
	 *            contrase�a del usuario
	 * @return Usuario o null si la operacion fallo
	 */
	public IUsuario getUsuario(String nick, String pass) {
		// TODO no esta implementada la conexion a la BD
		return null;
	}

	/**
	 * Valida un nick y password
	 * 
	 * @param nick
	 * @param pass
	 * @return
	 */
	public boolean validarUsuario(String nick, String pass) {
		// TODO no esta implementada la conexion a la BD
		if (nick.length() == 0 || pass.length() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Registra a un nuevo usuario en el sistema.
	 * 
	 * @since 25-07-2011
	 * @author Paul
	 * 
	 * @param nombre
	 * @param apellido
	 * @param nick
	 * @param dni
	 * @param mail
	 * @param telefono
	 * @param password
	 * @return <Code>true</Code> si el usuario se registro correctamente o
	 *         <Code>false</Code> si el usuario no se pudo registrar.
	 */
	public boolean registrarUsuario(String nombre, String apellido,
			String nick, String dni, String mail, String telefono,
			String password) {
		// TODO falta implementar
		return true;
	}

	/**
	 * Obtiene los reclamos realizados del usuario.
	 * 
	 * @since 06-08-2011
	 * @author Paul
	 * @param nick
	 *            NickName del usuario.
	 * @return Array de reclamos.
	 */
	public ReclamoItem[] getReclamosUsuario() {
		// TODO Falta implementar la conexion al servidor.
		// TODO Probando... Es un generador bobo de reclamos.

		return new ReclamoItem[] {
				new ReclamoItem("Tipo de Incidente 1",
						"Direccion de de Incidente 1"),
				new ReclamoItem("Tipo de Incidente  2",
						"Direccion de de Incidente 2"),
				new ReclamoItem("Tipo de Incidente  3",
						"Direccion de de Incidente 3"),
				new ReclamoItem("Tipo de Incidente  4",
						"Direccion de de Incidente 4"),
				new ReclamoItem("Tipo de Incidente  5",
						"Direccion de de Incidente 5"),
				new ReclamoItem("Tipo de Incidente  6",
						"Direccion de de Incidente 6"),
				new ReclamoItem("Tipo de Incidente  7",
						"Direccion de de Incidente 7"),
				new ReclamoItem("Tipo de Incidente  8",
						"Direccion de de Incidente 8"),
				new ReclamoItem("Tipo de Incidente  9",
						"Direccion de de Incidente 9"),
				new ReclamoItem("Tipo de Incidente  10",
						"Direccion de de Incidente 10"),
				new ReclamoItem("Tipo de Incidente  11",
						"Direccion de de Incidente 11") };
	}

	public boolean publicarReclamoDireccion(String nick, String tipo,
			String calle, String altura) {
		// TODO falta implementar
		return true;
	}

	public boolean publicarReclamoGPS(String nick, String tipo,
			String coordenada) {
		// TODO falta implementar
		return true;
	}

	/**
	 * Almacen para la foto. De esta forma no hace falta un archivo.
	 * 
	 * @param imagen
	 */
	public void setImagen(Bitmap imagen) {
		this.imagen = imagen;
	}

	public Bitmap getImagen() {
		return this.imagen;
	}

	public IUsuario getUsuarioActual() {
		return this.usuarioActual;
	}

	public void setUsuarioActual(IUsuario usuario) {
		this.usuarioActual = usuario;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String usuario) {
		this.nombreUsuario = usuario;
	}

}
