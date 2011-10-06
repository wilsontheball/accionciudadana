package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
 * @since 02-10-2011
 * @author Paul
 * 
 */
public class Login extends Activity implements ReceptorRest {
	private static final int LOGIN = 0;
	private static final int LOGIN_FAIL = 1;
	private static final int SERVER_ERROR = 2;
	private static final int CAMPOS_VACIOS = 3;

	// Almacena titulo de la ventana de alerta
	private String tituloDialogo = "";
	// Almacena texto de la ventana de alerta
	private String mensageDialogo = "";
	// Referencia al dialogo procesando
	private ProgressDialog procesando = null;

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
	 * @since 02-10-2011
	 * @author Paul
	 */
	@Override
	protected void onStart() {
		super.onStart();
		// TODO implementar Login.
		// Login no se muestra cuando ya esta autenticado.
		if ((this.getRepo().getNick() == null)
				|| (this.getRepo().getPass() == null)) {
			this.mostrarDialogo(LOGIN);
		} else {
			this.ejecutarFuncion(funcionAEjecutar);
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
		AlertDialog dialogo = null;
		switch (id) {
		case LOGIN:
			// Dialogo de Login.
			dialogo = new AlertDialog.Builder(Login.this)
					.setIcon(R.drawable.icono)
					.setTitle(tituloDialogo)
					.setView(findViewById(R.id.login_dialogo))
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// TODO Obtener los datos ingresados.
									setDatosLogin("pepe", "123");
									ejecutarFuncion(funcionAEjecutar);
									dialog.cancel();
								}
							})
					.setNegativeButton(R.string.cancelar,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// Cierra dialogo.
									dialog.cancel();
									// Vuelve a la ventana anterior.
									salirDePantalla(funcionAEjecutar,
											RESULT_CANCELED);
								}
							}).create();
			// dialogo.setContentView(findViewById(R.id.login_dialogo));
			break;
		default:
			// Dialogo de comun de Error.
			dialogo = new AlertDialog.Builder(Login.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(tituloDialogo)
					.setMessage(mensageDialogo)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// TODO lamar al servcio
									dialog.cancel();
									ejecutarFuncion(FuncionRest.GETPERFIL);
								}
							}).create();
			break;

		}
		return dialogo;
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
			this.mensageDialogo = getString(R.string.login_sub_titulo);
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
	 * @since 29-09-2011
	 * @author Paul
	 * @param mensaje
	 *            Texto que se va a mostrar en el dialogo.
	 */
	private void mostrarProcesando(String funcion) {
		Log.d(this.getClass().getName(), "Procesando: " + funcion);

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
						// Cierra el dialogo.
						dialog.cancel();
						// Vuelve a la ventana enterior.
						salirDePantalla("funcion cancelada", RESULT_CANCELED);
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
			Toast.makeText(this, "Fallo iniciar servicio", Toast.LENGTH_SHORT)
					.show();
			Log.e(this.getClass().getName(), "Fallo iniciar servicio");
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

	private void setDatosLogin(String usuario, String password) {
		this.getRepo().setNick(usuario);
		this.getRepo().setPass(password);
	}

	/**
	 * Cierra la ventana.
	 * 
	 * @param funcion
	 * @param codigoResultado
	 */
	private void salirDePantalla(String funcion, int codigoResultado) {
		// Termina una conexion. Cuando la hay, no hace nada.
		if (this.servicioRest != null) {
			this.stopService(this.servicioRest);
		}
		// Carga la devolucion.
		Intent resultado = new Intent();
		resultado.putExtra(ServicioRest.FUN, funcion);
		this.setResult(codigoResultado, resultado);
		this.finish();
	}
}
