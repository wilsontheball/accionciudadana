package ar.com.thinksoft.ac.wilsond;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un repositorio de reclamos.
 * 
 * @since 24-09-2011
 * @author Paul
 */
public class Repositorio {

	public List<Reclamo> list() {

		ArrayList<Reclamo> lista = new ArrayList<Reclamo>();

		// XXX Solo para probar.......
		lista.add(new Reclamo());
		lista.add(new Reclamo());
		lista.add(new Reclamo());
		// XXX fin

		return lista;
	}

}
