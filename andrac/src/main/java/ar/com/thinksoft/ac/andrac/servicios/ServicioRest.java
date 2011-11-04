package ar.com.thinksoft.ac.andrac.servicios;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.preference.PreferenceManager;
import android.util.Log;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.andrac.dominio.Imagen;
import ar.com.thinksoft.ac.andrac.dominio.Reclamo;
import ar.com.thinksoft.ac.andrac.dominio.Usuario;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Se encarga de correr en 2do plano todas las funciones de conexion a servidor.
 * 
 * @since 04-11-2011
 * @author Paul
 */
public class ServicioRest extends IntentService {

	public static final int RUN = 0;
	public static final int FIN = 1;
	public static final int ERROR = -1;
	public static final int ALIEN = -2;
	public static final int DUPLICADO = -3;
	public static final String FUN = "funcion";
	public static final String REC = "receptor";

	private DefaultHttpClient clienteHttp;

	public ServicioRest() {
		super("ServicioRest");
	}

	/**
	 * Atiende la creacion de un servicio.
	 * 
	 * @since 03-11-2011
	 * @author Paul
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		final ResultReceiver receptor = intent.getParcelableExtra(REC);
		String funcion = intent.getStringExtra(FUN);
		Log.d(this.getClass().getName(), "Funcion: " + funcion);

		// Almacena temporalmente la respuesta.
		HttpResponse respuestaHttp = null;

		// Por defecto devuelve ERROR.
		int retorno = ERROR;

		Bundle bundle = new Bundle();

		try {
			bundle.putString(FUN, funcion);
			receptor.send(RUN, bundle);

			if (FuncionRest.GETRECLAMOS.equals(funcion)) {
				// Funcion GET Reclamos.
				respuestaHttp = this.getReclamos(this.getRepo().getUrlServer(),
						this.getRepo().getNick(), this.getRepo().getPass());
			} else if (FuncionRest.GETPERFIL.equals(funcion)) {
				// Funcion GET Perfil.
				respuestaHttp = this.getPerfil(this.getRepo().getUrlServer(),
						this.getRepo().getNick(), this.getRepo().getPass());
			} else if (FuncionRest.POSTRECLAMO.equals(funcion)) {
				// Funcion POST Reclamo.
				respuestaHttp = this.postReclamo(this.getRepo().getUrlServer(),
						this.getRepo().getNick(), this.getRepo().getPass(),
						this.getRepo().getReclamoAEnviar());
			} else if (FuncionRest.POSTUSUARIO.equals(funcion)) {
				// Funcion POST Usuario.
				respuestaHttp = this.postUsuario(this.getRepo().getUrlServer(),
						this.getRepo().getUsuarioARegistrar());
			} else {
				// Funcion desconocida.
				Log.e(this.getClass().getName(), "Funcion desconocida!");
				throw new Exception("Funcion desconocida!");
			}
			retorno = FIN;
		} catch (ClientProtocolException e) {
			retorno = ERROR;
			Log.e(this.getClass().getName(), "HTTP_Excepcion: " + e.toString());
		} catch (IOException e) {
			retorno = ERROR;
			Log.e(this.getClass().getName(), "IO_Excepcion: " + e.toString());
		} catch (Exception e) {
			retorno = ERROR;
			Log.e(this.getClass().getName(), "Excepcion: " + e.toString());
		} finally {
			int codigoHttp = respuestaHttp != null ? respuestaHttp
					.getStatusLine().getStatusCode() : -1;
			String razon = respuestaHttp != null ? respuestaHttp
					.getStatusLine().getReasonPhrase() : "Timeout?";
			if (retorno == FIN && codigoHttp == HttpURLConnection.HTTP_OK) {
				retorno = FIN;
				Log.i(this.getClass().getName(), "HTTP_OK FIN. Retorno: "
						+ retorno + " CodigoHTTP: " + codigoHttp + " Razon: "
						+ razon);
			} else if (codigoHttp == HttpURLConnection.HTTP_FORBIDDEN) {
				retorno = ALIEN;
				Log.e(this.getClass().getName(),
						"HTTP_FORBIDDEN ALIEN. Retorno: " + retorno
								+ " CodigoHTTP: " + codigoHttp + " Razon: "
								+ razon);
			} else if (codigoHttp == HttpURLConnection.HTTP_CONFLICT) {
				retorno = DUPLICADO;
				Log.e(this.getClass().getName(),
						"HTTP_CONFLICT DUPLICADO. Retorno: " + retorno
								+ " CodigoHTTP: " + codigoHttp + " Razon: "
								+ razon);
			} else {
				retorno = ERROR;
				Log.e(this.getClass().getName(), "HTTP ERROR. Retorno: "
						+ retorno + " CodigoHTTP: " + codigoHttp + " Razon: "
						+ razon);
			}
			bundle.putString(FUN, funcion);
			receptor.send(retorno, bundle);
			this.stopSelf();
		}
	}

	/**
	 * Obtiene los reclamos del servidor Rest.
	 * 
	 * @since 04-11-2011
	 * @author Paul
	 * @param url
	 *            URL del servidor.
	 * @return HttpResponse
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResponse getReclamos(String url, String nick, String pass)
			throws ClientProtocolException, IOException {

		HttpGet method = new HttpGet(url + "/" + FuncionRest.GETRECLAMOS + "/"
				+ nick + "/" + pass);
		HttpResponse httpResponse = getClienteHttp().execute(method);

		// Si la respuesta no es de tipo HTTP_OK no hace nada.
		if (httpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
			InputStream is = httpResponse.getEntity().getContent();
			Gson gson = new Gson();
			Reader reader = new InputStreamReader(is);
			Type collectionType = new TypeToken<List<Reclamo>>() {
			}.getType();
			List<Reclamo> reclamos = gson.fromJson(reader, collectionType);
			this.getRepo().setReclamosUsuario(reclamos);
		}
		return httpResponse;
	}

	/**
	 * Obtiene perfil de usuario del servidor Rest.
	 * 
	 * @since 04-11-2011
	 * @author Paul
	 * @param url
	 *            URL del servidor.
	 * @return HttpResponse
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResponse getPerfil(String url, String nick, String pass)
			throws ClientProtocolException, IOException {

		HttpGet method = new HttpGet(url + "/" + FuncionRest.GETPERFIL + "/"
				+ nick + "/" + pass);
		HttpResponse httpResponse = getClienteHttp().execute(method);

		// Si la respuesta no es de tipo HTTP_OK no hace nada.
		if (httpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
			InputStream is = httpResponse.getEntity().getContent();
			Gson gson = new Gson();
			Reader reader = new InputStreamReader(is);
			Usuario perfil = gson.fromJson(reader, Usuario.class);
			this.getRepo().setPerfilUsuario(perfil);
		}
		return httpResponse;
	}

	/**
	 * Manda un reclamo al servidor Rest.
	 * 
	 * @since 04-11-2011
	 * @author Paul
	 * @param url
	 *            URL del servidor.
	 * @param reclamo
	 *            Reclamo a enviar.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResponse postReclamo(String url, String nick, String pass,
			Reclamo reclamo) throws ClientProtocolException, IOException {

		// Primero chequea la conexion para no perder tiempo.
		// HttpResponse respuesta = getClienteHttp().execute(new
		// HttpTrace(url));
		// if (respuesta.getStatusLine().getStatusCode() !=
		// HttpURLConnection.HTTP_OK) {
		// Log.e(this.getClass().getName(), "Fallo la conexion!");
		// return respuesta;
		// } else {
		// Log.d(this.getClass().getName(), "Hay conexion!");
		// }

		// Obtiene la imagen.
		if (reclamo.getNombreImagen() != null) {
			byte[] foto = this.getFoto(reclamo.getNombreImagen());
			if (foto != null) {
				reclamo.setImagen(new Imagen(foto, Imagen.TIPO_JPG));
			}
		}

		StringEntity entidad = new StringEntity(new Gson().toJson(reclamo));
		Header header = new BasicHeader(HTTP.CONTENT_TYPE, "application/json");
		entidad.setContentEncoding(header);

		HttpPost post = new HttpPost(url + "/" + FuncionRest.POSTRECLAMO + "/"
				+ nick + "/" + pass);
		post.setEntity(entidad);
		return getClienteHttp().execute(post);
	}

	/**
	 * Manda un usuario al servidor Rest. No se manda nick ni pass.
	 * 
	 * @since 04-11-2011
	 * @author Paul
	 * @param url
	 *            URL del servidor.
	 * @param usuario
	 *            Usuario a enviar.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResponse postUsuario(String url, Usuario usuario)
			throws ClientProtocolException, IOException {

		// Primero chequea la conexion para no perder tiempo.
		// HttpResponse respuesta = getClienteHttp().execute(new
		// HttpTrace(url));
		// if (respuesta.getStatusLine().getStatusCode() !=
		// HttpURLConnection.HTTP_OK) {
		// Log.e(this.getClass().getName(), "Fallo la conexion!");
		// return respuesta;
		// } else {
		// Log.d(this.getClass().getName(), "Hay conexion!");
		// }

		StringEntity entidad = new StringEntity(new Gson().toJson(usuario));
		Header header = new BasicHeader(HTTP.CONTENT_TYPE, "application/json");
		entidad.setContentEncoding(header);

		HttpPost post = new HttpPost(url + "/" + FuncionRest.POSTUSUARIO);
		post.setEntity(entidad);
		return getClienteHttp().execute(post);
	}

	/**
	 * Obtiene array de foto.
	 * 
	 * @since 01-11-2011
	 * @author Paul
	 * @param nombreArchivo
	 *            Nombre del archivo.
	 * @return Imagen en byte[] o null si no lo pudo obtener.
	 */
	private byte[] getFoto(String nombreArchivo) {
		try {
			FileInputStream stream = openFileInput(nombreArchivo);
			byte[] bytes = new byte[stream.available()];
			stream.read(bytes);
			return bytes;
		} catch (Exception e) {
			Log.e(this.getClass().getSimpleName(), "No encontro: "
					+ nombreArchivo + e);
			return null;
		}
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

	/**
	 * Devuelve de forma lazy Cliente configurado para conexion HTTP.
	 * 
	 * @since 04-11-2011
	 * @author Paul
	 * @return DefaultHttpClient.
	 */
	private DefaultHttpClient getClienteHttp() {
		if (this.clienteHttp == null) {
			SharedPreferences preferencias = PreferenceManager
					.getDefaultSharedPreferences(this);
			String timeoutStr = preferencias.getString(
					getString(R.string.key_timeout), null);
			if (timeoutStr == null || timeoutStr.length() == 0) {
				timeoutStr = getString(R.string.timeout_estandar);
			}
			// Se transforma a milisegundos
			int timeout = new Integer(timeoutStr).intValue() * 1000;
			HttpParams httpParameters = new BasicHttpParams();
			httpParameters.setParameter(AllClientPNames.CONNECTION_TIMEOUT,
					timeout);
			httpParameters.setParameter(AllClientPNames.SO_TIMEOUT, timeout);
			// httpParameters.setParameter(AllClientPNames.HTTP_CONTENT_CHARSET,
			// HTTP.UTF_8);
			this.clienteHttp = new DefaultHttpClient(httpParameters);
		}
		return this.clienteHttp;
	}
}
