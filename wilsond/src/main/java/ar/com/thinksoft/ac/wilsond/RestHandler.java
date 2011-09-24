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

public class RestHandler extends AbstractHandler

{
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// response.setContentType("text/html;charset=utf-8");
		// response.setStatus(HttpServletResponse.SC_OK);
		// baseRequest.setHandled(true);
		// response.getWriter().println("<h1>Hello World</h1>");

		// List<Data> list = (new DAO()).list();
		// response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
		// response.getWriter().write(new Gson().toJson(list));

		Request req = (request instanceof Request ? (Request) request
				: HttpConnection.getCurrentConnection().getRequest());
		try {
			// String methodName = request.getRequestURI().substring(1);
			List<Data> list = (new DAO()).list();
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