package ar.com.thinksoft.ac.wilsond;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.server.HttpConnection;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

import com.google.gson.Gson;

/**
 * Maneja los pedidos de distintos webservicies.
 * 
 * @since 28-09-2011
 * @author Paul
 */
public class RestHandler extends AbstractHandler

// TODO No es funcional!!! Falta implementar el resto de las
// funcionalidades!!!!!!!
{
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String funcion = null;
		String nick = null;
		String pass = null;

		if (baseRequest.getMethod().equalsIgnoreCase(HttpMethods.GET)) {
			// Atiende los GET
			Request req = (request instanceof Request ? (Request) request
					: HttpConnection.getCurrentConnection().getRequest());
			try {
				StringTokenizer tokens = new StringTokenizer(
						request.getRequestURI(), "/");
				if (tokens.hasMoreElements()) {
					funcion = (String) tokens.nextElement();
				}
				if (tokens.hasMoreElements()) {
					nick = (String) tokens.nextElement();
				}
				if (tokens.hasMoreElements()) {
					pass = (String) tokens.nextElement();
				}

				// String funcion = request.getRequestURI().substring(1);

				System.out.println("[" + new Date().toString() + "] " + funcion
						+ "::" + nick + "::" + pass);

				if (funcion.equalsIgnoreCase(FuncionRest.GETRECLAMOS)) {
					// Atiende pedido de reclamos de usuario.

					// XXX por ahora siempre devuelve FRUTA!!!!!!!!!!!!!!!!
					List<Reclamo> list = Repositorio.getInstancia()
							.getReclamos("usuario");
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					// XXX hasta aca FRUTA!!!!!!!!!!!!!!!!

					response.getWriter().write(new Gson().toJson(list));
					// response.setStatus(HttpServletResponse.SC_OK);
					req.setHandled(true);
				} else if (funcion.equalsIgnoreCase(FuncionRest.GETPERFIL)) {
					// Atiende pedido de perfil de usuario.
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");

					// XXX por ahora siempre devuelve FRUTA!!!!!!!!!!!!!!!!
					Usuario usr = new Usuario();
					usr.setApellido("Fulano");
					usr.setContrasenia("123");
					usr.setDni("10000000");
					usr.setMail("pepito@hot.com");
					usr.setNombre("Pepe");
					usr.setNombreUsuario("pepe");
					usr.setTelefono("456123");
					// XXX hasta aqui FRUTA!!!!!!!!!!!!!!!!

					response.getWriter().write(new Gson().toJson(usr));
					req.setHandled(true);
				} else {
					System.out.print("[" + new Date().toString() + "] "
							+ "Funcion Desconocida:");
					System.out.println("!= " + FuncionRest.GETRECLAMOS);
					System.out.println("!= " + FuncionRest.GETPERFIL);
					req.setHandled(false);
				}

			} catch (Exception e) {
				e.printStackTrace();
				req.setHandled(false);
			}

		} else {
			System.out.println("No es un get. Es: " + baseRequest.getMethod());
		}
	}
}