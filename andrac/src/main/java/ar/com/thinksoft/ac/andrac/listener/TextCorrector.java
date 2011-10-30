package ar.com.thinksoft.ac.andrac.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import ar.com.thinksoft.ac.andrac.pantallas.Registro;

/**
 * Limita el texto a minusculas y numeros en el EditText.
 * 
 * @since 25-10-2011
 * @author Paul
 */
public class TextCorrector implements TextWatcher {

	private EditText campoACorregir;
	private String cadenaValida;
	private int posicionCursor;
	private boolean habilitado = true;

	public TextCorrector(EditText campoACorregir) {
		this.setCampoACorregir(campoACorregir);
		this.setCadenaValida("");
		this.setPosicionCursor(0);
	}

	public void afterTextChanged(Editable arg0) {
		// No hace nada.
	}

	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// No hace nada.
	}

	public void onTextChanged(CharSequence secuencia, int posicion, int arg2,
			int arg3) {

		if (isHabilitado()) {
			String cadenaNueva = secuencia.toString();

			if (cadenaNueva.matches("[a-z0-9]*")) {
				Log.d(Registro.class.getName(), "Es minuscula o digito: "
						+ cadenaNueva);

				// Guarda la cadena correcta.
				this.setCadenaValida(cadenaNueva);
			} else {
				Log.d(Registro.class.getName(), "NO es minuscula o digito: "
						+ cadenaNueva);

				// Deshabilita la correccion por el setText().
				this.setHabilitado(false);

				// Vuelve una posicion atras.
				this.setPosicionCursor(this.getCampoACorregir()
						.getSelectionStart() - 1);

				// Sobreescribe la cadena.
				this.getCampoACorregir().setText(this.getCadenaValida());

				// Ubica el cursor.
				this.getCampoACorregir().setSelection(this.getPosicionCursor());
			}
		} else {
			this.setHabilitado(true);
		}
	}

	/* ********** Getters y Setters ********* */

	private EditText getCampoACorregir() {
		return this.campoACorregir;
	}

	private void setCampoACorregir(EditText campoACorregir) {
		this.campoACorregir = campoACorregir;
	}

	private String getCadenaValida() {
		return this.cadenaValida;
	}

	private void setCadenaValida(String cadenaValida) {
		this.cadenaValida = cadenaValida;
	}

	private int getPosicionCursor() {
		if (this.posicionCursor < 0) {
			this.setPosicionCursor(0);
		}
		return this.posicionCursor;
	}

	private void setPosicionCursor(int posicionCursor) {
		this.posicionCursor = posicionCursor;
	}

	private void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	private boolean isHabilitado() {
		return this.habilitado;
	}

}
