package ar.com.thinksoft.ac.andrac.contexto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import ar.com.thinksoft.ac.andrac.dominio.Imagen;
import ar.com.thinksoft.ac.andrac.dominio.Reclamo;
import ar.com.thinksoft.ac.andrac.dominio.Usuario;
import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.EnumTipoReclamo;

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
	private List<Reclamo> reclamosGuardados = null;
	private String nick = null;
	private String pass = null;
	private Reclamo reclamoAEnviar = null;

	/**
<<<<<<< HEAD
=======
	 * Devuelve la URL del servidor.
	 * 
	 * @since 27-09-2011
	 * @author Paul
	 * @return URL del servidor REST
	 */
	public String getSrvUrl() {
		 return "http://192.168.2.7:6060";
	}

	/**
>>>>>>> mati/master
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
	public Reclamo[] getReclamosUsuario() {
		Reclamo[] arrayReclamos = new Reclamo[this.reclamos.size()];

		// Se hace asi por que falla el metodo de conversion de lista.
		int i = 0;
		for (Reclamo rec : this.reclamos) {
			arrayReclamos[i++] = rec;
		}
		return arrayReclamos;
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

	/**
	 * Obtiene los reclamos guardados en el celular.
	 * 
	 * @since 04-10-2011
	 * @author Hernan
	 * @return Array de reclamos listo para mostrar.
	 */
	public Reclamo[] getReclamosGuardados() {

		// TODO Falta implementar como levantar los reclamos guardados!!!!!!!!
		Reclamo[] arrayReclamosGuardados = new Reclamo[this.reclamosGuardados
				.size()];
		// Se hace asi por que falla el metodo de conversion de lista.
		int i = 0;
		for (Reclamo rec : this.reclamosGuardados) {
			arrayReclamosGuardados[i++] = rec;
		}
		return arrayReclamosGuardados;
	}

	public void setReclamosGuardados(List<Reclamo> reclamos) {
		this.reclamosGuardados = reclamos;
	}

	private void setReclamoAEnviar(Reclamo reclamoAEnviar) {
		this.reclamoAEnviar = reclamoAEnviar;
	}

	public Reclamo getReclamoAEnviar() {
		// XXX Probado un Mock!!!!!!!!!!!!!!!!!!!!!!!

		Reclamo rec1 = new Reclamo("Cabildo", "145", "latitud...",
				"longitud...", EnumTipoReclamo.accesibilidad.getTipo(),
				"ayer.......", "hoy........", "pepe", "Lalala",
				EnumBarriosReclamo.Belgrano.getBarrio(), null);
		return rec1;
		// XXX Probado un Mock!!!!!!!!!!!!!!!!!!!!!!!
	}

	public void publicarReclamoDireccion(String tipo, String barrio,
			String calle, String altura, String observacion) {
		// TODO falta revisar si contentType es jpeg!!!!!!!!
		Imagen imagen = new Imagen(this.getImagen(), "jpeg", "prueba");
		String fecha = this.getFecha();

		// TODO falta implementar obtencion de coordenada
		String latitud = "zaraza1";
		String longitud = "zaraza2";

		Reclamo reclamo = new Reclamo(calle, altura, latitud, longitud, tipo,
				fecha, fecha, this.getNick(), observacion, barrio, imagen);
		this.setReclamoAEnviar(reclamo);
	}

	// private void enviarReclamo(Reclamo reclamo) {
	//
	// Gson gson = new Gson();
	// String json = gson.toJson(reclamo);
	//
	// HttpResponse responce;
	// try {
	// responce = this.doPost(this.getSrvUrl(), new JSONObject(json));
	// String temp = EntityUtils.toString(responce.getEntity());
	// } catch (ClientProtocolException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	// if (temp.compareTo("SUCCESS")==0)
	// {
	// Toast.makeText(this, "Sending complete!", Toast.LENGTH_LONG).show();
	// }
	//
	// }

	// public HttpResponse doPost(String url, JSONObject c)
	// throws ClientProtocolException, IOException {
	// HttpClient httpclient = new DefaultHttpClient();
	// HttpPost request = new HttpPost(url);
	// HttpEntity entity;
	// StringEntity s = new StringEntity(c.toString());
	// s.setContentEncoding((Header) new BasicHeader(HTTP.CONTENT_TYPE,
	// "application/json"));
	// entity = s;
	// request.setEntity(entity);
	// HttpResponse response;
	// response = httpclient.execute(request);
	// return response;
	// }

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
			double latitud, double longitud, String observacion) {
		// TODO falta implementar

		// XXX Probando Goeocoder....
		Geocoder geocoder = new Geocoder(null);
		try {
			Address dir = geocoder.getFromLocation(latitud, longitud, 1).get(0);
			Log.d(this.getClass().getName(),
					"Direccion es: " + dir.getAdminArea());
		} catch (IOException e) {
			Log.d(this.getClass().getName(), "Fallo Geocoder" + e.toString());
		}
		// XXX Hasta aqui probando Goeocoder....

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

	public String getNick() {
		return this.nick;
	}

	public void setNick(String usuario) {
		this.nick = usuario;
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

	public String getFecha() {
		Date date = new java.util.Date();
		SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"dd/MM/yyyy");
		return formatter.format(date);
	}
}
