package ar.com.thinksoft.ac.andrac.pantallas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.andrac.dominio.Reclamo;
import ar.com.thinksoft.ac.andrac.listener.UbicacionSpinnerListener;
import ar.com.thinksoft.ac.andrac.servicios.ServicioRest;
import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

/**
 * Maneja creacion de un reclamo.
 * 
 * @since 10-10-2011
 * @author Hernan
 */
public class IniciarReclamo extends Activity implements LocationListener {

	private final int ERR_GPS_INACCESIBLE = 1;
	private final int ERR_CALLE_VACIO = 2;
	private final int ERR_ALTURA_VACIO = 3;
	private final int ERR_COORD_VACIO = 4;
	private final int ERR_FALLO_ENVIO = 5;
	private final int ERR_FALLO_GUARDAR = 6;
	private final int COORDENADA_DELAY = 120000; // 2 min para aplicar PLAN B
	private Random random = new Random(new Date().getTime() * 1000);
	private double latitudActual;
	private double longitudActual;
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
		Spinner spinnerBarrio = (Spinner) findViewById(R.id.tipo_barrio);

		ArrayAdapter<CharSequence> adapterUbicacion = ArrayAdapter
				.createFromResource(this, R.array.ubicacion_array,
						android.R.layout.simple_spinner_item);

