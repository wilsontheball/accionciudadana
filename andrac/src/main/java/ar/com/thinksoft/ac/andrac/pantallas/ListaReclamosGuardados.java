package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.andrac.dominio.Reclamo;

/**
 * La clase se encarga de manejar el listado de reclamos guardados por usuario.
 * 
 * @since 02-10-2011
 * @author Hernan
 */
public class ListaReclamosGuardados extends Activity {
	
	// Almacena reclamo para mostrar su detalle
	private Reclamo reclamoGuardado = null;

	// Almacena reclamos para pasarlos al listener
	private Reclamo[] reclamosGuardados;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reclamos_guardados);
		
		// Obtiene los reclamos guardados del usuario.
		this.reclamosGuardados = (Reclamo[]) this.getRepo().getReclamosGuardados();
	}
	
	/**
	 * Devuelve el Repositorio
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @return repositorio
	 */
	private Repositorio getRepo() {
		return ((Aplicacion) this.getApplication()).getRepositorio();
	}

	/**
	 * Cierra la ventana. Llamado por el boton Atras.
	 * 
	 * @since 01-10-2011
	 * @author Hernan
	 * @param v
	 */
	public void salir(View v) {
		this.finish();
	}
}
