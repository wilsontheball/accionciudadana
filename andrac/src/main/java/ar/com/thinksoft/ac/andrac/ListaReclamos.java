package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

/**
 * La clase se encarga de manejar el listado de reclamos realizados.
 * 
 * @since 15-08-2011
 * @author Paul
 */
public class ListaReclamos extends Activity {

	// Almacena reclamo para mostrar su detalle
	private ReclamoItem reclamo = null;

	// Almacena reclamos para pasarlos al listener
	private ReclamoItem[] reclamos;

	/**
	 * Se encarga de la creacion de la ventana. Le asigna Layout. Obtiene los
	 * reclamos realizados del Repositorio y los carga al listado.
	 * 
	 * @since 12-08-2011
	 * @author Paul
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_reclamos);

		// // Esto anda bien!!!
		// // Obtiene los reclamos.
		// String[] array = (String[]) this.getRepo().getReclamosUsuario();
		// // Carga la lista con reclamos.
		// ((ListView) findViewById(R.id.list))
		// .setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, array));

		ListView listado = (ListView) findViewById(R.id.list);
		this.reclamos = (ReclamoItem[]) this.getRepo().getReclamosUsuario();
		listado.setAdapter(new ReclamoAdapter(this, this.reclamos));
		listado.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ReclamoItem reclamo = reclamos[position];
				mostrarDetalleReclamo(reclamo);
				return true;
			}
		});
	}

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

	/**
	 * Muestra una ventana de dialogo con el detalle de reclamo.
	 * 
	 * @since 11-08-2011
	 * @author Paul
	 */
	public void mostrarDetalleReclamo(ReclamoItem reclamo) {
		this.reclamo = reclamo;
		this.showDialog(1);
	}

	/**
	 * Crea la ventana de Dialogo. (Se hace de esta forma en Android 2.2)
	 * 
	 * @since 11-08-2011
	 * @author Paul
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Definir la vista del dialogo
		if (this.reclamo != null) {
			return new AlertDialog.Builder(ListaReclamos.this)
					.setIcon(R.drawable.icon)
					.setTitle("Estado: " + this.reclamo.getEstado())
					.setMessage(this.reclamo.getResumen())
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									/* User clicked OK so do some stuff */
								}
							}).create();
		} else {
			return new AlertDialog.Builder(ListaReclamos.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(R.string.titulo_detalle_reclamo)
					.setMessage("Error")
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									/* User clicked OK so do some stuff */
								}
							}).create();
		}
	}

}
