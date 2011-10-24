package ar.com.thinksoft.ac.andrac.pantallas;

import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.adapter.ReclamoAdapter;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.andrac.dominio.Reclamo;

/**
 * La clase se encarga de manejar el listado de reclamos realizados por usuario.
 * 
 * @since 10-10-2011
 * @author Paul
 */
public class ListaReclamos extends Activity {
	// Codigo de error
	private final int ERROR = -1;

	// Almacena reclamo para mostrar su detalle
	private Reclamo reclamo = null;

	// Almacena reclamos para pasarlos al listener
	private Reclamo[] reclamos;

	/**
	 * Se encarga de la creacion de la ventana. Le asigna Layout. Obtiene los
	 * reclamos realizados del Repositorio y los carga al listado.
	 * 
	 * @since 25-09-2011
	 * @author Paul
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_reclamos);

		// Obtiene los reclamos de usuario.
		this.reclamos = (Reclamo[]) this.getRepo().getReclamosUsuario();

		// Carga el listado con los reclamos.
		ListView listado = (ListView) findViewById(R.id.list);
		listado.setAdapter(new ReclamoAdapter(this, this.reclamos));
		listado.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int posicion, long id) {
				mostrarDialogo(posicion);
			}
		});
	}

	/**
	 * Atiende los cambios de configuracion, como rotacion de pantalla, etc...
	 * Refresca la imagen de background.
	 * 
	 * @since 07-09-2011
	 * @author Paul
	 * @param newConfig
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		this.getWindow().setBackgroundDrawableResource(R.drawable.wallpaper);
	}

	/**
	 * Crea la ventana de Dialogo. (Se hace de esta forma en Android 2.2)
	 * 
	 * @since 10-10-2011
	 * @author Paul
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Definir la vista fachera del dialogo

		switch (id) {
		case ERROR:
			return new AlertDialog.Builder(ListaReclamos.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(R.string.advertencia)
					.setMessage(R.string.error_inesperado)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// Cierra la pantalla
									finish();
								}
							}).create();
		default:
			return new AlertDialog.Builder(ListaReclamos.this)
					.setIcon(
							this.obtenerIcono(this.reclamo
									.getEstadoDescripcion()))
					.setTitle(this.reclamo.getEstadoDescripcion())
					.setMessage(this.armarResumen(this.reclamo))
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									/* User clicked OK so do some stuff */
								}
							}).create();
		}
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
	 * @since 10-10-2011
	 * @author Paul
	 */
	private void mostrarDialogo(int posicion) {
		Reclamo reclamo = reclamos[posicion];
		if (reclamo != null) {
			this.reclamo = reclamo;
			this.showDialog(posicion);
		} else {
			this.showDialog(ERROR);
		}
	}

	/**
	 * Obtene ID del icono por nombre de archivo.
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 * @param estadoOriginal
	 * @return
	 */
	private int obtenerIcono(String estadoOriginal) {
		try {
			String estado = this.limpiarCadena(estadoOriginal);
			Log.d(this.getClass().getName(), "Estado:" + estado);
			return this.getResources().getIdentifier(estado, "drawable",
					"ar.com.thinksoft.ac.andrac");
		} catch (Exception e) {
			Log.e(this.getClass().getName(), e.toString());
			return R.drawable.alert_dialog_icon;
		}
	}

	/**
	 * Convierte una cadena a formato de un nombre de archivo (solo minusculas
	 * sin espacios).
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 * @param s
	 * @return
	 */
	private String limpiarCadena(String s) {
		StringTokenizer st = new StringTokenizer(s.toLowerCase().trim(), " ",
				false);
		String t = "";
		while (st.hasMoreElements())
			t += st.nextElement();
		return t;
	}

	/**
	 * Arma el resumen de detalle de un reclamo.
	 * 
	 * @since 10-10-2011
	 * @author Paul
	 * @param reclamo
	 * @return
	 */
	private String armarResumen(Reclamo reclamo) {

		String resumen = reclamo.getTipoIncidente() + "\n"
				+ reclamo.getBarrioIncidente() + "\n"
				+ reclamo.getCalleIncidente() + " "
				+ reclamo.getAlturaIncidente() + "\n"
				+ reclamo.getFechaReclamo() + "\n" + reclamo.getObservaciones();
		return resumen;

	}
}