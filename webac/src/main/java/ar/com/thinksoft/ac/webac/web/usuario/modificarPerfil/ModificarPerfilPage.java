package ar.com.thinksoft.ac.webac.web.usuario.modificarPerfil;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import ar.com.thinksoft.ac.webac.AccionCiudadanaSession;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoPage;

@AuthorizeInstantiation("ALL")
public class ModificarPerfilPage extends BasePage{
	
	public ModificarPerfilPage(final PageParameters parameters) throws Exception {
		add(CSSPackageResource.getHeaderContribution(AltaReclamoPage.class,"../../css/RegistroPage.css"));
		add(JavascriptPackageResource.getHeaderContribution(AltaReclamoPage.class,"../../js/modificarPerfil.js"));
		AccionCiudadanaSession session = (AccionCiudadanaSession) getSession();
		add(new ModificarPerfilForm("modificarPerfilForm",session.getUsuario().getNombreUsuario()));
	}

}
