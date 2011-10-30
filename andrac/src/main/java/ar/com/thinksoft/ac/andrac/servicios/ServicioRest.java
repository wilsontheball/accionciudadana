package ar.com.thinksoft.ac.andrac.servicios;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.andrac.dominio.Reclamo;
import ar.com.thinksoft.ac.andrac.dominio.Usuario;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Se encarga de correr en 2do plano todas las funciones de conexion a servidor.
 * 
 * @since 16-10-2011
 * @author Paul
 */
public class ServicioRest extends IntentService {

	public static final int RUN = 0;
	public static final int FIN = 1;
	public static final int ERROR = -1;
	public static final String FUN = "funcion";
	public static final String HTTP_COD = "codigo";
	public static final String REC = "receptor";

	// Almacena temporalmente la respuesta.
	HttpResponse respuestaHttp = null;

	public ServicioRest() {
		super("ServicioRest");
	}

	/**
	 * Atiende la creacion de un servicio.
	 * 
	 * @since 16-10-2011
	 * @author Paul
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		final ResultReceiver receptor = intent.getParcelableExtra(REC);
		String funcion = intent.getStringExtra(FUN);
		Log.d(this.getClass().getName(), "Funcion: " + funcion);

		Bundle bundle = new Bundle();
		int codigoHttp = -1;
		int retorno = ERROR;
		try {
			bundle.putString(FUN, funcion);
			receptor.send(RUN, bundle);
			if (FuncionRest.GETRECLAMOS.equals(funcion)) {
				// Funcion GET Reclamos.
				this.getReclamos(this.getRepo().getUrlServer(), this.getRepo()
						.getNick(), this.getRepo().getPass());
			} else if (FuncionRest.GETPERFIL.equals(funcion)) {
				// Funcion GET Perfil.
				this.getPerfil(this.getRepo().getUrlServer(), this.getRepo()
						.getNick(), this.getRepo().getPass());
			} else if (FuncionRest.POSTRECLAMO.equals(funcion)) {
				// Funcion POST Reclamo.
				this.setRespuestaHttp(this.postReclamo(this.getRepo()
						.getUrlServer(), this.getRepo().getNick(), this
						.getRepo().getPass(), this.getRepo()
						.getReclamoAEnviar()));
			} else if (FuncionRest.POSTUSUARIO.equals(funcion)) {
				// Funcion POST Usuario.
				this.setRespuestaHttp(this.postUsuario(this.getRepo()
						.getUrlServer(), this.getRepo().getUsuarioARegistrar()));
			} else {
				// Funcion desconocida.
				Log.e(this.getClass().getName(), "Funcion desconocida!");
				throw new Exception("Funcion desconocida!");
			}
			retorno = FIN;
		} catch (ClientProtocolException e) {
			retorno = ERROR;
			Log.e(this.getClass().getName(), "Error HTTP: " + e.toString());
		} catch (IOException e) {
			retorno = ERROR;
			Log.e(this.getClass().getName(), e.toString());
		} catch (Exception e) {
			retorno = ERROR;
			Log.e(this.getClass().getName(), e.toString());
		} finally {
			codigoHttp = this.getRespuestaHttp() != null ? this
					.getRespuestaHttp().getStatusLine().getStatusCode() : -1;
			bundle.putInt(HTTP_COD, codigoHttp);
			bundle.putString(FUN, funcion);
			receptor.send(retorno, bundle);
			Log.e(this.getClass().getName(), "STOP. Retorno: " + retorno
					+ " CodigoHTTP: " + codigoHttp);
			this.stopSelf();
		}
	}

	@Override
	public void onDestroy() {
		Log.i(this.getClass().getName(), "Llamaron a onDestroy()...");
		super.onDestroy();
	}

	/**
	 * Obtiene los reclamos del servidor Rest.
	 * 
	 * @since 16-10-2011
	 * @author Paul
	 * @param url
	 *            URL del servidor.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private void getReclamos(String url, String nick, String pass)
			throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet method = new HttpGet(url + "/" + FuncionRest.GETRECLAMOS + "/"
				+ nick + "/" + pass);
		this.setRespuestaHttp(httpClient.execute(method));
		if (this.getRespuestaHttp().getStatusLine().getStatusCode() != 200) {
			throw new ClientProtocolException();
		}
		InputStream is = this.getRespuestaHttp().getEntity().getContent();
		Gson gson = new Gson();
		Reader reader = new InputStreamReader(is);
		Type collectionType = new TypeToken<List<Reclamo>>() {
		}.getType();
		List<Reclamo> reclamos = gson.fromJson(reader, collectionType);
		this.getRepo().setReclamosUsuario(reclamos);
	}

	/**
	 * Obtiene perfil de usuario del servidor Rest.
	 * 
	 * @since 16-10-2011
	 * @author Paul
	 * @param url
	 *            URL del servidor.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private void getPerfil(String url, String nick, String pass)
			throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet method = new HttpGet(url + "/" + FuncionRest.GETPERFIL + "/"
				+ nick + "/" + pass);
		this.setRespuestaHttp(httpClient.execute(method));
		Log.i(this.getClass().getName(), "HTTP Respuesta: "
				+ this.getRespuestaHttp().getStatusLine().toString());
		if (this.getRespuestaHttp().getStatusLine().getStatusCode() != 200) {
			throw new ClientProtocolException();
		}
		InputStream is = this.getRespuestaHttp().getEntity().getContent();
		Gson gson = new Gson();
		Reader reader = new InputStreamReader(is);
		Usuario perfil = gson.fromJson(reader, Usuario.class);
		this.getRepo().setPerfilUsuario(perfil);
	}

	/**
	 * Manda un reclamo al servidor Rest.
	 * 
	 * @since 10-10-2011
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

		// XXX Completa datos faltantes
		// this.completarUbicacion(reclamo);

		StringEntity entidad = new StringEntity(new Gson().toJson(reclamo));
		Header header = new BasicHeader(HTTP.CONTENT_TYPE, "application/json");
		entidad.setContentEncoding(header);

		HttpPost post = new HttpPost(url + "/" + FuncionRest.POSTRECLAMO + "/"
				+ nick + "/" + pass);
		post.setEntity(entidad);
		return new DefaultHttpClient().execute(post);
	}

	/**
	 * Manda un usuario al servidor Rest. No se manda nick ni pass.
	 * 
	 * @since 08-10-2011
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
		StringEntity entidad = new StringEntity(new Gson().toJson(usuario));
		Header header = new BasicHeader(HTTP.CONTENT_TYPE, "application/json");
		entidad.setContentEncoding(header);

		HttpPost post = new HttpPost(url + "/" + FuncionRest.POSTUSUARIO);
		post.setEntity(entidad);
		return new DefaultHttpClient().execute(post);
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
	 * Completa ubicacion de incidente con Geocoding segun lo que falta.
	 * 
	 * @since 08-10-2011
	 * @author Paul
	 * @param reclamo
	 * @throws IOException
	 */
	private void completarUbicacion(Reclamo reclamo) throws IOException {

		Address direccion = null;
		if ((reclamo.getLatitudIncidente() != null)
				&& (reclamo.getLatitudIncidente() != null)) {
			// Completa calle y altura.
			String calle = null;
			String altura = null;
			direccion = this.coordenadaADireccion(
					reclamo.getLatitudIncidente(),
					reclamo.getLongitudIncidente());
			String lineaDir = direccion.getAddressLine(0);
			StringTokenizer tokens = new StringTokenizer(lineaDir, " ");
			if (tokens.hasMoreElements()) {
				// altura = (String) tokens.nextElement();
				altura = direccion.getSubThoroughfare();
			}
			if (tokens.hasMoreElements()) {
				// calle = (String) tokens.toString();
				calle = direccion.getThoroughfare();
			}
			Log.d(this.getClass().getName(), "GEO obtuvo direccion: " + calle
					+ " " + altura);
			reclamo.setAlturaIncidente(altura);
			reclamo.setCalleIncidente(calle);
		} else if ((reclamo.getCalleIncidente() != null)
				&& (reclamo.getAlturaIncidente() != null)) {
			// Completa latitud y longitud.
			direccion = this.direccionACoordenada(reclamo.getCalleIncidente(),
					reclamo.getAlturaIncidente());
			Log.d(this.getClass().getName(), "GEO obtuvo coordenada: "
					+ direccion.getLatitude() + ", " + direccion.getLongitude());
			reclamo.setLatitudIncidente(direccion.getLatitude() + "");
			reclamo.setLongitudIncidente(direccion.getLongitude() + "");
		} else {
			// No se puede completar.
			Log.e(this.getClass().getName(), "Reclamo incompleto!");
			throw new IOException("Reclamo incompleto!");
		}
	}

