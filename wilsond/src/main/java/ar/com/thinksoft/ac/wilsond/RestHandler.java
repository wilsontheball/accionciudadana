package ar.com.thinksoft.ac.wilsond;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.HttpMethods;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;
import com.google.gson.Gson;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;
import ar.com.thinksoft.ac.intac.utils.classes.UsuarioMovil;
import ar.com.thinksoft.ac.wilsond.log.LogManager;
import ar.com.thinksoft.ac.wilsond.reclamo.ReclamoAndrac;
import ar.com.thinksoft.ac.wilsond.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.wilsond.usuario.UsuarioAndrac;
import ar.com.thinksoft.ac.wilsond.usuario.UsuarioManager;

/**
 * Maneja los pedidos de distintos webservicies.
 * 
 * @since 08-10-2011
 * @author Paul
 */
public class RestHandler extends AbstractHandler {

	String funcion = "";
	String nick = "";
	String pass = "";

	public void handle(String target, HttpServletRequest baseRequest,
			HttpServletResponse response, int dispatch) throws IOException,
			ServletException {

		StringTokenizer tokens = new StringTokenizer(
				baseRequest.getRequestURI(), "/");
		if (tokens.hasMoreElements()) {
			funcion = (String) tokens.nextElement();
		}
		if (tokens.hasMoreElements()) {
			nick = (String) tokens.nextElement();
		}
		if (tokens.hasMoreElements()) {
			pass = (String) tokens.nextElement();
		}
		
		if(UsuarioManager.getInstance().validarUsuario(nick,pass) || funcion.equalsIgnoreCase(FuncionRest.POSTUSUARIO)){
			if (baseRequest.getMethod().equalsIgnoreCase(HttpMethods.GET)) {
				try {
					atenderGet(baseRequest, response);
				} catch (Exception e) {
					// TODO error en get
					LogManager.getInstance(RestHandler.class).error("No se pude realizar el GET. Detalle: " + e.getMessage());
				}
			} else {
				if (baseRequest.getMethod().equalsIgnoreCase(HttpMethods.POST)) {
					try {
						atenderPost(baseRequest);
					} catch (Exception e) {
						// TODO error en post
						LogManager.getInstance(RestHandler.class).error("No se pude realizar el POST. Detalle: " + e.getMessage());
					}
				} else {
					// TODO error
					LogManager.getInstance(RestHandler.class).error("Función desconocida. No es un GET ni un POST. Contacte al Soporte Técnico.");
				}
			}
		}else{
			//TODO: error validacion de usuario
			LogManager.getInstance(RestHandler.class).error("El usuario y/o contraseña son incorrectos.");
		}
	}

	/**
	 * Atiende los pedidos de POST.
	 * 
	 * @since 08-10-2011
	 * @author Paul
	 * @param baseRequest
	 * @throws Exception 
	 */
	private void atenderPost(HttpServletRequest baseRequest) throws Exception {

		if (funcion.equalsIgnoreCase(FuncionRest.POSTRECLAMO)) {
			InputStream instream = baseRequest.getInputStream();
			InputStreamReader isReader = new InputStreamReader(instream);
			Gson gson = new Gson();
			ReclamoAndrac reclamoAndrac= gson.fromJson(isReader, ReclamoAndrac.class);
			IUsuario user = UsuarioManager.getInstance().getUsuarioFromDB(nick);
			IReclamo reclamo = ReclamoManager.getInstance().toReclamoInt(reclamoAndrac,user);
			ReclamoManager.getInstance().guardarReclamo(reclamo);
			
		} else if (funcion.equalsIgnoreCase(FuncionRest.POSTUSUARIO)) {
			InputStream instream = baseRequest.getInputStream();
			InputStreamReader isReader = new InputStreamReader(instream);
			Gson gson = new Gson();
			IUsuario usuario = UsuarioManager.getInstance().toUsuarioInt(gson.fromJson(isReader, UsuarioAndrac.class));
			UsuarioManager.getInstance().guardarUsuario(usuario);
			
		} else {
			throw new Exception("Función desconocida.");
		}
	}

	private void atenderGet(HttpServletRequest baseRequest,	HttpServletResponse response) throws Exception {

		Request req = (baseRequest instanceof Request ? (Request) baseRequest: HttpConnection.getCurrentConnection().getRequest());

		if (funcion.equalsIgnoreCase(FuncionRest.GETRECLAMOS)) {
			atenderReclamos(response, req);

		} else if (funcion.equalsIgnoreCase(FuncionRest.GETPERFIL)) {
			atenderPerfilUsuario(response, req);

		} else {
			throw new Exception("funcion desconocida");
		}

	}

	private void atenderPerfilUsuario(HttpServletResponse response, Request req)throws Exception {
		try{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		UsuarioMovil usr = UsuarioManager.getInstance().getPerfil(nick);

		response.getWriter().write(new Gson().toJson(usr));
		req.setHandled(true);
		}catch(Exception e){
			throw new Exception("No se pudo obtener el perfil del usuario. Detalle: " + e.getMessage());
		}
	}

	private void atenderReclamos(HttpServletResponse response, Request req)	throws Exception {
		try{
		List<ReclamoAndrac> listaReclamosAndrac = ReclamoManager.getInstance()
				.toReclamoAndrac(ReclamoManager.getInstance().obtenerTodosReclamos(nick));

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		response.getWriter().write(new Gson().toJson(listaReclamosAndrac));
		req.setHandled(true);
		}catch(Exception e){
			throw new Exception("No se pudo obtener los reclamos del usuario. Detalle: "+e.getMessage());
		}
	}

}