		ArrayAdapter<String> adapterIncidente = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				EnumTipoReclamo.getListaTiposReclamo());

		ArrayAdapter<String> adapterBarrio = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				EnumBarriosReclamo.getListaBarriosReclamo());

		adapterUbicacion
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerUbicacion.setAdapter(adapterUbicacion);

		adapterIncidente
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerIncidente.setAdapter(adapterIncidente);

		adapterBarrio
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerBarrio.setAdapter(adapterBarrio);

		spinnerUbicacion
				.setOnItemSelectedListener(new UbicacionSpinnerListener());
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
	 * Atiende resultados de ejecucion tanto de la Camara como de Login.
	 * 
	 * @since 10-10-2011
	 * @author Paul
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			// Resultado devuelto el OK.
			if (FuncionRest.POSTRECLAMO.equals(data
					.getStringExtra(ServicioRest.FUN))) {
				// Se envio reclamo.
				Toast.makeText(this, R.string.reclamo_enviado,
						Toast.LENGTH_LONG).show();
				this.finish();
			} else if (CamaraView.SACAR_FOTO.equals(data
					.getStringExtra(CamaraView.FUN))) {
				// Se saco una foto.
				ImageView preview = (ImageView) this
						.findViewById(R.id.fotoPreview);
				Bitmap foto = this.getFotoPreview(this.getRepo().getImagen());
				if (foto != null) {
					preview.setImageBitmap(foto);
				} else {
					Log.e(this.getClass().getName(),
							"this.getRepo().getImagen() es null!");
				}
			} else {
				Log.e(this.getClass().getName(),
						"No se sabe quien devolvio RESULT_OK");
			}

		} else if (resultCode == Activity.RESULT_CANCELED
				|| resultCode == Activity.RESULT_FIRST_USER) {
			// Resultado devuelto el CANCELED.
			if (FuncionRest.POSTRECLAMO.equals(data
					.getStringExtra(ServicioRest.FUN))) {
				// Fallo envio de reclamo.
				Log.e(this.getClass().getName(), "Fallo enviar reclamo.");

				try {
					this.guardarReclamo(getRepo().getReclamoAEnviar());
					this.mostrarAdvertencia(ERR_FALLO_ENVIO);
				} catch (IOException e) {
					Log.e(this.getClass().getName(), "Fallo guardar reclamo.");
					this.mostrarAdvertencia(ERR_FALLO_GUARDAR);
				}

			} else if (CamaraView.SACAR_FOTO.equals(data
					.getStringExtra(CamaraView.FUN))) {
				// Fallo sacar foto.
				Log.d(this.getClass().getName(), "No se saco la foto.");

			} else {
				Log.e(this.getClass().getName(),
						"No se sabe quien devolvio RESULT_OK");
			}

		} else {
			// Resultado devuelto es desconocido.
			Log.e(this.getClass().getName(),
					"Es un resultado de ejecucion desconocido.");
		}
	}

	/**
	 * Valida los datos, arma el reclamo y lo envia.
	 * 
	 * @since 07-10-2011
	 * @author Hernan
	 * @param v
	 */
	public void crearReclamo(View v) {
		String tipo = ((Spinner) findViewById(R.id.tipo_incidente))
				.getSelectedItem().toString();

		String barrio = ((Spinner) findViewById(R.id.tipo_barrio))
				.getSelectedItem().toString();

		// Este campo puede ser nulo
		String observ = ((EditText) findViewById(R.id.observaciones)).getText()
				.toString();

		if (((EditText) this.findViewById(R.id.latitud)).isEnabled()) {
			if (((EditText) findViewById(R.id.latitud)).getText().toString()
					.length() == 0) {
				mostrarAdvertencia(ERR_COORD_VACIO);
			} else {
				this.getRepo().publicarReclamoGPS(tipo, barrio,
						this.latitudActual, this.longitudActual, observ);
				this.ejecutarFuncionREST(FuncionRest.POSTRECLAMO);
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
					this.getRepo().publicarReclamoDireccion(tipo, barrio,
							calle, altura, observ);
					this.ejecutarFuncionREST(FuncionRest.POSTRECLAMO);
				}
			}
		}
	}

	/**
	 * Muestra la ventana de Login esperando resultado de ejecucion.
	 * 
	 * @since 07-10-2011
	 * @author Paul
	 */
	private void ejecutarFuncionREST(String funcion) {
		Intent proceso = new Intent(this, Login.class);
		proceso.putExtra(ServicioRest.FUN, funcion);
		this.startActivityForResult(proceso, 0);
	}

	/**
	 * Se obtiene la coordenada GPS mostrando un cuadro de dialogo mientras
	 * espera por la coord. En caso de no tener habilitado el GPS, pide al
	 * usuario habilitarlo. Responde al apretar boton GPS.
	 * 
	 * @since 07-10-2011
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
			this.setBotonGpsHabilitado(false);

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
					// Muestra la coordenada de Medrano
					latitudActual = getPlanBLatitud();
					longitudActual = getPlanBLongitud();
					mostrarCoordenada(latitudActual, longitudActual);
					// Desactiva GPS
					locationManager.removeUpdates(IniciarReclamo.this);
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
			this.mostrarAdvertencia(ERR_GPS_INACCESIBLE);
		}
	}

	/**
	 * Cierra la ventana de Reclamo. Responde al apretar boton Cancelar.
	 * 
	 * @since 20-08-11
	 * @author Hernan
	 * @param v
	 */
	public void cancelar(View v) {
		this.finish();
	}

	/**
	 * Muestra la pantalla de la camara de fotos. Responde al apretar boton
	 * Camara.
	 * 
	 * @since 20-08-11
	 * @author Hernan
	 * @param v
	 */
	public void tomarFoto(View v) {
		this.startActivityForResult(new Intent(this, CamaraView.class), 0);
	}

	/**
	 * Muestra las coordenadas en la los campos de la pantalla.
	 * 
	 * @since 07-10-2011
	 * @author Paul
	 * @param latitud
	 * @param longitud
	 */
	private void mostrarCoordenada(double latitud, double longitud) {
		((EditText) this.findViewById(R.id.latitud)).setText(latitud + "");
		((EditText) this.findViewById(R.id.longitud)).setText(longitud + "");
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
						setBotonGpsHabilitado(true);
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
			this.showDialog(numero);
			break;
		case ERR_ALTURA_VACIO:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.campo_altura_vacio);
			this.showDialog(numero);
			break;
		case ERR_CALLE_VACIO:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.campo_calle_vacio);
			this.showDialog(numero);
			break;
		case ERR_COORD_VACIO:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.campo_coord_vacio);
			this.showDialog(numero);
			break;
		case ERR_FALLO_ENVIO:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.reclamo_no_enviado);
			this.showDialog(numero);
			break;
		case ERR_FALLO_GUARDAR:
			this.tituloAlerta = getString(R.string.advertencia);
			this.mensageAlerta = getString(R.string.reclamo_no_guardado);
			this.showDialog(numero);
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
		AlertDialog dialogo = null;
		switch (id) {
		case ERR_FALLO_ENVIO:
			dialogo = new AlertDialog.Builder(IniciarReclamo.this)
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
			dialogo = new AlertDialog.Builder(IniciarReclamo.this)
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
			break;
		}
		return dialogo;
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

		this.latitudActual = location.getLatitude();
		this.longitudActual = location.getLongitude();
		this.mostrarCoordenada(this.latitudActual, this.longitudActual);
		// Desactiva GPS
		locationManager.removeUpdates(IniciarReclamo.this);

		Log.d(this.getClass().getName(), "onLocationChanged");

	}

	/**
	 * Hablita o deshabilita el boton GPS.
	 * 
	 * @since 09-09-2011
	 * @author Paul
	 * @param valor
	 *            Cuando "valor" es <code>true</code> boton esta habilitado y se
	 *            puede seleccionar, es al reves cuando es <code>false</code>.
	 */
	private void setBotonGpsHabilitado(boolean valor) {
		((Button) this.findViewById(R.id.boton_gps)).setEnabled(valor);
		((Button) this.findViewById(R.id.boton_gps)).setFocusable(valor);
	}

	/**
	 * Convierte array de foto a Bitmap para preview.
	 * 
	 * @since 11-09-2011
	 * @author Paul
	 * @param imagen
	 *            Imagen en formato de array de byte.
	 * @return Imagen en formato Bitmap.
	 */
	private Bitmap getFotoPreview(byte[] imagen) {
		return BitmapFactory.decodeByteArray(imagen, 0, imagen.length, null);
	}

	/**
	 * Devuelve latitud de Medrano.
	 * 
	 * @since 07-10-2011
	 * @author Paul
	 * @return Latitud.
	 */
	private double getPlanBLatitud() {
		// Genera enteros comprendidos entre 0 y 9
		int x = 0;
		for (int i = 0; i < 10; i++) {
			x = (int) (random.nextDouble() * 100.0);
		}
		return (x * 0.00001) - 34.5984;
	}

	/**
	 * Devuelve longitud de Medrano.
	 * 
	 * @since 07-10-2011
	 * @author Paul
	 * @return Longitud.
	 */
	private double getPlanBLongitud() {
		// Genera enteros comprendidos entre 0 y 9
		int x = 0;
		for (int i = 0; i < 10; i++) {
			x = (int) (random.nextDouble() * 100.0);
		}
		return (x * 0.00001) - 58.4202;
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d(this.getClass().getName(), "onStatusChanged");
	}

	public void onProviderEnabled(String provider) {
		Log.d(this.getClass().getName(), "onProviderEnabled");
	}

	public void onProviderDisabled(String provider) {
		Log.d(this.getClass().getName(), "onProviderDisabled");
	}

	/**
	 * Guarda un reclamo en la memoria del celular.
	 * 
	 * @since 09-10-2011
	 * @author Hernan
	 * @param reclamo
	 *            Reclamo a guardar.
	 * @throws IOException
	 */
	private void guardarReclamo(Reclamo reclamo) throws IOException {

		// Se crea el nombre de archivo.
		String fechaConFormato = reclamo.getFechaReclamo().replace('/', '-');
		String nombreArchivo = reclamo.getTipoIncidente() + " "
				+ fechaConFormato + " " + getRepo().getHoraConFormato();

		// Se convierte el objeto a array de byte.
		Gson gson = new Gson();
		String reclamoString = gson.toJson(reclamo);

		// Se graba el archivo.
		FileOutputStream stream = openFileOutput(nombreArchivo,
				Context.MODE_PRIVATE);
		stream.write(reclamoString.getBytes());
		stream.close();
	}
}
