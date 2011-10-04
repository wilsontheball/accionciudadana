package ar.com.thinksoft.ac.webac;


import org.apache.wicket.protocol.http.WebApplication;

import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.configuracion.ConfiguracionPage;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoPage;
import ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo.BusquedaReclamoPage;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoPage;
import ar.com.thinksoft.ac.webac.web.reclamo.modificarReclamo.ModificarReclamoPage;
import ar.com.thinksoft.ac.webac.web.registro.RegistroPage;
import ar.com.thinksoft.ac.webac.web.usuario.alta.UsuarioNuevoPage;
import ar.com.thinksoft.ac.webac.web.usuario.form.UsuarioPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see ar.com.thinksoft.ac.webac.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	/**
	 * Constructor
	 */
	public WicketApplication() {
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<LoginPage> getHomePage() {
		return LoginPage.class;
	}

	@Override
	protected void init() {
		getResourceSettings().setAddLastModifiedTimeToResourceReferenceUrl(true);
		getDebugSettings().setAjaxDebugModeEnabled(false);
		
		mountBookmarkablePage("Login", LoginPage.class);
		mountBookmarkablePage("Home", HomePage.class);
		mountBookmarkablePage("AltaReclamo", AltaReclamoPage.class);
		mountBookmarkablePage("ListadoReclamos", BusquedaReclamoPage.class);
		mountBookmarkablePage("DetalleReclamo", DetalleReclamoPage.class);
		mountBookmarkablePage("ModificarReclamo", ModificarReclamoPage.class);
		mountBookmarkablePage("Configuracion", ConfiguracionPage.class);
		mountBookmarkablePage("Registro", RegistroPage.class);
		mountBookmarkablePage("Usuarios", UsuarioPage.class);
		mountBookmarkablePage("UsuarioNuevo", UsuarioNuevoPage.class);
		
	}
	
}
