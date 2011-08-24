package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class IniciarReclamo extends Activity implements LocationListener {

	private final int ERR_GPS_INACCESIBLE = 1;
	private final int ERR_CALLE_VACIO = 2;
	private final int ERR_ALTURA_VACIO = 3;
	private final int ERR_COORD_VACIO = 4;
	private final boolean DEBUG = true;
	private final int COORDENADA_DELAY = 120000; // 2 min para aplicar PLAN B
	private ProgressDialog procesando = null;
	private LocationManager locationManager;
	private CountDownTimer timer = null;
	private String tituloAlerta = "";
	private String mensageAlerta = "";

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
				.setOnItemSelectedListener(new UbicacionSpinnerListener());
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
		String tipo = ((Spinner) findViewById(R.id.tipo_incidente))
				.getSelectedItem().toString();

		// Este campo puede ser nulo
		String observ = ((EditText) findViewById(R.id.observaciones)).getText()
				.toString();

		if (((EditText) this.findViewById(R.id.latitud)).isEnabled()) {
			if (((EditText) findViewById(R.id.latitud)).getText().toString()
					.length() == 0) {
				mostrarAdvertencia(ERR_COORD_VACIO);
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
				mostrarAdvertencia(ERR_CALLE_VACIO);
			} else {

				if (((EditText) findViewById(R.id.altura)).getText().toString()
						.length() == 0) {
					mostrarAdvertencia(ERR_ALTURA_VACIO);
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
	 * @since 20-08-11
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

			// Arranca timer (PLAN B)
			this.timer = new CountDownTimer(COORDENADA_DELAY, 1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					// No hace nada
				}

				/**
				 * Para GPS y muestra la latitud y longitud de Medrano.
				 */
				@Override
				public void onFinish() {
					procesando.dismiss();
					mostrarCoordenada("-34.59841", "-58.42024");
					// Desactiva GPS
					locationManager.removeUpdates(IniciarReclamo.this);
				}
			}.start();
		} catch (Exception e) {
			this.mostrarAdvertencia(ERR_GPS_INACCESIBLE);
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
			preview.setImageBitmap(this.getRepo().getImagen());
		}
	}

	private void mostrarCoordenada(String latitud, String longitud) {
		((EditText) this.findViewById(R.id.latitud)).setText(latitud);
		((EditText) this.findViewById(R.id.longitud)).setText(longitud);
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

	/**
	 * Muestra una ventana de dialogo con un boton para cerrarla.
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 */
	private void mostrarAdvertencia(int numero) {
		// No se puede pasar los atributos directamente al Dialogo
		switch (numero) {
		case ERR_GPS_INACCESIBLE:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.gps_inaccesible);
			break;
		case ERR_ALTURA_VACIO:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.campo_altura_vacio);
			break;
		case ERR_CALLE_VACIO:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.campo_calle_vacio);
			break;
		case ERR_COORD_VACIO:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.campo_coord_vacio);
			break;
		default:
			break;
		}
	}

	/**
	 * Crea la ventana de Alerta. (Se hace de esta forma en Android 2.2)
	 * 
	 * @since 10-08-2011
	 * @author Paul
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		return new AlertDialog.Builder(IniciarReclamo.this)
				.setIcon(R.drawable.alert_dialog_icon)
				.setTitle(tituloAlerta)
				.setMessage(mensageAlerta)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								/* User clicked OK so do some stuff */
							}
						}).create();
	}

	// Metodos de la interfaz LocationListener
	public void onLocationChanged(Location location) {
		// Cancela PLAN B
		if (this.timer != null) {
			this.timer.cancel();
			this.timer = null;
		}
		// Cierra el dialogo Procesando
		if (this.procesando != null) {
			this.procesando.cancel();
			this.procesando = null;
		}
		this.mostrarCoordenada(location.getLatitude() + "",
				location.getLongitude() + "");
		// Desactiva GPS
		locationManager.removeUpdates(IniciarReclamo.this);

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
