package ar.com.thinksoft.ac.wilsond;

import java.io.IOException;
import java.util.ArrayList;
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
import ar.com.thinksoft.ac.wilsond.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.wilsond.usuario.Usuario;



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

	public void handle(String target, HttpServletRequest baseRequest,
			HttpServletResponse response, int dispatch) throws IOException,
			ServletException {

		

		if (baseRequest.getMethod().equalsIgnoreCase(HttpMethods.GET)) {
			// Atiende los GET
			try {
				atenderGet(baseRequest, response);
			} catch (Exception e) {
				//loggear error
			}

		} else {
			atenderPost(baseRequest);
		}
	}

	private void atenderPost(HttpServletRequest baseRequest) {
		System.out.println("No es un get. Es: " + baseRequest.getMethod());
	}

	private void atenderGet(HttpServletRequest baseRequest,	HttpServletResponse response) throws Exception {
		Request req = (baseRequest instanceof Request ? (Request) baseRequest: HttpConnection.getCurrentConnection().getRequest());

		String funcion = "";
		String nick = "";
		String pass = "";
		
		StringTokenizer tokens = new StringTokenizer(baseRequest.getRequestURI(), "/");
		if (tokens.hasMoreElements()) {
			funcion = (String) tokens.nextElement();
		}
		if (tokens.hasMoreElements()) {
			nick = (String) tokens.nextElement();
		}
		if (tokens.hasMoreElements()) {
			pass = (String) tokens.nextElement();
		}

		if (funcion.equalsIgnoreCase(FuncionRest.GETRECLAMOS)) {
			// Atiende pedido de reclamos de usuario.
			atenderReclamos(response, req);
			
		} else if (funcion.equalsIgnoreCase(FuncionRest.GETPERFIL)) {
			// Atiende pedido de perfil de usuario.
			atenderPerfilUsuario(response, req);
			
		} else {
			throw new Exception("funcion desconocida");
		}

	}

	private void atenderPerfilUsuario(HttpServletResponse response, Request req) throws IOException {
		
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
	}

	private void atenderReclamos(HttpServletResponse response, Request req)
			throws IOException {
		List<ReclamoAndrac> listaReclamosAndrac = toReclamoAndrac(ReclamoManager.getInstance().obtenerTodosReclamos());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		response.getWriter().write(new Gson().toJson(listaReclamosAndrac));
		response.setStatus(HttpServletResponse.SC_OK);
		req.setHandled(true);
	}

	private List<ReclamoAndrac> toReclamoAndrac(List<IReclamo> list) {
		List<ReclamoAndrac> listaDefinitiva = new ArrayList<ReclamoAndrac>();
		
		for(IReclamo reclamoInt : list){
			ReclamoAndrac reclamo = new ReclamoAndrac();
			reclamo.setAlturaIncidente(reclamoInt.getAlturaIncidente());
			reclamo.setBarrioIncidente(reclamoInt.getBarrioIncidente());
			reclamo.setCalleIncidente(reclamoInt.getCalleIncidente());
			reclamo.setCiudadanoGeneradorReclamo(reclamoInt.getCiudadanoGeneradorReclamo());
			reclamo.setMailCiudadanoGeneradorReclamo(reclamoInt.getMailCiudadanoGeneradorReclamo());
			reclamo.cambiarEstado(reclamoInt.getEstadoDescripcion());
			reclamo.setFechaReclamo(reclamoInt.getFechaReclamo());
			reclamo.setFechaUltimaModificacionReclamo(reclamoInt.getFechaUltimaModificacionReclamo());
			reclamo.setImagen(null);
			reclamo.setLatitudIncidente(reclamoInt.getLatitudIncidente());
			reclamo.setLongitudIncidente(reclamoInt.getLongitudIncidente());
			reclamo.setObservaciones(reclamoInt.getObservaciones());
			reclamo.setTipoIncidente(reclamoInt.getTipoIncidente());
			
			listaDefinitiva.add(reclamo);
		}
		return listaDefinitiva;
	}

}