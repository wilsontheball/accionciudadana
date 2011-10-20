package ar.com.thinksoft.ac.webac.web.usuario.perfil;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.CSSPackageResource;

import ar.com.thinksoft.ac.webac.AccionCiudadanaSession;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

@AuthorizeInstantiation("ALL")
public class PerfilPage extends BasePage{

	public PerfilPage(final PageParameters parameters) throws Exception {
		add(CSSPackageResource.getHeaderContribution(PerfilPage.class,"../../css/PerfilPage.css"));
		AccionCiudadanaSession session = (AccionCiudadanaSession) getSession();
		add(new PerfilForm("perfilForm",session.getUsuario().getNombreUsuario()));
	}
}
