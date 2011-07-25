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

	/**
	 * valida un usuario y contraseña
	 * 
	 * @param nick
	 * @param pass
	 * @return
	 */
	public boolean validarUsuario(String nick, String pass) {
		// TODO no esta implementada la conexion a la BD
		if (nick.isEmpty() || pass.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean publicarReclamoDireccion(String nick, String tipo, String calle, String altura,
			String observacion) {
		// TODO falta implementar
		return true;
	}
	
	public boolean publicarReclamoGPS(String nick, String tipo, String coordenada,
			String observacion) {
		// TODO falta implementar
		return true;
	}

}
