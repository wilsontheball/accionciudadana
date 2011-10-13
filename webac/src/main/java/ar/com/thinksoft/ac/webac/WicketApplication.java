package ar.com.thinksoft.ac.webac;

import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;

import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.HomePage.Administrativo.HomePageAdministrativo;
import ar.com.thinksoft.ac.webac.web.HomePage.Ciudadano.HomePageCiudadano;
import ar.com.thinksoft.ac.webac.web.configuracion.ConfiguracionPage;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoPage;
import ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo.BusquedaReclamoPage;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoPage;
import ar.com.thinksoft.ac.webac.web.reclamo.modificarReclamo.ModificarReclamoPage;
import ar.com.thinksoft.ac.webac.web.registro.RegistroPage;
import ar.com.thinksoft.ac.webac.web.usuario.alta.UsuarioNuevoPage;
import ar.com.thinksoft.ac.webac.web.usuario.form.UsuarioPage;
import ar.com.thinksoft.ac.webac.web.usuario.god.GodPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see ar.com.thinksoft.ac.webac.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication {
	/**
	 * Constructor
	 */
	public WicketApplication() {
		super();
	}

	
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();
		getResourceSettings()
				.setAddLastModifiedTimeToResourceReferenceUrl(true);
		getDebugSettings().setAjaxDebugModeEnabled(false);
		mountBookmarkablePage("Login", LoginPage.class);
		mountBookmarkablePage("Home", HomePage.class);
		mountBookmarkablePage("HomeCiudadano", HomePageCiudadano.class);
		mountBookmarkablePage("HomeAdministrativo", HomePageAdministrativo.class);
		mountBookmarkablePage("AltaReclamo", AltaReclamoPage.class);
		mountBookmarkablePage("ListadoReclamos", BusquedaReclamoPage.class);
		mountBookmarkablePage("DetalleReclamo", DetalleReclamoPage.class);
		mountBookmarkablePage("ModificarReclamo", ModificarReclamoPage.class);
		mountBookmarkablePage("Configuracion", ConfiguracionPage.class);
		mountBookmarkablePage("Registro", RegistroPage.class);
		mountBookmarkablePage("Usuarios", UsuarioPage.class);
		mountBookmarkablePage("UsuarioNuevo", UsuarioNuevoPage.class);
		mountBookmarkablePage("God", GodPage.class);
	}

	@Override
	protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
		return AccionCiudadanaSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}
	

}
