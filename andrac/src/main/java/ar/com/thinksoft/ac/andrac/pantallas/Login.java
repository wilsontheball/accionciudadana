package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

/**
 * La clase se encarga de manejar la pantalla de Autentificacion. Desde esta
 * pantalla se puede acceder a la pantalla de Registracion o cerrar la
 * aplicacion.
 * 
 * @since 07-09-2011
 * @author Paul
 * 
 */
public class Login extends Activity {
	private static final int CAMPOS_VACIOS = 0;
	private static final int LOGIN_FAIL = 1;
	private static final int SERVER_ERROR = 2;
	private static final int LOGIN = 3;

	// Almacena titulo de la ventana de alerta
	private String tituloAlerta = "";
	// Almacena texto de la ventana de alerta
	private String mensageAlerta = "";

	private ProgressDialog procesando = null;

	/**
	 * Se encarga de la creacion de la ventana. Le asigna Layout.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.login);
		// this.getWindow().getDecorView().setVisibility(View.INVISIBLE);
		// Esta pantalla no tiene layout por que es invisible ;-)

	}

	/**
	 * Previene apertura doble de la ventana Login.
	 * 
	 * @since 06-09-2011
	 * @author Paul
	 */
	@Override
	protected void onStart() {
		super.onStart();
		if (this.getResultadoLogin() == Activity.RESULT_OK) {
			this.setResult(Activity.RESULT_OK);
			this.finish();
		}

		// Login no se muestra cuando ya esta autenticado.
		// if ((this.getRepo().getNick() == null)
		// || (this.getRepo().getPass() == null)) {
		// this.mostrarDialogo(LOGIN);
		// } else {
		//
		// this.ejecutarFuncion(FuncionRest.GETPERFIL);
		//
		// }

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
	 * Muestra una ventana de dialogo con un boton para cerrarla.
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 */
	private void mostrarDialogo(int codigo) {
		// No se puede pasar los atributos directamente al Dialogo
		switch (codigo) {
		case CAMPOS_VACIOS:
			this.tituloAlerta = getString(R.string.atencion);
			this.mensageAlerta = getString(R.string.campo_vacio);
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
		case LOGIN:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.server_inaccesible);
			this.showDialog(codigo);
			break;
		default:
			break;
		}
	}

	/**
	 * Crea la ventana de Alerta. (Se hace de esta forma en Android 2.2)
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
	 * Valida el Usuario y Contrase�a ingresados contra la base de datos. En
	 * caso de exito devuelve resultado positivo a la Activity padre.
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 * @param v
	 *            una View
	 */
	public void validar(View v) {
		this.getWindow().getDecorView().setVisibility(View.INVISIBLE);
		this.mostrarProcesando(FuncionRest.GETPERFIL);
		// try {
		// String nick = this.getUsuario();
		// String pass = this.getPassword();
		// // Valida que los campos no esten vacios.
		// if (nick.length() == 0 || pass.length() == 0) {
		// mostrarAdvertencia(CAMPOS_VACIOS);
		// return;
		// }
		// if (this.getRepo().validarUsuario(nick, pass)) {
		// this.getRepo().setNick(nick);
		// this.setResult(Activity.RESULT_OK);
		// this.finish();
		// } else {
		// this.mostrarAdvertencia(LOGIN_FAIL);
		// this.limpiarDatosIngresados();
		// }
		// } catch (Exception e) {
		// this.mostrarAdvertencia(SERVER_ERROR);
		// this.limpiarDatosIngresados();
		// }
	}

	/**
	 * Muestra la ventana de Registro. Metodo llamado por el boton Registro
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 * @param v
	 *            una View
	 */
	public void registro(View v) {
		Intent intent = new Intent(v.getContext(), Registro.class);
		startActivity(intent);
	}

	/**
	 * Cierra la ventana. Devuelve resultado negativo al padre.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 * @param v
	 *            una View
	 */
	public void salir(View v) {
		this.setResult(Activity.RESULT_CANCELED);
		this.finish();
	}

	/**
	 * Devuelve la aplicacion principal
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @return aplicacion
	 */
	public Repositorio getRepo() {
		return ((Aplicacion) this.getApplication()).getRepositorio();
	}

	/**
	 * Obtiene el NickName ingresado en la pantalla
	 * 
	 * @sice 22-07-2011
	 * @author Paul
	 * @return nick de usuario
	 */
	private String getUsuario() {
		return ((EditText) findViewById(R.id.nick)).getText().toString();
	}

	/**
	 * Obtiene el Password ingresado en la pantalla
	 * 
	 * @sice 22-07-2011
	 * @author Paul
	 * @return password de usuario
	 */
	private String getPassword() {
		return ((EditText) findViewById(R.id.pass)).getText().toString();
	}

	/**
	 * Limpia el contenido de los campos NickName y Password
	 * 
	 * @sice 22-07-2011
	 * @author Paul
	 */
	private void limpiarDatosIngresados() {
		((EditText) findViewById(R.id.nick)).setText("");
		((EditText) findViewById(R.id.pass)).setText("");
	}

	private int getResultadoLogin() {
		return ((Aplicacion) this.getApplication()).getResultadoLogin();
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
			// this.cancelarServicioRest();
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
}
