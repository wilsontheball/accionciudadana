package ar.com.thinksoft.ac.webac;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.basic.Label;

import ar.com.thinksoft.ac.webac.seguridad.Permiso;
import ar.com.thinksoft.ac.webac.web.Context;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoForm;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoPage;

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

		add(CSSPackageResource.getHeaderContribution(HomePage.class,"../../css/HomeCiudadano.css"));
		add(JavascriptPackageResource.getHeaderContribution(HomePage.class,"../../js/homeCiudadano.js"));
		//add(new AltaReclamoForm("altaReclamoForm"));
	}

	// ****************** SEGURIDAD *******************
	@Override
	public Permiso getPermisoNecesario() {
		return new HomePagePermiso();
	}
}
