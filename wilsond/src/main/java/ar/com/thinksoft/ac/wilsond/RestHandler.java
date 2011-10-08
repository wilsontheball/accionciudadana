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
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;
import ar.com.thinksoft.ac.intac.utils.classes.UsuarioMovil;
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

		if (baseRequest.getMethod().equalsIgnoreCase(HttpMethods.GET)) {
			try {
				atenderGet(baseRequest, response);
			} catch (Exception e) {
				// TODO error
				e.printStackTrace();
			}
		} else {
			if (baseRequest.getMethod().equalsIgnoreCase(HttpMethods.POST)) {
				atenderPost(baseRequest);
			} else {
				// TODO error
			}
		}
	}

	/**
	 * Atiende los pedidos de POST.
	 * 
	 * @since 08-10-2011
	 * @author Paul
	 * @param baseRequest
	 * @throws IOException
	 */
	private void atenderPost(HttpServletRequest baseRequest) throws IOException {

		if (funcion.equalsIgnoreCase(FuncionRest.POSTRECLAMO)) {
			InputStream instream = baseRequest.getInputStream();
			InputStreamReader isReader = new InputStreamReader(instream);
			Gson gson = new Gson();
			IReclamo reclamo = ReclamoManager.getInstance().toReclamoInt(
					gson.fromJson(isReader, ReclamoAndrac.class));
			ReclamoManager.getInstance().guardarReclamo(reclamo);
		} else if (funcion.equalsIgnoreCase(FuncionRest.POSTUSUARIO)) {
			InputStream instream = baseRequest.getInputStream();
			InputStreamReader isReader = new InputStreamReader(instream);
			Gson gson = new Gson();
			gson.fromJson(isReader, UsuarioAndrac.class);
			// TODO Implementar registro de usuario
		} else {
			// TODO error... nada conocido
		}
	}

	private void atenderGet(HttpServletRequest baseRequest,
			HttpServletResponse response) throws Exception {

		Request req = (baseRequest instanceof Request ? (Request) baseRequest
				: HttpConnection.getCurrentConnection().getRequest());

		if (funcion.equalsIgnoreCase(FuncionRest.GETRECLAMOS)) {
			atenderReclamos(response, req);

		} else if (funcion.equalsIgnoreCase(FuncionRest.GETPERFIL)) {
			atenderPerfilUsuario(response, req);

		} else {
			throw new Exception("funcion desconocida");
		}

	}

	private void atenderPerfilUsuario(HttpServletResponse response, Request req)
			throws Exception {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		UsuarioMovil usr = UsuarioManager.getInstance().getPerfil("mati");

		response.getWriter().write(new Gson().toJson(usr));
		req.setHandled(true);
	}

	private void atenderReclamos(HttpServletResponse response, Request req)
			throws IOException {
		// TODO: esto debe ser del ciudadano, no TODOS
		List<ReclamoAndrac> listaReclamosAndrac = ReclamoManager.getInstance()
				.toReclamoAndrac(
						ReclamoManager.getInstance().obtenerTodosReclamos());

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		response.getWriter().write(new Gson().toJson(listaReclamosAndrac));
		req.setHandled(true);
	}

}