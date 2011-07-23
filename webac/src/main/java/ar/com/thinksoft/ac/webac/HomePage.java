package ar.com.thinksoft.ac.webac;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;

import ar.com.thinksoft.ac.webac.seguridad.Permiso;
import ar.com.thinksoft.ac.webac.seguridad.Permitible;

/**
 * Homepage
 */
public class HomePage extends Permitible {

	private static final long serialVersionUID = 1L;

	// TODO Add any page properties or variables here

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameterPages
	 */
    public HomePage(final PageParameters parameters) {

        // Add the simplest type of label
        add(new Label("message", "If you see this message wicket is properly configured and running"));

        // TODO Add your page's components here
    }

    
    
    
    
    // ****************** SEGURIDAD *******************
	@Override
	public Permiso getPermisoNecesario() {
		return new HomePagePermiso();
	}
}
