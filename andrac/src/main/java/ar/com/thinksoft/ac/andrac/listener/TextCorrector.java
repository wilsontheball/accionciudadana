package ar.com.thinksoft.ac.andrac.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import ar.com.thinksoft.ac.andrac.pantallas.Registro;

/**
 * Limita el texto a minusculas y numeros en el EditText.
 * 
 * @since 24-10-2011
 * @author Paul
 */
public class TextCorrector implements TextWatcher {

	private EditText campoACorregir;
	private String cadenaValida;
	private int posicionCursor;

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

		if (secuencia.toString().matches("[a-z0-9]*")) {
			this.setCadenaValida(secuencia + "");
			this.setPosicionCursor(this.getCampoACorregir().getSelectionStart());
			Log.i(Registro.class.getName(), "Es minuscula o digito: "
					+ secuencia + "cursor: " + posicion);
		} else {
			this.getCampoACorregir().setText(this.getCadenaValida());
			this.getCampoACorregir().setSelection(this.getPosicionCursor());
			Log.e(Registro.class.getName(), "No es minuscula o digito: "
					+ secuencia);
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
		return this.posicionCursor;
	}

	private void setPosicionCursor(int posicionCursor) {
		this.posicionCursor = posicionCursor;
	}

}
