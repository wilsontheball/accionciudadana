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
import org.mortbay.jetty.handler.DefaultHandler;

import com.google.gson.Gson;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;
import ar.com.thinksoft.ac.intac.utils.classes.UsuarioMovil;
import ar.com.thinksoft.ac.wilsond.log.LogManager;
import ar.com.thinksoft.ac.wilsond.mail.MailWilsonD;
import ar.com.thinksoft.ac.wilsond.reclamo.ReclamoAndrac;
import ar.com.thinksoft.ac.wilsond.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.wilsond.usuario.UsuarioAndrac;
import ar.com.thinksoft.ac.wilsond.usuario.UsuarioManager;

/**
 * Maneja las conexiones al servidor mediante webservice.
 * 
 * @since 16-10-2011
 * @author Paul
 */
public class RestHandler extends DefaultHandler {

	String funcion = "";
	String nick = "";
	String pass = "";
	
	public RestHandler() {
		this.setServeIcon(false);
	}

	/**
	 * Atiende una conexion y respondo el pedido o devuelve mensaje de error.
	 * 
	 * @since 16-10-2011
	 * @author Paul
	 */
	@SuppressWarnings("static-access")
	public void handle(String target, HttpServletRequest baseRequest,
			HttpServletResponse response, int dispatch) throws IOException,
			ServletException {
		
		try {
			
			
			StringTokenizer tokens = new StringTokenizer(
					baseRequest.getRequestURI(), "/");
			if (tokens.hasMoreElements()) {
				funcion = (String) tokens.nextElement();
			}
			if (tokens.hasMoreElements()) {
				nick = (String) tokens.nextElement();
				nick = nick.toLowerCase().replace(" ", "");
			}
			if (tokens.hasMoreElements()) {
				pass = (String) tokens.nextElement();
			}

			
			if(this.funcion.equals("favicon.ico")){

				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				Request request = this.getRequest(baseRequest);
				response.setStatus(response.SC_OK);
				request.setHandled(true);
				return;
			
			}
			
			
			if (UsuarioManager.getInstance().validarUsuario(nick, pass)
					|| funcion.equalsIgnoreCase(FuncionRest.POSTUSUARIO)) {
				if (baseRequest.getMethod().equalsIgnoreCase(HttpMethods.GET)) {
					try {
						atenderGet(baseRequest, response);
					} catch (Exception e) {
						
						
						if(funcion.equals("favicon.ico")) 
						
						this.responderError(
								HttpServletResponse.SC_BAD_REQUEST,
								"No se pudo realizar el GET. Detalle: "
										+ e.toString(), baseRequest, response);
						LogManager.getInstance(RestHandler.class).error(
								"No se pudo realizar el GET. Detalle: "
										+ e.toString());
						System.out.println("No se pudo realizar el GET. Detalle: "
								+ e.toString());
					}
				} else {
					if (baseRequest.getMethod().equalsIgnoreCase(
							HttpMethods.POST)) {
						try {
							atenderPost(baseRequest);
						} catch (Exception e) {
							
							this.responderError(
									HttpServletResponse.SC_BAD_REQUEST,
									"No se pudo realizar el POST. Detalle: "
											+ e.toString(), baseRequest,
									response);
							LogManager.getInstance(RestHandler.class).error(
									"No se pudo realizar el POST. Detalle: "
											+ e.toString());
							System.out.println("No se pudo realizar el POST. Detalle: "
									+ e.toString());
						}
					} else {
						this.responderError(
								HttpServletResponse.SC_BAD_REQUEST,
								"Funcion desconocida. No es un GET ni un POST. Contacte al Soporte Tecnico.",
								baseRequest, response);
						LogManager
								.getInstance(RestHandler.class)
								.error("Funcion desconocida. No es un GET ni un POST. Contacte al Soporte Tecnico.");
						System.out.println("Funcion desconocida. No es un GET ni un POST. Contacte al Soporte Tecnico.");
					}
				}
			} else {
				this.responderError(HttpServletResponse.SC_FORBIDDEN,
						"El usuario y/o password son incorrectos.",
						baseRequest, response);
				LogManager.getInstance(RestHandler.class).error(
						"El usuario y/o password son incorrectos.");
				System.out.println("El usuario y/o password son incorrectos.");
			}
		} catch (Exception e) {
			this.responderError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Error interno del servidor: " + e.toString(), baseRequest,
					response);
			LogManager.getInstance(RestHandler.class).error(
					"Error interno del servidor: " + e.toString());
			System.out.println("Error interno del servidor: " + e.toString());
			
		}
	}

	/**
	 * Atiende los pedidos del tipo POST.
	 * 
	 * @param baseRequest
	 * @throws Exception
	 */
	private void atenderPost(HttpServletRequest baseRequest) throws Exception {

		Request req = getRequest(baseRequest);

		if (funcion.equalsIgnoreCase(FuncionRest.POSTRECLAMO)) {
			InputStream instream = baseRequest.getInputStream();
			InputStreamReader isReader = new InputStreamReader(instream);
			Gson gson = new Gson();
			ReclamoAndrac reclamoAndrac = gson.fromJson(isReader,
					ReclamoAndrac.class);
			IUsuario user = UsuarioManager.getInstance().getUsuarioFromDB(nick);
			IReclamo reclamo = ReclamoManager.getInstance().toReclamoInt(
					reclamoAndrac, user);
			ReclamoManager.getInstance().guardarReclamo(reclamo);
			req.setHandled(true);
			MailWilsonD.getInstance().enviarMail(
					reclamo.getMailCiudadanoGeneradorReclamo(),
					"Accion Ciudadana - Activacion de reclamo",
					MailWilsonD.getInstance().armarTextoCambioEstados(
							reclamo.getEstadoDescripcion(), reclamo));

		} else if (funcion.equalsIgnoreCase(FuncionRest.POSTUSUARIO)) {
			InputStream instream = baseRequest.getInputStream();
			InputStreamReader isReader = new InputStreamReader(instream);
			Gson gson = new Gson();
			IUsuario usuario = UsuarioManager.getInstance().toUsuarioInt(
					gson.fromJson(isReader, UsuarioAndrac.class));
			UsuarioManager.getInstance().guardarUsuario(usuario);
			req.setHandled(true);
			MailWilsonD.getInstance().enviarMail(usuario.getMail(),
					"Accion Ciudadana - Bienvenido",
					MailWilsonD.getInstance().armarTextoBienvenida(usuario));

		} else {
			throw new Exception("Funcion desconocida.");
		}
	}

	/**
	 * Atiende los pedidos del tipo GET.
	 * 
	 * @param baseRequest
	 * @param response
	 * @throws Exception
	 */
	private void atenderGet(HttpServletRequest baseRequest,
			HttpServletResponse response) throws Exception {

		Request req = getRequest(baseRequest);

		if (funcion.equalsIgnoreCase(FuncionRest.GETRECLAMOS)) {
			atenderReclamos(response, req);

		} else if (funcion.equalsIgnoreCase(FuncionRest.GETPERFIL)) {
			atenderPerfilUsuario(response, req);

		} else {
			throw new Exception("funcion desconocida");
		}

	}

	/**
	 * Envia datos de perfil de un usuario.
	 * 
	 * @param response
	 * @param req
	 * @throws Exception
	 */
	private void atenderPerfilUsuario(HttpServletResponse response, Request req)
			throws Exception {
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			UsuarioMovil usr = UsuarioManager.getInstance().getPerfil(nick);

			response.getWriter().write(new Gson().toJson(usr));
			req.setHandled(true);
		} catch (Exception e) {
			throw new Exception(
					"No se pudo obtener el perfil del usuario. Detalle: "
							+ e.getMessage());
		}
	}

	/**
	 * Envia lista de reclamos realizados por un usuario.
	 * 
	 * @param response
	 * @param req
	 * @throws Exception
	 */
	private void atenderReclamos(HttpServletResponse response, Request req)
			throws Exception {
		try {
			List<ReclamoAndrac> listaReclamosAndrac = ReclamoManager
					.getInstance().toReclamoAndrac(
							ReclamoManager.getInstance().obtenerTodosReclamos(
									nick));

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(new Gson().toJson(listaReclamosAndrac));
			req.setHandled(true);
		} catch (Exception e) {
			throw new Exception(
					"No se pudo obtener los reclamos del usuario. Detalle: "
							+ e.getMessage());
		}
	}

	/**
	 * Manda una respuesta con el codigo y mensage de error.
	 * 
	 * @since 16-10-2011
	 * @author Paul
	 * @param codigoError
	 * @param mensajeError
	 * @param baseRequest
	 * @param response
	 */
	private void responderError(int codigoError, String mensajeError,
			HttpServletRequest baseRequest, HttpServletResponse response) {
		Request req = getRequest(baseRequest);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			response.sendError(codigoError, mensajeError);
		} catch (IOException e) {
			LogManager.getInstance(RestHandler.class).error(
					"Fallo tratando responder ERROR.");
		}
		req.setHandled(true);
	}

	private Request getRequest(HttpServletRequest baseRequest) {
		Request req = (baseRequest instanceof Request ? (Request) baseRequest
				: HttpConnection.getCurrentConnection().getRequest());
		return req;
	}
}