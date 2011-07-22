package ar.com.thinksoft.ac.andrac;

import android.app.Application;
import ar.com.thinksoft.ac.intac.IUsuario;

/**
 * Clase que mantiene el contexto de la Aplicacion.
 * 
 * @since 22-07-2011
 * @author Paul
 */
public class Aplicacion extends Application {
	private Repositorio repositorio = null;
	private IUsuario usuarioActual = null;

	public Repositorio getRepositorio() {
		return this.repositorio;
	}

	public void setRepositorio(Repositorio repo) {
		this.repositorio = repo;
	}

	public IUsuario getUsuarioActual() {
		return this.usuarioActual;
	}

	public void setUsuarioActual(IUsuario usuario) {
		this.usuarioActual = usuario;
	}

}
