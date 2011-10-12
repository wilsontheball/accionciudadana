package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.andrac.servicios.ReceptorRest;
import ar.com.thinksoft.ac.andrac.servicios.ReceptorResultados;
import ar.com.thinksoft.ac.andrac.servicios.ServicioRest;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

/**
 * Pantalla transparente que maneja los servicios y pide autentificacion.
 * 
 * @since 12-10-2011
 * @author Paul
 * 
 */
public class Login extends Activity implements ReceptorRest {

	private static final int LOGIN = 0;
	private static final int LOGIN_FAIL = 1;
	private static final int SERVER_ERROR = 2;
	private static final int CAMPOS_VACIOS = 3;

	private static final String ANDRAC_NICK = "andrac_nick";
	private static final String ANDRAC_PASS = "andrac_pass";

	// Almacena titulo de la ventana de alerta
	private String tituloDialogo = "";
	// Almacena texto de la ventana de alerta
	private String mensageDialogo = "";
	// Referencia al dialogo procesando
	private ProgressDialog procesando = null;

	private EditText campoNick;
	private EditText campoPass;
	private CheckBox checkPreferencias;

	private Intent servicioRest;
	private ReceptorResultados receptor;
	private String funcionAEjecutar = "Funcion desconocida";

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
		funcionAEjecutar = getIntent().getExtras().getString(ServicioRest.FUN);
	}

	/**
	 * Revisa si tiene que pedir autenticacion.
	 * 
	 * @since 08-10-2011
	 * @author Paul
	 */
	@Override
	protected void onStart() {
		super.onStart();
		// TODO implementar Login.
		// Login no se muestra cuando ya esta autenticado o cuando la funcion es
		// POST Usuario.
		if ((FuncionRest.POSTUSUARIO.equals(this.funcionAEjecutar))
				|| ((this.getRepo().getNick() != null) && (this.getRepo()
						.getPass() != null))) {
			this.ejecutarFuncion(funcionAEjecutar);
		} else {
			this.mostrarDialogo(LOGIN);
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
	 * @since 12-10-2011
	 * @author Paul
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialogo = null;
		switch (id) {
		case LOGIN:
			// Dialogo de Login.
			LayoutInflater inflater = (LayoutInflater) Login.this
					.getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.login_dialogo,
					(ViewGroup) findViewById(R.id.login_dialogo));
			campoNick = (EditText) layout.findViewById(R.id.nick);
			campoPass = (EditText) layout.findViewById(R.id.pass);
			checkPreferencias = (CheckBox) layout
					.findViewById(R.id.guardar_pass);
			dialogo = new AlertDialog.Builder(Login.this)
					.setCancelable(false)
					.setIcon(R.drawable.lock)
					.setTitle(tituloDialogo)
					.setView(layout)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									setDatosLogin(getNick(), getPass());
									if (isGuardarPass()) {
										guardarPreferencias(getNick(),
												getPass());
									} else {
										guardarPreferencias(null, null);
									}
									ejecutarFuncion(funcionAEjecutar);
									dialog.cancel();
								}
							})
					.setNegativeButton(R.string.cancelar,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									setDatosLogin(null, null);
									// Cierra dialogo.
									dialog.cancel();
									// Vuelve a la ventana anterior.
									salirDePantalla(funcionAEjecutar,
											RESULT_FIRST_USER);
								}
							}).create();
			break;
		default:
			// Dialogo comun de Error.
			dialogo = new AlertDialog.Builder(Login.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(tituloDialogo)
					.setMessage(mensageDialogo)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									dialog.cancel();
									salirDePantalla(funcionAEjecutar,
											RESULT_CANCELED);
								}
							}).create();
			break;

		}
		return dialogo;
	}

	/**
	 * Actualiza la ventana de dialogo antes de mostrarla.
	 * 
	 * @since 12-10-2011
	 * @author Paul
	 */
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case LOGIN:
			mostrarNickPass();
			break;
		default:
			break;
		}
	}

	/**
	 * Atiende los resultados de los servicios REST.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 */
	public void onReceiveResult(int resultCode, Bundle funcionData) {
		String funcion = funcionData.getString(ServicioRest.FUN);
		Log.d(this.getClass().getName(), "Resultado: " + funcion + "["
				+ resultCode + "]");
		switch (resultCode) {
		case ServicioRest.RUN:
			// Servicio Arranco: Muestra dialogo procesando.
			mostrarProcesando(funcion);
			break;
		case ServicioRest.FIN:
			// Servicio Finalizo: Cierra dialogo procesando.
			this.cerrarProcesando();
			// Vuelve a la ventana anterior.
			this.salirDePantalla(funcion, RESULT_OK);
			break;
		case ServicioRest.ERROR:
			// Limpia nick y pass.
			this.setDatosLogin(null, null);

			// Servicio Fallo: Cierra dialogo procesando.
			this.cerrarProcesando();
			// Vuelve a la ventana anterior.
			this.salirDePantalla(funcion, RESULT_CANCELED);
			break;
		}
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
			this.tituloDialogo = getString(R.string.login_titulo);
			this.showDialog(codigo);
			break;
		case LOGIN_FAIL:
			this.tituloDialogo = getString(R.string.advertencia);
			this.mensageDialogo = getString(R.string.nick_pass_fail);
			this.showDialog(codigo);
			break;
		case SERVER_ERROR:
			this.tituloDialogo = getString(R.string.advertencia);
			this.mensageDialogo = getString(R.string.server_inaccesible);
			this.showDialog(codigo);
			break;
		case CAMPOS_VACIOS:
			this.tituloDialogo = getString(R.string.atencion);
			this.mensageDialogo = getString(R.string.campo_vacio);
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
	 * @since 07-10-2011
	 * @author Paul
	 * @param mensaje
	 *            Texto que se va a mostrar en el dialogo.
	 */
	private void mostrarProcesando(String funcion) {
		Log.d(this.getClass().getName(), "Procesando: " + funcion);

		this.procesando = new ProgressDialog(Login.this);

		// TODO Agregar mensaje al Bunldle!!!!!!!!!!!!!!!!!!
		String mensaje = "no tiene mensaje!!";
		if (FuncionRest.GETRECLAMOS.equals(funcion)) {
			mensaje = getString(R.string.obteniendo_reclamos);
		} else if (FuncionRest.GETPERFIL.equals(funcion)) {
			mensaje = getString(R.string.obteniendo_perfil);
		} else {
			Log.e(this.getClass().getName(), "Funcion sin mensaje: " + funcion);
			mensaje = getString(R.string.procesando);
		}
		this.procesando.setMessage(mensaje);
		this.procesando.setButton(getString(R.string.cancelar),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// Limpia nick y pass.
						setDatosLogin(null, null);
						// Cierra el dialogo.
						dialog.cancel();
						// Vuelve a la ventana enterior.
						salirDePantalla(funcionAEjecutar, RESULT_FIRST_USER);
					}
				});
		this.procesando.setCancelable(false);
		this.procesando.show();
	}

	/**
	 * Cierra el dialogo en el caso que exista.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 */
	private void cerrarProcesando() {
		if (this.procesando != null) {
			this.procesando.dismiss();
			this.procesando = null;
		}
	}

	private void ejecutarFuncion(String funcion) {
		try {
			// Crea un servicio.
			this.servicioRest = new Intent(Intent.ACTION_SYNC, null, this,
					ServicioRest.class);
			this.servicioRest.putExtra(ServicioRest.REC, this.getReceptor());
			this.servicioRest.putExtra(ServicioRest.FUN, funcion);
			this.startService(servicioRest);

		} catch (Exception e) {
			// TODO: mostrar error!!!!
			Toast.makeText(this, "Fallo iniciar servicio: " + funcionAEjecutar,
					Toast.LENGTH_SHORT).show();
			Log.e(this.getClass().getName(), "Fallo iniciar servicio: "
					+ funcionAEjecutar);
		}
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

	/**
	 * Devuelve receptor.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 * @return Objeto receptor.
	 */
	private ReceptorResultados getReceptor() {
		if (this.receptor == null) {
			this.receptor = new ReceptorResultados(new Handler());
			this.receptor.setReceiver(this);
		}
		return receptor;
	}

	/**
	 * Obtiene usuario y pass guardados en el registro del telefono.
	 * 
	 * @since 11-10-2011
	 * @author Paul
	 */
	private void obtenerPreferencias() {
		SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
		this.getRepo().setNick(preferencias.getString(ANDRAC_NICK, null));
		this.getRepo().setPass(preferencias.getString(ANDRAC_PASS, null));

		Log.d(this.getClass().getName(), "Obtener prefs: "
				+ this.getRepo().getNick() + " " + this.getRepo().getPass());
	}

	/**
	 * Guarda usuario y pass en el registro del telefo.
	 * 
	 * @since 11-10-2011
	 * @author Paul
	 */
	private void guardarPreferencias(String nick, String pass) {
		SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
		Editor editor = preferencias.edit();
		editor.putString(ANDRAC_NICK, nick);
		editor.putString(ANDRAC_PASS, pass);
		editor.commit();

		Log.d(this.getClass().getName(), "Guardar prefs: " + nick + " " + pass);
	}

	/**
	 * Muestra usuario y pass en en dialog de Login.
	 * 
	 * @since 11-10-2011
	 * @author Paul
	 */
	private void mostrarNickPass() {

		// Obtiene los valores del registro.
		this.obtenerPreferencias();

		// Muestra los valores.
		if (this.getRepo().getNick() != null
				&& this.getRepo().getPass() != null) {
			this.setNick(this.getRepo().getNick());
			this.setPass(this.getRepo().getPass());
			this.setGuardarPass(true);
		} else {
			this.setGuardarPass(false);
		}
	}

	/**
	 * Almacena los datos de Login para no tener que pedirlos en cada operacion.
	 * 
	 * @param usuario
	 * @param password
	 */
	private void setDatosLogin(String usuario, String password) {
		this.getRepo().setNick(usuario);
		this.getRepo().setPass(password);
	}

	/**
	 * Para servicio al cerrar la ventana.
	 * 
	 * @since 10-10-2011
	 * @author Paul
	 */
	private void pararServicio() {
		// Termina una conexion.
		if (this.servicioRest != null) {
			boolean resultado = this.stopService(this.servicioRest);
			Log.d(this.getClass().getName(),
					"Stop Service (por las buenas), con resultado (boolean): "
							+ resultado);
		} else {
			Intent svc = new Intent(this, ServicioRest.class);
			boolean resultado = stopService(svc);
			Log.d(this.getClass().getName(),
					"Stop Service (por la malas), con resultado (boolean): "
							+ resultado);
		}
	}

	/**
	 * Cierra la ventana.
	 * 
	 * @param funcion
	 * @param codigoResultado
	 */
	private void salirDePantalla(String funcion, int codigoResultado) {
		this.pararServicio();

		// Carga la devolucion.
		Intent resultado = new Intent();
		resultado.putExtra(ServicioRest.FUN, funcion);
		this.setResult(codigoResultado, resultado);
		this.finish();
	}

	/* *********** Getters y setters ************ */

	private boolean isGuardarPass() {
		return this.checkPreferencias.isChecked();
	}

	private void setGuardarPass(boolean valor) {
		this.checkPreferencias.setChecked(valor);
	}

	private void setNick(String text) {
		this.campoNick.setText(text);
	}

	private String getNick() {
		return this.campoNick.getText().toString();
	}

	private void setPass(String text) {
		this.campoPass.setText(text);
	}

	private String getPass() {
		return this.campoPass.getText().toString();
	}
}
