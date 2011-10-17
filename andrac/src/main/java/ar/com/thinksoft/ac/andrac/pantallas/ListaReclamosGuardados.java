package ar.com.thinksoft.ac.andrac.pantallas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.adapter.ReclamoGuardadoAdapter;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.andrac.dominio.Reclamo;
import ar.com.thinksoft.ac.andrac.servicios.ServicioRest;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

import com.google.gson.Gson;

/**
 * La clase se encarga de manejar el listado de reclamos guardados por usuario.
 * 
 * @since 14-10-2011
 * @author Paul
 */
public class ListaReclamosGuardados extends Activity {

	// Codigo de error de envio de reclamo
	private final int ERROR_ENVIO = -1;

	// Almacena reclamo para mostrar su detalle
	private Reclamo reclamoGuardado = null;

	// Almacena posicion del reclamo en la lista
	private int posicionReclamo = 0;

	// Almacena reclamos para pasarlos al listener
	private Reclamo[] reclamosGuardados;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reclamos_guardados);
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Obtiene los reclamos guardados del usuario.
		try {
			this.obtenerReclamosGuardados();
		} catch (FileNotFoundException e) {
			// No existen reclamos guardados. Muestra nada.
		} catch (IOException e) {
			Log.e(this.getClass().getName(), "No se pudo leer reclamo");
		}
		// Carga el listado con los reclamos guardados
		ListView listado = (ListView) findViewById(R.id.reclamos_list);
		listado.setAdapter(new ReclamoGuardadoAdapter(this,
				this.reclamosGuardados));
		listado.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int posicion, long id) {
				mostrarDialogo(posicion);
			}
		});
	}

	/**
	 * Crea la ventana de Dialogo. (Se hace de esta forma en Android 2.2)
	 * 
	 * @since 14-10-2011
	 * @author Paul
	 */
	@Override
	protected Dialog onCreateDialog(int id) {

		if (id < 0) {
			// Muestra mensaje de error.
			return new AlertDialog.Builder(ListaReclamosGuardados.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(R.string.advertencia)
					.setMessage(R.string.reclamo_no_reenviado)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							}).create();
		} else {
			// Muestra detalle de reclamo.
			return new AlertDialog.Builder(ListaReclamosGuardados.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(R.string.no_enviado)
					.setMessage(this.armarResumen(this.getReclamoGuardado()))
					.setNegativeButton(R.string.cancelar,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									/* User clicked OK so do some stuff */
								}
							})
					.setNeutralButton(R.string.borrar,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									borrarReclamoLista(getPosicionReclamo());
								}
							})
					.setPositiveButton(R.string.enviar,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									setReclamoAEnviar(getPosicionReclamo());
									ejecutarFuncion(FuncionRest.POSTRECLAMO);
								}
							}).create();
		}
	}

	/**
	 * Captura la respuesta de la ventana Login.
	 * 
	 * @since 14-10-2011
	 * @author Paul
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			// Se envio exitosamente el reclamo.
			this.borrarReclamoLista(this.getPosicionReclamo());
			Log.d(this.getClass().getName(),
					"Se ejecuto: " + data.getStringExtra(ServicioRest.FUN));
			Toast.makeText(this, R.string.reclamo_enviado, Toast.LENGTH_LONG)
					.show();
		} else if (resultCode == Activity.RESULT_CANCELED) {
			// Fallo el envio de reclamo
			Log.e(this.getClass().getName(),
					"Fallo al ejecutar: "
							+ data.getStringExtra(ServicioRest.FUN));
			this.mostrarDialogo(ERROR_ENVIO);
		} else if (resultCode == Activity.RESULT_FIRST_USER) {
			// Usuario cancelo el envio de reclamo.
			Log.d(this.getClass().getName(), "Usuario cancelo ejecucion: "
					+ data.getStringExtra(ServicioRest.FUN));
		} else {
			Log.e(this.getClass().getName(),
					"Resultado de ejecucion no esperado");
		}
	}

	/**
	 * Muestra una ventana de dialogo con el detalle de reclamo.
	 * 
	 * @since 14-10-2011
	 * @author Paul
	 */
	private void mostrarDialogo(int indice) {
		if (indice >= 0) {
			Reclamo reclamo = reclamosGuardados[indice];
			if (reclamo != null) {
				this.setReclamoGuardado(reclamo);
				this.setPosicionReclamo(indice);
				this.showDialog(indice);
			}
		} else {
			this.showDialog(indice);
		}
	}

	/**
	 * Borra un reclamo de la lista segun la posicion dada. Refresca la
	 * pantalla.
	 * 
	 * @since 10-10-2011
	 * @author Paul
	 * @param posicion
	 * @return
	 */
	private void borrarReclamoLista(int posicion) {

		String[] nombresArchivos = this.fileList();
		String nombreArchivo = nombresArchivos[posicion];
		this.deleteFile(nombreArchivo);

		this.onStart();

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
				+ reclamo.getFechaReclamo() + "\n" + reclamo.getObservaciones();
		return resumen;

	}

	/**
	 * Obtiene los reclamos guardados de la memoria
	 * 
	 * @since 09-10-2011
	 * @author Hernan
	 * @throws FileNotFoundException
	 */
	private void obtenerReclamosGuardados() throws FileNotFoundException,
			IOException {
		String[] nombresArchivos = this.fileList();

		ArrayList<Reclamo> listaReclamos = new ArrayList<Reclamo>();
		FileInputStream stream;
		for (int i = 0; i < nombresArchivos.length; i++) {
			stream = openFileInput(nombresArchivos[i]);
			listaReclamos.add(reclamoFromStream(stream));
		}

		this.reclamosGuardados = new Reclamo[listaReclamos.size()];

		for (int i = 0; i < listaReclamos.size(); i++) {
			this.reclamosGuardados[i] = listaReclamos.get(i);
		}
	}

	/**
	 * Convierte stream a reclamo.
	 * 
	 * @param stream
	 * @return Un reclamo.
	 */
	private Reclamo reclamoFromStream(FileInputStream stream) {
		InputStreamReader isReader = new InputStreamReader(stream);
		Gson gson = new Gson();
		return gson.fromJson(isReader, Reclamo.class);
	}

	/**
	 * Muestra la ventana de Login esperando resultado de ejecucion.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	private void ejecutarFuncion(String funcion) {
		Intent proceso = new Intent(this, Login.class);
		proceso.putExtra(ServicioRest.FUN, funcion);
		this.startActivityForResult(proceso, 0);
	}

	/**
	 * Guarda el reclamo a enviar en el repositorio.
	 * 
	 * @since 14-10-2011
	 * @author Paul
	 * @param indice
	 *            Posicion del reclamo.
	 */
	private void setReclamoAEnviar(int indice) {
		this.getRepo().setReclamoAEnviar(this.reclamosGuardados[indice]);
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

	/**
	 * Devuelve el repositorio.
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @return aplicacion
	 */
	private Repositorio getRepo() {
		return ((Aplicacion) this.getApplication()).getRepositorio();
	}

	/* ******** Getters y Setters ******* */

	private int getPosicionReclamo() {
		return posicionReclamo;
	}

	private void setPosicionReclamo(int posicionReclamo) {
		this.posicionReclamo = posicionReclamo;
	}

	private Reclamo getReclamoGuardado() {
		return reclamoGuardado;
	}

	private void setReclamoGuardado(Reclamo reclamoGuardado) {
		this.reclamoGuardado = reclamoGuardado;
	}

}
