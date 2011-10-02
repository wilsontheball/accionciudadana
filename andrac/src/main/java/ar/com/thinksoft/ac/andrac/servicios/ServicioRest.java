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
import ar.com.thinksoft.ac.andrac.dominio.Usuario;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

/**
 * Se encarga de correr en 2do plano todas las funciones de conexion a servidor.
 * 
 * @since 29-09-2011
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

		Bundle bundle = new Bundle();
		try {
			bundle.putString(FUN, funcion);
			receptor.send(RUN, bundle);
			if (FuncionRest.GETRECLAMOS.equals(funcion)) {
				// Funcion GET Reclamos.
				this.getReclamos(this.getRepo().getSrvUrl());
			} else if (FuncionRest.GETPERFIL.equals(funcion)) {
				// Funcion GET Perfil.
				this.getPerfil(this.getRepo().getSrvUrl());
			} else {
				// Funcion desconocida!
				bundle.putString(FUN, funcion);
				receptor.send(ERROR, bundle);
				this.stopSelf();
				Log.e(this.getClass().getName(), "Funcion desconocida!");
				return;
			}
			bundle.putString(FUN, funcion);
			receptor.send(FIN, bundle);
			this.stopSelf();
		} catch (ClientProtocolException e) {
			bundle.putString(FUN, funcion);
			receptor.send(ERROR, bundle);
			this.stopSelf();
			Log.e(this.getClass().getName(), e.toString());
		} catch (IOException e) {
			bundle.putString(FUN, funcion);
			receptor.send(ERROR, bundle);
			this.stopSelf();
			Log.e(this.getClass().getName(), e.toString());
		} catch (Exception e) {
			bundle.putString(FUN, funcion);
			receptor.send(ERROR, bundle);
			this.stopSelf();
			Log.e(this.getClass().getName(), e.toString());
		}
	}

	/**
	 * Obtiene los reclamos del servidor Rest.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 * @param url
	 *            URL del servidor.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private void getReclamos(String url) throws ClientProtocolException,
			IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet method = new HttpGet(url + "/" + FuncionRest.GETRECLAMOS + "/"
				+ this.getRepo().getNombreUsuario() + "/"
				+ this.getRepo().getPass());
		HttpResponse httpResponse = httpClient.execute(method);
		InputStream is = httpResponse.getEntity().getContent();
		Gson gson = new Gson();
		Reader reader = new InputStreamReader(is);
		Type collectionType = new TypeToken<List<Reclamo>>() {
		}.getType();
		List<Reclamo> reclamos = gson.fromJson(reader, collectionType);
		this.getRepo().setReclamosUsuario(reclamos);

		// XXX Imprime reclamos!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		String respuesta = "";
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
	 * Obtiene perfil de usuario del servidor Rest.
	 * 
	 * @since 29-09-2011
	 * @author Paul
	 * @param url
	 *            URL del servidor.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private void getPerfil(String url) throws ClientProtocolException,
			IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet method = new HttpGet(url + "/" + FuncionRest.GETPERFIL + "/"
				+ this.getRepo().getNombreUsuario() + "/"
				+ this.getRepo().getPass());
		HttpResponse httpResponse = httpClient.execute(method);
		InputStream is = httpResponse.getEntity().getContent();
		Gson gson = new Gson();
		Reader reader = new InputStreamReader(is);
		Usuario perfil = gson.fromJson(reader, Usuario.class);
		this.getRepo().setPerfilUsuario(perfil);
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