	/**
	 * Convierte una coordenada a una direccion con Geocoding.
	 * 
	 * @since 08-10-2011
	 * @author Paul
	 * @param latitud
	 * @param longitud
	 * @return Direccion obtenida.
	 * @throws IOException
	 */
	private Address coordenadaADireccion(String unaLatitud, String unaLongitud)
			throws IOException {

		Double latitud = new Double(unaLatitud);
		Double longitud = new Double(unaLongitud);
		// XXX Probando Goeocoder....
		Geocoder geocoder = new Geocoder(ServicioRest.this);
		return geocoder.getFromLocation(latitud, longitud, 1).get(0);
		// XXX Hasta aqui probando Goeocoder....

	}

	/**
	 * Convierte una direccion a una coordenada con Geocoding.
	 * 
	 * @since 08-10-2011
	 * @author Paul
	 * @param calle
	 * @param altura
	 * @return
	 * @throws IOException
	 */
	private Address direccionACoordenada(String calle, String altura)
			throws IOException {

		// XXX Probando Goeocoder....
		Geocoder geocoder = new Geocoder(ServicioRest.this);
		return geocoder.getFromLocationName(
				altura + " " + calle + ", " + "Buenos Aires, Argentina", 0)
				.get(0);
		// XXX Hasta aqui probando Goeocoder....
	}

	/* ************ Getters y Setters ************* */

	private HttpResponse getRespuestaHttp() {
		return respuestaHttp;
	}

	private void setRespuestaHttp(HttpResponse respuestaHttp) {
		this.respuestaHttp = respuestaHttp;
	}
}
