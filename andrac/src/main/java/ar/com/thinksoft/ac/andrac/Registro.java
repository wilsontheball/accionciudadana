package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * La clase se encarga de manejar la pantalla de Registro.
 * 
 * @since 12-08-2011
 * @author Paul
 */
public class Registro extends Activity {

	private final int ERROR_DIALOG = 1;
	private final int CONFIRM_DIALOG = 2;

	// Almacena titulo de la ventana de alerta
	private String tituloAlerta = "";
	// Almacena texto de la ventana de alerta
	private String mensageAlerta = "";

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
	 * Registra al usuario y muestra confirmacion. Responde al boton Aceptar.
	 * 
	 * @since 12-08-2011
	 * @author Paul
	 * @param v
	 */
	public void registrar(View v) {
		try {
			if (this.camposIncompletos()) {
				this.mostrarDialogo(getString(R.string.atencion),
						getString(R.string.campo_vacio), this.ERROR_DIALOG);
			} else {
				if (!this.getMail().equals(this.getMailConfirm())) {
					this.mostrarDialogo(getString(R.string.atencion),
							getString(R.string.mail_no_coincide),
							this.ERROR_DIALOG);
					this.limpiarMail();
				} else {
					if (!this.getPass().equals(this.getPassConfirm())) {
						this.mostrarDialogo(getString(R.string.atencion),
								getString(R.string.pass_no_coincide),
								this.ERROR_DIALOG);
						this.limpiarPass();
					} else {
						((Aplicacion) this.getApplication()).getRepositorio()
								.registrarUsuario(getNombre(), getApellido(),
										getUsuario(), getDNI(), getMail(),
										getTelefono(), getPass());
						this.mostrarDialogo(getString(R.string.atencion),
								getString(R.string.confirmar_registro),
								this.CONFIRM_DIALOG);
					}
				}
			}
		} catch (Exception e) {
			this.mostrarDialogo(getString(R.string.advertencia), e.toString(),
					this.ERROR_DIALOG);
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

	/**
	 * Muestra una ventana de dialogo con un boton para cerrarla.
	 * 
	 * @since 12-08-2011
	 * @author Paul
	 */
	private void mostrarDialogo(String titulo, String mensaje, int tipo) {
		// No se puede pasar los atributos directamente al Dialogo
		this.tituloAlerta = titulo;
		this.mensageAlerta = mensaje;
		this.showDialog(tipo);
	}

	/**
	 * Crea la ventana de Alerta. (Se hace de esta forma en Android 2.2)
	 * 
	 * @since 12-08-2011
	 * @author Paul
	 */
	@Override
	protected Dialog onCreateDialog(int tipo) {
		Dialog unDialog = null;
		switch (tipo) {
		case CONFIRM_DIALOG:
			unDialog = new AlertDialog.Builder(Registro.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(tituloAlerta)
					.setMessage(mensageAlerta)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									finish();
								}
							}).create();
			break;
		case ERROR_DIALOG:
			unDialog = new AlertDialog.Builder(Registro.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(tituloAlerta)
					.setMessage(mensageAlerta)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									/* Solo cierra el dialogo */
								}
							}).create();
			break;
		default:
			break;
		}
		return unDialog;
	}

	/**
	 * Bussca si hay campos sin completar en la pantalla.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 * @return <Code>true</Code> si falta completar alguno, <Code>false</Code>
	 *         si todos estan completos.
	 */
	private boolean camposIncompletos() {
		return getNombre().length() == 0 || getApellido().length() == 0
				|| getUsuario().length() == 0 || getDNI().length() == 0
				|| getMail().length() == 0 || getMailConfirm().length() == 0
				|| getTelefono().length() == 0 || getPass().length() == 0
				|| getPassConfirm().length() == 0;
	}

	/**
	 * Limpia el contenido de los campos Mail y RepetirMail
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	private void limpiarMail() {
		((EditText) findViewById(R.id.mail)).setText("");
		((EditText) findViewById(R.id.mail_confirm)).setText("");
	}

	/**
	 * Limpia el contenido de los campos Password y RepetirPassword
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	private void limpiarPass() {
		((EditText) findViewById(R.id.pass)).setText("");
		((EditText) findViewById(R.id.pass_confirm)).setText("");
	}

	/* Metodos que obtienen el contenido de los campos de la pantalla */

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
}
