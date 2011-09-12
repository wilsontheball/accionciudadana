package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * La clase se encarga de manejar la pantalla de Autentificacion. Desde esta
 * pantalla se puede acceder a la pantalla de Registracion o cerrar la
 * aplicacion.
 * 
 * @since 23-08-2011
 * @author Paul
 * 
 */
public class Login extends Activity {
	private static final int CAMPOS_VACIOS = 0;
	private static final int LOGIN_FAIL = 1;
	private static final int SERVER_ERROR = 2;

	// Almacena titulo de la ventana de alerta
	private String tituloAlerta = "";
	// Almacena texto de la ventana de alerta
	private String mensageAlerta = "";

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
		setContentView(R.layout.login);
	}

	/**
	 * Atiende los cambios de configuracion, como rotacion de pantalla, etc...
	 * 
	 * @since 12-08-2011
	 * @author Paul
	 * @param newConfig
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	/**
	 * Muestra una ventana de dialogo con un boton para cerrarla.
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 */
	private void mostrarAdvertencia(int codigo) {
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
								/* User clicked OK so do some stuff */
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
		try {
			String nick = this.getUsuario();
			String pass = this.getPassword();
			// Valida que los campos no esten vacios.
			if (nick.length() == 0 || pass.length() == 0) {
				mostrarAdvertencia(CAMPOS_VACIOS);
				return;
			}
			if (this.getRepo().validarUsuario(nick, pass)) {
				this.getRepo().setNombreUsuario(nick);
				this.setResult(Activity.RESULT_OK);
				this.finish();
			} else {
				this.mostrarAdvertencia(LOGIN_FAIL);
				this.limpiarDatosIngresados();
			}
		} catch (Exception e) {
			this.mostrarAdvertencia(SERVER_ERROR);
			this.limpiarDatosIngresados();
		}
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
}
