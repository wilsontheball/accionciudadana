package ar.com.thinksoft.ac.andrac.servicios;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.andrac.dominio.Reclamo;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

/**
 * Se encarga de correr en 2do plano todas las funciones de conexion a servidor.
 * 
 * @since 28-09-2011
 * @author Paul
 */
public class ServicioRest extends IntentService {

	public static final int RUN = 0;
	public static final int FIN = 1;
	public static final int ERROR = -1;
	public static final String FUN = "funcion";
	public static final String REC = "receptor";

	public ServicioRest() {
		super("ServicioRest");
	}

	/**
	 * Atiende la creacion de un servicio.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 */
	protected void onHandleIntent(Intent intent) {
		final ResultReceiver receptor = intent.getParcelableExtra(REC);
		Log.d(this.getClass().getName(), receptor.toString());
		String funcion = intent.getStringExtra(FUN);
		Log.d(this.getClass().getName(), funcion);
		if (funcion.equals(FuncionRest.GETRECLAMOS)) {
			Bundle bundle = new Bundle();
			bundle.putString(FUN, FuncionRest.GETRECLAMOS);
			receptor.send(RUN, bundle);
			try {
				this.getReclamos(this.getRepo().getSrvUrl());
				bundle.putString(FUN, FuncionRest.GETRECLAMOS);
				receptor.send(FIN, bundle);
			} catch (ClientProtocolException e) {
				bundle.putString(FUN, FuncionRest.GETRECLAMOS);
				receptor.send(ERROR, bundle);
				Log.e(this.getClass().getName(), e.toString());
			} catch (IOException e) {
				bundle.putString(FUN, FuncionRest.GETRECLAMOS);
				receptor.send(ERROR, bundle);
				Log.e(this.getClass().getName(), e.toString());
			} catch (Exception e) {
				bundle.putString(FUN, FuncionRest.GETRECLAMOS);
				receptor.send(ERROR, bundle);
				Log.e(this.getClass().getName(), e.toString());
			}
		} else {
			Log.d(this.getClass().getName(),
					"Funcion desconocida: " + funcion.toString());
			this.stopSelf();
		}
	}

	/**
	 * Obtiene los reclamos del servidor Rest.
	 * 
	 * @since 20-09-2011
	 * @author Paul
	 * @param url
	 *            URL del servidor.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private void getReclamos(String url) throws ClientProtocolException,
			IOException {
		String respuesta = "";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet method = new HttpGet(url + "/" + FuncionRest.GETRECLAMOS + "/"
				+ "nick" + "/" + "pass");
		HttpResponse httpResponse = httpClient.execute(method);
		InputStream is = httpResponse.getEntity().getContent();
		Gson gson = new Gson();
		Reader reader = new InputStreamReader(is);
		Type collectionType = new TypeToken<List<Reclamo>>() {
		}.getType();
		List<Reclamo> reclamos = gson.fromJson(reader, collectionType);
		this.getRepo().setReclamosUsuario(reclamos);

		// XXX Imprime reclamos!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		respuesta = respuesta + "RECLAMOS\n";
		for (Reclamo d : reclamos) {
			respuesta = respuesta + " : " + d.getCalleIncidente() + " - "
					+ d.getAlturaIncidente() + ": " + d.getTipoIncidente()
					+ "\n";
		}
		Log.d(this.getClass().getName(), respuesta);
		// XXX Imprime reclamos!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

	/**
	 * Devuelve Repositorio.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 * @return Repositorio.
	 */
	private Repositorio getRepo() {
		return ((Aplicacion) this.getApplication()).getRepositorio();
	}
}
