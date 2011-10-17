package ar.com.thinksoft.ac.andrac.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import ar.com.thinksoft.ac.andrac.R;

/**
 * Atiende seleccion en el Spinner de Ubicacion.
 * 
 * @since 13-10-2011
 * @author Paul
 */
public class UbicacionSpinnerListener implements OnItemSelectedListener {

	/**
	 * Habilita o deshabilita los controles segun la opcion seleccionada en el
	 * Spiner de Ubicacion.
	 * 
	 * @since 13-10-2011
	 * @author Paul
	 */
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		if (pos == 0) {
			this.setGpsHabilitado(view, true);
			this.setDireccionHabilitada(view, false);
			this.limpiarDIreccion(view);
		} else {
			this.setGpsHabilitado(view, false);
			this.setDireccionHabilitada(view, true);
			this.limpiarGPS(view);
		}
	}

	private void setGpsHabilitado(View view, boolean valor) {

		((EditText) view.getRootView().findViewById(R.id.latitud))
				.setEnabled(valor);
		((EditText) view.getRootView().findViewById(R.id.longitud))
				.setEnabled(valor);
		((Button) view.getRootView().findViewById(R.id.boton_gps))
				.setEnabled(valor);
		((Button) view.getRootView().findViewById(R.id.boton_gps))
				.setFocusable(valor);
	}

	private void setDireccionHabilitada(View view, boolean valor) {

		((EditText) view.getRootView().findViewById(R.id.calle))
				.setEnabled(valor);
		((EditText) view.getRootView().findViewById(R.id.calle))
				.setFocusable(valor);
		((EditText) view.getRootView().findViewById(R.id.calle))
				.setFocusableInTouchMode(valor);
		// ((EditText) view.getRootView().findViewById(R.id.calle))
		// .setClickable(false);
		((EditText) view.getRootView().findViewById(R.id.altura))
				.setEnabled(valor);
		((EditText) view.getRootView().findViewById(R.id.altura))
				.setFocusable(valor);
		((EditText) view.getRootView().findViewById(R.id.altura))
				.setFocusableInTouchMode(valor);
		// ((EditText) view.getRootView().findViewById(R.id.altura))
		// .setClickable(false);
	}

	private void limpiarGPS(View view) {
		((EditText) view.getRootView().findViewById(R.id.latitud))
				.setText(null);
		((EditText) view.getRootView().findViewById(R.id.longitud))
				.setText(null);

	}

	private void limpiarDIreccion(View view) {
		((EditText) view.getRootView().findViewById(R.id.calle)).setText(null);
		((EditText) view.getRootView().findViewById(R.id.altura)).setText(null);
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Do nothing.
	}
}