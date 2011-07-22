package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ar.com.thinksoft.ac.intac.IUsuario;

/**
 * La clase se encarga de manejar la pantalla de Autentificacion
 * 
 * @since 19-07-2011
 * @author Paul
 * 
 */
public class Login extends Activity {

	// TODO Definir que variables van!!!
	// private static String TAG = "andrac";
	// private static Activity hilo = null;
	// private int resultado = Activity.RESULT_OK;

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
	 * Muestra una ventana de dialogo con un boton que la cierra
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @param titulo
	 *            titulo que se va a mostrar en la ventana
	 * @param mensaje
	 *            mensaje que se va a mostrar
	 */
	private void mostrarAdvertencia(String titulo, String mensaje) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(titulo)
				.setMessage(mensaje)
				.setCancelable(false)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}

	/**
	 * Se ejecuta cuando se cierra la ventana. Devuelve resultado de su
	 * ejecucion al proceso padre.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	@Override
	public void onDestroy() {
		// TODO Hacer que devuelva un valor a la ventana padre!!!
		// setResult(this.resultado);
		super.onDestroy();

	}

	/**
	 * Valida el Usuario y Contraseña ingresados contra la base de datos. Metodo
	 * llamado por el boton Aceptar.
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @param v
	 *            una View
	 */
	public void validar(View v) {
		try {
			String nick = this.getUsuario();
			String pass = this.getPassword();
			IUsuario usuario = this.getAplicacion().getRepositorio()
					.getUsuario(nick, pass);
			this.getAplicacion().setUsuarioActual(usuario);
		} catch (Exception e) {
			this.mostrarAdvertencia("Atención!", e.toString());
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
	 * Cierra la ventana. Asigna resultado negativo.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 * @param v
	 *            una View
	 */
	public void salir(View v) {
		// TODO Hacer que se devuelva una valor CANCELADO al salir!
		this.finish();
	}

	/**
	 * Devuelve la aplicacion principal
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @return aplicacion
	 */

	public Aplicacion getAplicacion() {
		return ((Aplicacion) this.getApplication());
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
