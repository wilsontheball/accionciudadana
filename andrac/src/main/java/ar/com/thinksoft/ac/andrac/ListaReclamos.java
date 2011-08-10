package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * La clase se encarga de manejar el listado de reclamos realizados.
 * 
 * @since 06-08-2011
 * @author Paul
 * 
 */
public class ListaReclamos extends ListActivity {

	/**
	 * Se encarga de la creacion de la ventana. Le asigna Layout. Obtiene los
	 * reclamos realizados del Repositorio y los carga al listado.
	 * 
	 * @since 06-08-2011
	 * @author Paul
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_reclamos);

		// // Obtiene los reclamos.
		// String[] reclamos = this.getAplicacion().getRepositorio()
		// .getReclamosUsuario(this.getAplicacion().getNombreUsuario());
		// // Carga la lista con reclamos.
		// ListView list = (ListView) findViewById(R.id.listado_reclamos);
		// list.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, reclamos));

		String[] array = { "Reclamo", "Reclamo", "Reclamo", "Reclamo",
				"Reclamo", "Reclamo", "Reclamo", "Reclamo", "Reclamo",
				"Reclamo", "Reclamo", "Reclamo", "Reclamo", "Reclamo",
				"Reclamo" };
		

		// this.getRepo().getReclamosUsuario();
		
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, array));
		
	}

	// @Override
	// protected void onResume() {
	// super.onResume();
	// String[] array = { "Hola", "Chau" };
	// this.setListAdapter(new ArrayAdapter<String>(this,
	// android.R.layout.simple_list_item_1, array));
	// }
	//
	/**
	 * Cierra la ventana. Llamado por el boton Cancelar.
	 * 
	 * @since 06-08-2011
	 * @author Paul
	 * @param v
	 */
	public void salir(View v) {
		this.finish();
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

}
