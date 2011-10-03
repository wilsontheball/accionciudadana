package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

/**
 * Pantalla transparente que maneja los servicios y pide autentificacion.
 * 
 * @since 02-10-2011
 * @author Paul
 * 
 */
public class Login extends Activity {
	private static final int LOGIN = 0;
	private static final int LOGIN_FAIL = 1;
	private static final int SERVER_ERROR = 2;
	private static final int CAMPOS_VACIOS = 3;

	// Almacena titulo de la ventana de alerta
	private String tituloAlerta = "";
	// Almacena texto de la ventana de alerta
	private String mensageAlerta = "";
	// Referencia al dialogo procesando
	private ProgressDialog procesando = null;

	/**
	 * Se encarga de la creacion de la ventana.
	 * 
	 * @since 02-10-2011
	 * @author Paul
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Esta pantalla no tiene layout por que es invisible ;-)
	}

	/**
	 * Revisa si tiene que pedir autenticacion.
	 * 
	 * @since 02-10-2011
	 * @author Paul
	 */
	@Override
	protected void onStart() {
		super.onStart();
		// Login no se muestra cuando ya esta autenticado.
		if ((this.getRepo().getNick() == null)
				|| (this.getRepo().getPass() == null)) {
			this.mostrarDialogo(LOGIN);
		} else {
			// TODO Debe obtener la funcion desde extras
			this.ejecutarFuncion(FuncionRest.GETPERFIL);
		}
	}

	/**
	 * Atiende los cambios de configuracion, como rotacion de pantalla, etc...
	 * 
	 * @since 07-09-2011
	 * @author Paul
	 * @param newConfig
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	/**
	 * Crea la ventana de dialogo. (Se hace de esta forma en Android 2.2)
	 * 
	 * @since 10-08-2011
	 * @author Paul
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		return new AlertDialog.Builder(Login.this)
				.setIcon(R.drawable.alert_dialog_icon)
				.setTitle(tituloAlerta)
				.setMessage(mensageAlerta)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// TODO lamar al servcio
								dialog.cancel();
								ejecutarFuncion(FuncionRest.GETPERFIL);
							}
						})
				.setNegativeButton(R.string.cancelar,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								dialog.cancel();
								setResult(RESULT_CANCELED);
								finish();
							}
						}).create();
	}

	/**
	 * Muestra una ventana de dialogo segun la necesidad.
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 */
	private void mostrarDialogo(int codigo) {
		// Asi, por que no se puede pasar los atributos directamente al Dialogo.
		switch (codigo) {
		case LOGIN:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.server_inaccesible);
			this.showDialog(codigo);
			break;
		case LOGIN_FAIL:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.nick_pass_fail);
			this.showDialog(codigo);
			break;
		case SERVER_ERROR:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.server_inaccesible);
			this.showDialog(codigo);
			break;
		case CAMPOS_VACIOS:
			this.tituloAlerta = getString(R.string.atencion);
			this.mensageAlerta = getString(R.string.campo_vacio);
			this.showDialog(codigo);
			break;
		default:
			break;
		}
	}

	/**
	 * Muestra una ventana dialogo "Procesando". Al hacer clic en el boton
	 * finaliza servicio que corre en este momento.
	 * 
	 * @since 29-09-2011
	 * @author Paul
	 * @param mensaje
	 *            Texto que se va a mostrar en el dialogo.
	 */
	private void mostrarProcesando(String funcion) {
		this.procesando = new ProgressDialog(Login.this);

		// TODO Definir mensajes para todas las funciones
		String mensaje = "no tiene mensaje!!";
		if (FuncionRest.GETRECLAMOS.equals(funcion)) {
			mensaje = getString(R.string.obteniendo_reclamos);
		} else if (FuncionRest.GETPERFIL.equals(funcion)) {
			mensaje = getString(R.string.obteniendo_perfil);
		} else {
			Log.d(this.getClass().getName(), "Funcion sin mensaje: " + funcion);
			// TODO this.cancelarServicioRest();
			return;
		}
		this.procesando.setMessage(mensaje);
		this.procesando.setButton(getString(R.string.cancelar),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// TODO Debe parar servicio que corre
						// cancelarServicioRest();
						dialog.cancel();
						setResult(RESULT_CANCELED);
						finish();
					}
				});
		this.procesando.setCancelable(false);
		this.procesando.show();
	}

	private void ejecutarFuncion(String funcion) {
		// TODO aca se debe conectar al servidor
		this.mostrarProcesando(FuncionRest.GETPERFIL);
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
}
