package ar.com.thinksoft.ac.andrac;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Atiende seleccion en el Spinner de Ubicacion.
 * 
 * @since 20-08-2011
 * @author Hernan
 */
class UbicacionSpinnerListener implements OnItemSelectedListener {

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		if (pos == 0) {
			((EditText) view.getRootView().findViewById(R.id.latitud))
					.setEnabled(true);
			((EditText) view.getRootView().findViewById(R.id.longitud))
					.setEnabled(true);
			((Button) view.getRootView().findViewById(R.id.boton_gps))
					.setEnabled(true);
			((EditText) view.getRootView().findViewById(R.id.calle))
					.setEnabled(false);
			// ((EditText) view.getRootView().findViewById(R.id.calle))
			// .setFocusable(false);
			// ((EditText) view.getRootView().findViewById(R.id.calle))
			// .setClickable(false);
			((EditText) view.getRootView().findViewById(R.id.altura))
					.setEnabled(false);
			// ((EditText) view.getRootView().findViewById(R.id.altura))
			// .setFocusable(false);
			// ((EditText) view.getRootView().findViewById(R.id.altura))
			// .setClickable(false);
		} else {
			((EditText) view.getRootView().findViewById(R.id.latitud))
					.setEnabled(false);
			((EditText) view.getRootView().findViewById(R.id.longitud))
					.setEnabled(false);
			((Button) view.getRootView().findViewById(R.id.boton_gps))
					.setEnabled(false);
			((EditText) view.getRootView().findViewById(R.id.calle))
					.setEnabled(true);
			// ((EditText) view.getRootView().findViewById(R.id.calle))
			// .setFocusable(true);
			// ((EditText) view.getRootView().findViewById(R.id.calle))
			// .setClickable(true);
			((EditText) view.getRootView().findViewById(R.id.altura))
					.setEnabled(true);
			// ((EditText) view.getRootView().findViewById(R.id.altura))
			// .setFocusable(true);
			// ((EditText) view.getRootView().findViewById(R.id.altura))
			// .setClickable(true);
		}
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Do nothing.
	}
}