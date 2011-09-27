package ar.com.thinksoft.ac.wilsond;

import java.io.IOException;
import java.util.List;

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
 * @since 24-09-2011
 * @author Paul
 */
public class RestHandler extends AbstractHandler

// TODO No es funcional!!! Por ahora simpre devuelve reclamos. Falta implementar
// el resto de las funcionalidades!!!!!!!
{
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if (baseRequest.getMethod().equalsIgnoreCase(HttpMethods.GET)) {
			// Atiende los GET
			Request req = (request instanceof Request ? (Request) request
					: HttpConnection.getCurrentConnection().getRequest());
			try {
				String methodName = request.getRequestURI().substring(1);
				System.out.println(methodName);
				if (methodName.equalsIgnoreCase(FuncionRest.RECLAMOS)) {
					// Atiende pedido de reclamos de usuario.
					// XXX por ahora siempre devuelve FRUTA!!!!!!!!!!!!!!!!
					System.out.println("RECLAMOS");
					List<Reclamo> list = Repositorio.getInstancia()
							.getReclamos("usuario");
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(new Gson().toJson(list));
					// response.setStatus(HttpServletResponse.SC_OK);
					req.setHandled(true);
				} else if (methodName.equalsIgnoreCase(FuncionRest.PERFIL)) {
					// Atiende pedido de perfil de usuario.
					// XXX por ahora siempre devuelve FRUTA!!!!!!!!!!!!!!!!
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					Usuario usr = new Usuario();
					usr.setApellido("Fulano");
					usr.setContrasenia("123");
					usr.setDni(1000111L);
					usr.setMail("pepito@hot.com");
					usr.setNombre("Pepe");
					usr.setNombreUsuario("pepe");
					usr.setTelefono(1540405050L);
					response.getWriter().write(new Gson().toJson(usr));
					System.out.println("PERFIL");
					req.setHandled(true);
				} else if (methodName.equalsIgnoreCase(FuncionRest.VALIDAR)) {
					// Atiende validacion de user-pass.
					// XXX por ahora siempre devuelve TRUE!!!!!!!!!!!!!!!!
					System.out.println("VALIDAR");
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(new Gson().toJson(true));
					req.setHandled(true);
				} else {
					System.out.println("Funcion Desconocida:");
					System.out.println("!= " + FuncionRest.RECLAMOS);
					System.out.println("!= " + FuncionRest.PERFIL);
					System.out.println("!= " + FuncionRest.VALIDAR);
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