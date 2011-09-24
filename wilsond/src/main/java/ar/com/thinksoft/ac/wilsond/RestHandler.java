package ar.com.thinksoft.ac.wilsond;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.HttpConnection;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

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

		Request req = (request instanceof Request ? (Request) request
				: HttpConnection.getCurrentConnection().getRequest());
		try {
			// String methodName = request.getRequestURI().substring(1);
			List<Reclamo> list = Repositorio.getInstancia().getReclamos(
					"usuario");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(list));
			// response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setHandled(true);
	}
}