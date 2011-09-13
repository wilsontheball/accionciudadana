package ar.com.thinksoft.ac.webac;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;

import ar.com.thinksoft.ac.webac.seguridad.Permiso;
import ar.com.thinksoft.ac.webac.web.Context;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

/**
 * Homepage
 */
public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	// TODO Add any page properties or variables here

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameterPages
	 */
	public HomePage(final PageParameters parameters) {

		
		//todo lo que esta hecho aca es a modo de pruebas... modificar...

			String nombre = "";
			if (Context.getInstance().getUsuario() != null)
				nombre = Context.getInstance().getUsuario().getNombreUsuario();
			// Add the simplest type of label
			add(new Label("message", "Bienvenido " + nombre
					+ " a Accion Ciudadana"));

		// TODO Add your page's components here
	}

	// ****************** SEGURIDAD *******************
	@Override
	public Permiso getPermisoNecesario() {
		return new HomePagePermiso();
	}
}
