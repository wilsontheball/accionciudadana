package ar.com.thinksoft.ac.andrac.contexto;

import android.app.Activity;
import android.app.Application;

/**
 * Mantiene el contexto de la Aplicacion. Por ahora solo sirve para compartir el
 * Repositorio entre las distintas pantallas.
 * 
 * @since 10-08-2011
 * @author Paul
 */
public class Aplicacion extends Application {
	// Objeto que se comunica con el servidor
	private Repositorio repositorio = null;

	// Almacena el resultado de Login
	private int resultadoLogin = Activity.RESULT_FIRST_USER;

	public Repositorio getRepositorio() {
		if (this.repositorio == null) {
			this.setRepositorio(new Repositorio());
		}
		return this.repositorio;
	}

	public void setRepositorio(Repositorio repo) {
		this.repositorio = repo;
	}

	public int getResultadoLogin() {
		return this.resultadoLogin;
	}

	public void setResultadoLogin(int resultadoLogin) {
		this.resultadoLogin = resultadoLogin;
	}
}
