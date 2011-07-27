package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Spinner;
import android.widget.Toast;

class MyOnItemSelectedListener implements OnItemSelectedListener {

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// Toast.makeText(
		// parent.getContext(),
		// "La opcion elegida es "
		// + parent.getItemAtPosition(pos).toString(),
		// Toast.LENGTH_LONG).show();
		if (pos == 0) {
			((EditText) view.getRootView().findViewById(R.id.coordenada))
					.setEnabled(true);
			((Button) view.getRootView().findViewById(R.id.boton_gps))
					.setEnabled(true);
			((EditText) view.getRootView().findViewById(R.id.calle))
					.setEnabled(false);
			((EditText) view.getRootView().findViewById(R.id.altura))
					.setEnabled(false);
		} else {
			((EditText) view.getRootView().findViewById(R.id.coordenada))
					.setEnabled(false);
			((Button) view.getRootView().findViewById(R.id.boton_gps))
					.setEnabled(false);
			((EditText) view.getRootView().findViewById(R.id.calle))
					.setEnabled(true);
			((EditText) view.getRootView().findViewById(R.id.altura))
					.setEnabled(true);
		}
	}

	public void onNothingSelected(AdapterView parent) {
		// Do nothing.
	}
}

class MyLocationListener implements LocationListener {
	private View view = null;

	// Contructor
	public MyLocationListener(View v) {
		this.view = v;
	}

	public void onLocationChanged(Location location) {
		// Called when a new location is found by the network location
		// provider.
		((EditText) this.view.getRootView().findViewById(R.id.coordenada))
				.setText(location.getLatitude() + "," + location.getLongitude());
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onProviderDisabled(String provider) {
	}
}

public class IniciarReclamo extends Activity {

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

		// ((EditText) findViewById(R.id.calle)).setEnabled(true);
	}

	public void crearReclamo(View v) {

		// TODO falta agregar parametro de la foto

		String nick = ((Aplicacion) this.getApplication()).getNombreUsuario();
		String tipo = ((Spinner) findViewById(R.id.tipo_incidente))
				.getSelectedItem().toString();
		String observ = ((EditText) findViewById(R.id.observaciones)).getText()
				.toString();

		if (((EditText) this.findViewById(R.id.coordenada)).isEnabled()) {

			// TODO chequear q la coordenada no este vacia

			String coord = ((EditText) findViewById(R.id.coordenada)).getText()
					.toString();
			((Aplicacion) this.getApplication()).getRepositorio()
					.publicarReclamoGPS(nick, tipo, coord, observ);

		} else {

			// TODO chequear q la calle y altura no esten vacios
			// getText.ToString.isempty()
			String calle = ((EditText) findViewById(R.id.calle)).getText()
					.toString();
			String altura = ((EditText) findViewById(R.id.altura)).getText()
					.toString();

			((Aplicacion) this.getApplication())
					.getRepositorio()
					.publicarReclamoDireccion(nick, tipo, calle, altura, observ);
		}
		
		this.finish();
	}

	public void obtenerCoordenada(View v) {
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		MyLocationListener locationListener = new MyLocationListener(v);

		// Register the listener with the Location Manager to receive location
		// updates
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	}
	
	public void cancelar(View v) {
		this.finish();
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
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(titulo)
				.setMessage(mensaje)
				.setCancelable(false)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
