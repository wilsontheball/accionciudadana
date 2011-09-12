package ar.com.thinksoft.ac.andrac;

import android.app.Application;

/**
 * Mantiene el contexto de la Aplicacion. Por ahora solo sirve para compartir el
 * Repositorio entre las distintas pantallas.
 * 
 * @since 10-08-2011
 * @author Paul
 */
public class Aplicacion extends Application {
	private Repositorio repositorio = null;

	public Repositorio getRepositorio() {
		if (this.repositorio == null) {
			this.setRepositorio(new Repositorio());
		}
		return this.repositorio;
	}

	public void setRepositorio(Repositorio repo) {
		this.repositorio = repo;
	}
}
