package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;

/**
 * La clase se encarga de manejar la pantalla de Registro.
 * 
 * @since 07-09-2011
 * @author Paul
 */
public class Registro extends Activity {

	private final int REGISTRO_EXITOSO = 1;
	private final int CAMPOS_VACIOS = 2;
	private final int PASS_NO_COINCIDE = 3;
	private final int MAIL_NO_COINCIDE = 4;
	private final int REGISTRO_FALLO = 5;

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
	 * Registra al usuario y muestra confirmacion. Responde al boton Aceptar.
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 * @param v
	 */
	public void registrar(View v) {
		try {
			if (this.camposIncompletos()) {
				this.mostrarDialogo(CAMPOS_VACIOS);
			} else {
				if (!this.getMail().equals(this.getMailConfirm())) {
					this.mostrarDialogo(MAIL_NO_COINCIDE);
					this.limpiarMail();
				} else {
					if (!this.getPass().equals(this.getPassConfirm())) {
						this.mostrarDialogo(PASS_NO_COINCIDE);
						this.limpiarPass();
					} else {
						((Aplicacion) this.getApplication()).getRepositorio()
								.registrarUsuario(getNombre(), getApellido(),
										getUsuario(), getDNI(), getMail(),
										getTelefono(), getPass());
						this.mostrarDialogo(REGISTRO_EXITOSO);
					}
				}
			}
		} catch (Exception e) {
			this.mostrarDialogo(REGISTRO_FALLO);
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
	 * @since 23-08-2011
	 * @author Paul
	 */
	private void mostrarDialogo(int tipo) {
		// No se puede pasar los atributos directamente al Dialogo
		// TODO falta definir si hay mas errores posibles.
		switch (tipo) {
		case REGISTRO_EXITOSO:
			this.tituloAlerta = getString(R.string.atencion);
			this.mensageAlerta = getString(R.string.confirmar_registro);
			break;
		case CAMPOS_VACIOS:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.campo_vacio);
			break;
		case PASS_NO_COINCIDE:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.pass_no_coincide);
			break;
		case MAIL_NO_COINCIDE:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.mail_no_coincide);
			break;
		case REGISTRO_FALLO:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.registro_fallido);
			break;

		default:
			return;
		}
		this.showDialog(tipo);
	}

	/**
	 * Crea la ventana de Alerta. (Se hace de esta forma en Android 2.2)
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 */
	@Override
	protected Dialog onCreateDialog(int tipo) {
		Dialog unDialog = null;
		switch (tipo) {
		case REGISTRO_EXITOSO:
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
		default:
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
