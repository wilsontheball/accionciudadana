package ar.com.thinksoft.ac.andrac;

import ar.com.thinksoft.ac.intac.IUsuario;

/**
 * Abstrae la conexion remota a la base de datos
 * 
 * @since 22-07-2011
 * @author Paul
 */
public class Repositorio {

	/**
	 * Devuelve un objeto Usuario desde la base de datos
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @param nick
	 *            nombre del usuario
	 * @param pass
	 *            contraseña del usuario
	 * @return Usuario o null si la operacion fallo
	 */
	public IUsuario getUsuario(String nick, String pass) {
		// TODO no esta implementada la conexion a la BD
		return null;
	}

}
