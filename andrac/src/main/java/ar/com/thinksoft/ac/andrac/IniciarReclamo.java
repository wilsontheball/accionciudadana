package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

class MyOnItemSelectedListener implements OnItemSelectedListener {

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

public class IniciarReclamo extends Activity implements LocationListener {
	private final boolean DEBUG = false;
	private ProgressDialog procesando = null;
	LocationManager locationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iniciar_reclamo);

		Spinner spinnerUbicacion = (Spinner) findViewById(R.id.ubicacion);
		Spinner spinnerIncidente = (Spinner) findViewById(R.id.tipo_incidente);

		ArrayAdapter<CharSequence> adapterUbicacion = ArrayAdapter
				.createFromResource(this, R.array.ubicacion_array,
						android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> adapterIncidente = ArrayAdapter
				.createFromResource(this, R.array.tipoincidente_array,
						android.R.layout.simple_spinner_item);

		adapterUbicacion
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerUbicacion.setAdapter(adapterUbicacion);

		adapterIncidente
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerIncidente.setAdapter(adapterIncidente);

		spinnerUbicacion
				.setOnItemSelectedListener(new MyOnItemSelectedListener());
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

	public void crearReclamo(View v) {

		// TODO falta agregar parametro de la foto

		String tipo = ((Spinner) findViewById(R.id.tipo_incidente))
				.getSelectedItem().toString();

		// Este campo puede ser nulo
		String observ = ((EditText) findViewById(R.id.observaciones)).getText()
				.toString();

		if (((EditText) this.findViewById(R.id.latitud)).isEnabled()) {

			// TODO chequear q la coordenada no este vacia

			if (((EditText) findViewById(R.id.latitud)).getText().toString()
					.length() == 0) {

				mostrarAdvertencia(getString(R.string.advertencia),
						getString(R.string.campo_coord_vacio));

			} else {
				String latitud = ((EditText) findViewById(R.id.latitud))
						.getText().toString();
				String longitud = ((EditText) findViewById(R.id.longitud))
						.getText().toString();
				this.getRepo().publicarReclamoGPS(tipo, latitud, longitud,
						observ);
				Toast.makeText(this, R.string.reclamo_enviado,
						Toast.LENGTH_LONG).show();
				this.finish();
			}
		} else {
			if (((EditText) findViewById(R.id.calle)).getText().toString()
					.length() == 0) {
				mostrarAdvertencia(getString(R.string.advertencia),
						getString(R.string.campo_calle_vacio));
			} else {

				if (((EditText) findViewById(R.id.altura)).getText().toString()
						.length() == 0) {
					mostrarAdvertencia(getString(R.string.advertencia),
							getString(R.string.campo_altura_vacio));
				} else {
					String calle = ((EditText) findViewById(R.id.calle))
							.getText().toString();
					String altura = ((EditText) findViewById(R.id.altura))
							.getText().toString();

					this.getRepo().publicarReclamoDireccion(tipo, calle,
							altura, observ);
					Toast.makeText(this, R.string.reclamo_enviado,
							Toast.LENGTH_LONG).show();
					this.finish();
				}
			}
		}
	}

	/**
	 * Se obtiene la coordenada GPS mostrando un cuadro de dialogo mientras
	 * espera por la coord. En caso de no tener habilitado el GPS, pide al
	 * usuario habilitarlo
	 * 
	 * @since 16-08-11
	 * @author Hernan
	 * @param v
	 */
	public void obtenerCoordenada(View v) {

		try {
			// Acquire a reference to the system Location Manager
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

			locationManager.requestLocationUpdates(
					locationManager.getBestProvider(new Criteria(), true), 0,
					0, this);

			// Desactiva el boton una vez que lo apretas
			((Button) findViewById(R.id.boton_gps)).setEnabled(false);

			// Muestra el dialogo procesando
			this.mostrarProcesando();

		} catch (Exception e) {
			// TODO Aca se deberia mostrar advertencia
			Toast.makeText(this,
					"Por favor habilite su GPS para obtener coordenada." + e,
					Toast.LENGTH_LONG).show();
		}
	}

	public void cancelar(View v) {
		this.finish();
	}

	public void tomarFoto(View v) {

		this.startActivityForResult(new Intent(this, CamaraView.class), 0);

		// this.startActivity(new Intent(v.getContext(), CamaraView.class));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_CANCELED) {
			ImageView preview = (ImageView) this.findViewById(R.id.fotoPreview);
			preview.setImageBitmap(((Aplicacion) this.getApplication())
					.getRepositorio().getImagen());
		}
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
		// TODO Aca se deberia mostrar advertencia
		Toast.makeText(this, "Aca se deberia mostrar advertencia",
				Toast.LENGTH_LONG).show();
	}

	/**
	 * Devuelve el Repositorio
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @return repositorio
	 */

	private Repositorio getRepo() {
		return ((Aplicacion) this.getApplication()).getRepositorio();
	}

	private void mostrarProcesando() {
		this.procesando = new ProgressDialog(IniciarReclamo.this);
		this.procesando.setMessage("Obteniendo coordenada...");
		this.procesando.setButton("Cancelar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						locationManager.removeUpdates(IniciarReclamo.this);
						((Button) findViewById(R.id.boton_gps))
								.setEnabled(true);
						dialog.cancel();
					}
				});
		this.procesando.setCancelable(false);
		this.procesando.show();
	}

	// Metodos de la interfaz LocationListener
	public void onLocationChanged(Location location) {
		((EditText) this.findViewById(R.id.latitud)).setText(location
				.getLatitude() + "");

		((EditText) this.findViewById(R.id.longitud)).setText(location
				.getLongitude() + "");
		if (this.procesando != null) {
			this.procesando.cancel();
			this.procesando = null;
		}
		if (DEBUG) {
			Toast.makeText(this, "onLocationChanged", Toast.LENGTH_LONG).show();
		}

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		if (DEBUG) {
			Toast.makeText(this, "onStatusChanged", Toast.LENGTH_LONG).show();
		}
	}

	public void onProviderEnabled(String provider) {
		if (DEBUG) {
			Toast.makeText(this, "onProviderEnabled", Toast.LENGTH_LONG).show();
		}
	}

	public void onProviderDisabled(String provider) {
		if (DEBUG) {
			Toast.makeText(this, "onProviderDisabled", Toast.LENGTH_LONG)
					.show();
		}
	}

}
