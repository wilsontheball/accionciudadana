package ar.com.thinksoft.ac.andrac.pantallas;

import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.adapter.ReclamoAdapter;
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

	// Codigo de error
	private final int ERROR = -1;

	// Mensaje de error
	private String mensajeError = "Error!";
	// Almacena reclamo para mostrar su detalle
	private Reclamo reclamoGuardado = null;

	// Almacena reclamos para pasarlos al listener
	private Reclamo[] reclamosGuardados;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reclamos_guardados);

		// Obtiene los reclamos guardados del usuario.
		this.reclamosGuardados = (Reclamo[]) this.getRepo()
				.getReclamosGuardados();

		// Carga el listado con los reclamos guardados
		ListView listado = (ListView) findViewById(R.id.reclamos_list);
		listado.setAdapter(new ReclamoAdapter(this, this.reclamosGuardados));
		listado.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int posicion, long id) {
				mostrarDialogo(posicion);
			}
		});
	}

	/**
	 * Muestra una ventana de dialogo con el detalle de reclamo.
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 */
	public void mostrarDialogo(int posicion) {
		Reclamo reclamo = reclamosGuardados[posicion];
		if (reclamo != null) {
			this.reclamoGuardado = reclamo;
			this.showDialog(posicion);
		} else {
			this.showDialog(1);
		}
	}

	/**
	 * Crea la ventana de Dialogo. (Se hace de esta forma en Android 2.2)
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Definir la vista fachera del dialogo

		switch (id) {
		case ERROR:
			return new AlertDialog.Builder(ListaReclamosGuardados.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(R.string.advertencia)
					.setMessage(this.mensajeError)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// Cierra la pantalla
									finish();
								}
							}).create();
		default:
			return new AlertDialog.Builder(ListaReclamosGuardados.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(this.reclamoGuardado.getEstadoDescripcion())
					.setMessage(this.armarResumen(this.reclamoGuardado))
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									/* User clicked OK so do some stuff */
								}
							}).create();
		}
	}

	private String armarResumen(Reclamo reclamo) {

		String resumen = reclamo.getTipoIncidente() + "\n"
				+ reclamo.getCalleIncidente() + " "
				+ reclamo.getAlturaIncidente() + "\n"
				+ reclamo.getFechaReclamo();
		return resumen;

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
