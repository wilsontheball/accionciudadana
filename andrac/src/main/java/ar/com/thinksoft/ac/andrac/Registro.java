package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * La clase se encarga de manejar la pantalla de Registro.
 * 
 * @since 19-07-2011
 * @author Paul
 * 
 */
public class Registro extends Activity {
	/**
	 * Se encarga de la creacion de la ventana.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registro);
	}

	/**
	 * Registra al usuario y cierra la ventana. Responde al boton Aceptar.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 * @param v
	 */
	public void registrar(View v) {
		try {
			if (this.camposIncompletos()) {
				this.mostrarAdvertencia(getString(R.string.atencion),
						getString(R.string.campo_vacio));
			} else {
				if (!this.getMail().equals(this.getMailConfirm())) {
					this.mostrarAdvertencia(getString(R.string.atencion),
							getString(R.string.mail_no_coincide));
					this.limpiarMail();
				} else {
					if (!this.getPass().equals(this.getPassConfirm())) {
						this.mostrarAdvertencia(getString(R.string.atencion),
								getString(R.string.pass_no_coincide));
						this.limpiarPass();
					} else {
						((Aplicacion) this.getApplication()).getRepositorio()
								.registrarUsuario(getNombre(), getApellido(),
										getUsuario(), getDNI(), getMail(),
										getTelefono(), getPass());
						// TODO Hacer que la Activity espere el cierre del
						// dialogo
						this.mostrarAdvertencia(getString(R.string.atencion),
								getString(R.string.confirmar_registro));
						this.finish();
					}
				}
			}
		} catch (Exception e) {
			this.mostrarAdvertencia(getString(R.string.advertencia),
					e.toString());
		}
	}

	/**
	 * Cierra la ventana sin registrar. Responde al boton Salir.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 * @param v
	 */
	public void cancelar(View v) {
		this.finish();
	}

	private String getNombre() {
		return ((EditText) findViewById(R.id.nombre)).getText().toString();
	}

	private String getApellido() {
		return ((EditText) findViewById(R.id.apellido)).getText().toString();
	}

	private String getUsuario() {
		return ((EditText) findViewById(R.id.nick)).getText().toString();
	}

	private String getDNI() {
		return ((EditText) findViewById(R.id.dni)).getText().toString();
	}

	private String getMail() {
		return ((EditText) findViewById(R.id.mail)).getText().toString();
	}

	private String getMailConfirm() {
		return ((EditText) findViewById(R.id.mail_confirm)).getText()
				.toString();
	}

	private String getTelefono() {
		return ((EditText) findViewById(R.id.tel)).getText().toString();
	}

	private String getPass() {
		return ((EditText) findViewById(R.id.pass)).getText().toString();
	}

	private String getPassConfirm() {
		return ((EditText) findViewById(R.id.pass_confirm)).getText()
				.toString();
	}

	private void limpiarMail() {
		((EditText) findViewById(R.id.mail)).setText("");
		((EditText) findViewById(R.id.mail_confirm)).setText("");
	}

	private void limpiarPass() {
		((EditText) findViewById(R.id.pass)).setText("");
		((EditText) findViewById(R.id.pass_confirm)).setText("");
	}

	private boolean camposIncompletos() {
		return getNombre().length() == 0 || getApellido().length() == 0
				|| getUsuario().length() == 0 || getDNI().length() == 0
				|| getMail().length() == 0 || getMailConfirm().length() == 0
				|| getTelefono().length() == 0 || getPass().length() == 0
				|| getPassConfirm().length() == 0;
	}

	/**
	 * Muestra una ventana de dialogo con un boton para cerrarla.
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @param titulo
	 *            titulo que se va a mostrar en el dialogo.
	 * @param mensaje
	 *            mensaje que se va a mostrar en el dialogo.
	 */
	private void mostrarAdvertencia(String titulo, String mensaje) {
		// TODO Aca se deberia mostrar advertencia
		Toast.makeText(this, "Aca se deberia mostrar advertencia",
				Toast.LENGTH_LONG).show();
	}
}
