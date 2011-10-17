package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ar.com.thinksoft.ac.andrac.R;

/**
 * Permite la configuracion de la conexion al servidor WilsonD.
 * 
 * @since 14-10-2011
 * @author Paul
 */
public class Configuracion extends Activity {

	static final String ANDRAC_URL = "andrac_url";
	static final String ANDRAC_PUERTO = "andrac_puerto";

	// ip o url de servidor
	private String url = null;

	// puerto de servidor
	private String puerto = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.configuracion);

		this.obtenerPreferencias();
	}

	/**
	 * Guarda la configuracion en el registro del telefono y cierra la ventana.
	 * 
	 * @since 14-10-2011
	 * @author Paul
	 * @param v
	 */
	public void guardar(View v) {

		this.guardarPreferencias(this.getUrl(), this.getPort());
		Toast.makeText(this, R.string.config_guardada, Toast.LENGTH_SHORT)
				.show();
		this.finish();
	}

	/**
	 * Cierra la ventana sin guardar.
	 * 
	 * @since 14-10-2011
	 * @author Paul
	 * @param v
	 */
	public void cancelar(View v) {
		this.finish();
	}

	/**
	 * Obtiene url y puerto de servidor guardados en el registro del telefono.
	 * 
	 * @since 14-10-2011
	 * @author Paul
	 */
	private void obtenerPreferencias() {
		SharedPreferences preferencias = getSharedPreferences(this.getClass()
				.getName(), MODE_WORLD_READABLE);
		url = preferencias.getString(ANDRAC_URL, null);
		puerto = preferencias.getString(ANDRAC_PUERTO, null);

		if (url != null && puerto != null) {
			this.setUrl(url);
			this.setPort(puerto);
		}

		Log.d(this.getClass().getName(), "Obtener prefs: " + url + " " + puerto);
	}

	/**
	 * Guarda url y puerto de servidor en el registro del telefo.
	 * 
	 * @since 14-10-2011
	 * @author Paul
	 */
	private void guardarPreferencias(String url, String puerto) {
		SharedPreferences preferencias = getSharedPreferences(this.getClass()
				.getName(), MODE_WORLD_READABLE);
		Editor editor = preferencias.edit();
		editor.putString(ANDRAC_URL, url);
		editor.putString(ANDRAC_PUERTO, puerto);
		editor.commit();

		Log.d(this.getClass().getName(), "Guardar prefs: " + url + " " + puerto);
	}

	/* Metodos que obtienen el contenido de los campos de la pantalla */

	private void setUrl(String url) {
		((EditText) findViewById(R.id.url)).setText(url);
	}

	private String getUrl() {
		return ((EditText) findViewById(R.id.url)).getText().toString();
	}

	private void setPort(String puerto) {
		((EditText) findViewById(R.id.puerto)).setText(puerto);
	}

	private String getPort() {
		return ((EditText) findViewById(R.id.puerto)).getText().toString();
	}
}
