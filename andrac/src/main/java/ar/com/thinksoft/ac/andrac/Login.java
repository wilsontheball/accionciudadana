package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * La clase se encarga de manejar la pantalla de Autentificacion. Desde esta
 * pantalla se puede acceder a la pantalla de Registracion o cerrar la
 * aplicacion.
 * 
 * @since 19-07-2011
 * @author Paul
 * 
 */
public class Login extends Activity {

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
	 * Muestra una ventana de dialogo con un boton para cerrarla.
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @param titulo
	 *            titulo que se va a mostrar en la ventana
	 * @param mensaje
	 *            mensaje que se va a mostrar
	 */
	private void mostrarAdvertencia(String titulo, String mensaje) {
		// TODO corregir, mostrar advertencia
		// AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setTitle(titulo)
		// .setMessage(mensaje)
		// .setCancelable(false)
		// .setPositiveButton(R.string.ok,
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int id) {
		// dialog.cancel();
		// }
		// });
		// AlertDialog alert = builder.create();
		// alert.show();

		this.showDialog(1);

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		return new AlertDialog.Builder(Login.this)
				.setTitle("Aca iria un Titulo")
				.setMessage("Aca iria un Mensaje")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						/* User clicked OK so do some stuff */
					}
				})
				// .setNeutralButton("Neutro",
				// new DialogInterface.OnClickListener() {
				// public void onClick(DialogInterface dialog,
				// int whichButton) {
				//
				// /* User clicked Something so do some stuff */
				// }
				// })
				// .setNegativeButton("cancelar",
				// new DialogInterface.OnClickListener() {
				// public void onClick(DialogInterface dialog,
				// int whichButton) {
				//
				// /* User clicked Cancel so do some stuff */
				// }
				// })
				//
				.create();
	}

	/**
	 * Valida el Usuario y Contraseña ingresados contra la base de datos. En
	 * caso de exito devuelve resultado positivo a la Activity padre.
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

			if (this.getAplicacion().getRepositorio()
					.validarUsuario(nick, pass)) {
				this.getAplicacion().setNombreUsuario(nick);
				this.setResult(Activity.RESULT_OK);
				this.finish();
			} else {
				this.mostrarAdvertencia(getString(R.string.advertencia),
						getString(R.string.nick_pass_fail));
				this.limpiarDatosIngresados();
			}
		} catch (Exception e) {
			this.mostrarAdvertencia(getString(R.string.advertencia),
					e.toString());
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
