package ar.com.thinksoft.ac.andrac.contexto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import ar.com.thinksoft.ac.andrac.adapter.ReclamoItem;
import ar.com.thinksoft.ac.andrac.dominio.Imagen;
import ar.com.thinksoft.ac.andrac.dominio.Reclamo;
import ar.com.thinksoft.ac.andrac.dominio.Usuario;

import com.google.gson.Gson;

/**
 * Abstrae la conexion remota a la base de datos
 * 
 * @since 27-09-2011
 * @author Paul
 */
public class Repositorio {

	byte[] imagenBytes = null;
	private Usuario perfilUsuario = null;
	private List<Reclamo> reclamos = null;
	private String nombreUsuario = "nick";
	private String pass = "pass";

	/**
	 * Devuelve la URL del servidor.
	 * 
	 * @since 27-09-2011
	 * @author Paul
	 * @return URL del servidor REST
	 */
	public String getSrvUrl() {
		// return "http://10.24.192.183:6060";
		// return "http://192.168.1.108:6060";
		return "http://96.126.102.85:6060";
	}

	/**
	 * Devuelve un objeto Usuario desde la base de datos
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @param nick
	 *            nombre del usuario
	 * @param pass
	 *            contrasenia del usuario
	 * @return Usuario o null si la operacion fallo
	 */
	public Usuario getUsuario(String nick, String pass) {
		// TODO no esta implementada la conexion a la BD
		return null;
	}

	/**
	 * Valida un nick y password
	 * 
	 * @param nick
	 * @param pass
	 * @return
	 */
	public boolean validarUsuario(String nick, String pass) {
		// TODO Es solo para probar! No esta implementada la conexion a la BD
		if (nick.equals("0") && pass.equals("0")) {
			return false;
		}
		return true;
	}

	/**
	 * Registra a un nuevo usuario en el sistema.
	 * 
	 * @since 25-07-2011
	 * @author Paul
	 * 
	 * @param nombre
	 * @param apellido
	 * @param nick
	 * @param dni
	 * @param mail
	 * @param telefono
	 * @param password
	 * @return <Code>true</Code> si el usuario se registro correctamente o
	 *         <Code>false</Code> si el usuario no se pudo registrar.
	 */
	public boolean registrarUsuario(String nombre, String apellido,
			String nick, String dni, String mail, String telefono,
			String password) {
		// TODO falta implementar
		return true;
	}

	/**
	 * Obtiene los reclamos realizados del usuario listos para mostrar.
	 * 
	 * @since 25-09-2011
	 * @author Paul
	 * @return Array de reclamos listo para mostrar.
	 */
	public ReclamoItem[] getReclamosUsuario() {
		ArrayList<ReclamoItem> listaItems = new ArrayList<ReclamoItem>();

		if (this.reclamos != null) {
			for (Reclamo rec : this.reclamos) {
				listaItems.add(new ReclamoItem(rec.getEstadoDescripcion(), rec
						.getTipoIncidente(), rec.getCalleIncidente() + " "
						+ rec.getAlturaIncidente(), rec.getFechaReclamo()));
			}
		}
		ReclamoItem[] arrayItems = new ReclamoItem[listaItems.size()];

		// Se hace asi por que falla el metodo de conversion de lista.
		int i = 0;
		for (ReclamoItem item : listaItems) {
			arrayItems[i++] = item;
		}
		return arrayItems;
	}

	/**
	 * Guarda los reclamos realizados del usuario tal como llegaron del
	 * servidor.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 * @param reclamos
	 *            Array de reclamos tal como llego de servidor.
	 */
	public void setReclamosUsuario(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}

	public boolean publicarReclamoDireccion(String tipo, String barrio,
			String calle, String altura, String observacion) {
		// TODO falta revisar si contentType es jpeg!!!!!!!!
		Imagen imagen = new Imagen(this.getImagen(), "jpeg", "prueba");

		// TODO falta implementar obtencion de coordenada
		String latitud = "";
		String longitud = "";
		// TODO falta implementar obtencion de comuna
		String comuna = "";
		// TODO falta implementar obtencion de fecha
		String fecha = "";
		String fechaModificacion = "";
		Reclamo reclamo = new Reclamo(calle, altura, latitud, longitud, tipo,
				fecha, fechaModificacion, this.getNombreUsuario(), observacion,
				barrio, comuna, imagen);

		this.enviarReclamo(reclamo);

		return true;
	}

	private void enviarReclamo(Reclamo reclamo) {

		Gson gson = new Gson();
		String json = gson.toJson(reclamo);

		HttpResponse responce;
		try {
			responce = this.doPost(this.getSrvUrl(), new JSONObject(json));
			String temp = EntityUtils.toString(responce.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if (temp.compareTo("SUCCESS")==0)
		// {
		// Toast.makeText(this, "Sending complete!", Toast.LENGTH_LONG).show();
		// }

	}

	public HttpResponse doPost(String url, JSONObject c)
			throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		HttpEntity entity;
		StringEntity s = new StringEntity(c.toString());
		s.setContentEncoding((Header) new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json"));
		entity = s;
		request.setEntity(entity);
		HttpResponse response;
		response = httpclient.execute(request);
		return response;
	}

	/**
	 * Se conecta a Wilsond para obtener reclamos hechos por un usuario.
	 * 
	 * @since 25-09-2011
	 * @author Paul
	 * @param usuario
	 *            Nick de usuario.
	 * @return Lista de reclamos hecho por un usuario.
	 */
	// private List<Reclamo> obtenerReclamos(String usuario) {
	//
	// try {
	// DefaultHttpClient httpClient = new DefaultHttpClient();
	// HttpGet method = new HttpGet(this.getSrvUrl() + "/reclamos");
	// HttpResponse httpResponse = httpClient.execute(method);
	// InputStream is = httpResponse.getEntity().getContent();
	// Gson gson = new Gson();
	// Reader reader = new InputStreamReader(is);
	// Type collectionType = new TypeToken<List<Reclamo>>() {
	// }.getType();
	// return gson.fromJson(reader, collectionType);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	public boolean publicarReclamoGPS(String tipo, String barrio,
			String latitud, String longitud, String observacion) {
		// TODO falta implementar
		return true;
	}

	/**
	 * Almacen para la foto. De esta forma no hace falta un archivo.
	 * 
	 * @param imagen
	 */
	public void setImagen(byte[] imagen) {
		this.imagenBytes = imagen;
	}

	public byte[] getImagen() {
		return this.imagenBytes;
	}

	public Usuario getPerfilUsuario() {
		return this.perfilUsuario;
	}

	public void setPerfilUsuario(Usuario perfil) {
		this.perfilUsuario = perfil;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String usuario) {
		this.nombreUsuario = usuario;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		// Aseguro que no devuelva null;
		if (this.pass == null) {
			return "";
		} else {
			return this.pass;
		}
	}
}
