package ar.com.thinksoft.ac.andrac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Se encarga de presentar los datos de perfil de usuario y da la posibilidad de
 * modificarlos.
 * 
 * @since 07-09-2011
 * @author Paul
 */
public class PerfilUsuario extends Activity {

	/**
	 * Se encarga de la creacion de la ventana.
	 * 
	 * @since 28-08-2011
	 * @author Paul
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.perfil);
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
	 * Cierra la ventana. Llamado por el boton Cancelar.
	 * 
	 * @since 28-08-2011
	 * @author Paul
	 * @param v
	 */
	public void salir(View v) {
		this.finish();
	}

	// Probando JSON
	private String url = "http://192.168.1.102:9998/wilsond/flights";

	private EditText getSalida() {
		return (EditText) findViewById(R.id.salida);
	}

	public void probar(View v) {
		getSalida().append("Conectando...\n");
		String respuesta = this.getJSONdata(this.url);
		getSalida().append(respuesta);
		// RestClient.connect(url);
	}

	private String getJSONdata(String url) {
		String response = "";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet method = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(method);
			InputStream is = httpResponse.getEntity().getContent();
			response = convertStreamToString(is);
		} catch (Exception e) {
			getSalida().append("ERROR de conexion: " + e.getMessage());
			e.printStackTrace();
		}
		Log.i("phpwsex", "JSON Response: " + response);
		return response;
	}

	private String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			getSalida().append("ERROR de lectura: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
