package ar.com.thinksoft.ac.wilsond;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un repositorio de reclamos. Implementa Singleton. Internamente se
 * comunica con la DB.
 * 
 * @since 24-09-2011
 * @author Paul
 */
public class Repositorio {

	// Variable de clase que almacena la unica instancia (Singleton).
	private static Repositorio instancia;

	/**
	 * Constructor privado redefiniendo el que viene por defecto (Singleton).
	 */
	private Repositorio() {
		// objectContainer = Db4oClientServer.openClient("localhost", 5555,
		// "wilson", "wilson");
		// LogFwk.getInstance(this.getClass()).info("Inicio de BD");
	}

	/**
	 * Devuelve la unica instancia de Repositorio (Singleton).
	 * 
	 * @since 24-09-2011
	 * @author Paul
	 * @return Instancia de Repositorio.
	 */
	public static Repositorio getInstancia() {

		if (instancia == null) {
			instancia = new Repositorio();
		}
		return instancia;
	}

	/**
	 * Obtiene lista de reclamos realizados por un usuario.
	 * 
	 * @since 24-09-2011
	 * @author Paul
	 * @param nombreUsuario
	 * @return Lista de reclamos.
	 */
	public List<Reclamo> getReclamos(String nombreUsuario) {

		ArrayList<Reclamo> lista = new ArrayList<Reclamo>();

		// XXX Solo para probar.......
		for (int i = 0; i < 100; i++) {
			Reclamo rec = new Reclamo();
			rec.setAlturaIncidente("45");
			rec.setBarrioIncidente("Belgrano");
			rec.setCalleIncidente("Cabildo");
			rec.setCiudadanoGeneradorReclamo("pepe");
			rec.setFechaReclamo("hoy " + i);
			rec.setTipoIncidente("CACA DE PERRO");
			lista.add(rec);
		}
		// XXX fin

		return lista;
	}
}
