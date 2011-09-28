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
 * @since 28-08-2011
 * @author Hernan
 */
public class UbicacionSpinnerListener implements OnItemSelectedListener {

	/**
	 * Habilita o deshabilita los controles segun la opcion seleccionada en el
	 * Spiner de Ubicacion.
	 * 
	 * @since 28-08-2011
	 * @author Hernan
	 */
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		if (pos == 0) {
			((EditText) view.getRootView().findViewById(R.id.latitud))
					.setEnabled(true);
			((EditText) view.getRootView().findViewById(R.id.longitud))
					.setEnabled(true);
			((Button) view.getRootView().findViewById(R.id.boton_gps))
					.setEnabled(true);
			((Button) view.getRootView().findViewById(R.id.boton_gps))
					.setFocusable(true);
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
			((Button) view.getRootView().findViewById(R.id.boton_gps))
					.setFocusable(false);
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