package ar.com.thinksoft.ac.webac.web.usuario.form;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.CSSPackageResource;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo.BusquedaReclamoPage;


@AuthorizeInstantiation({"ADMIN","OPERADOR"})
public class UsuarioPage extends BasePage {

	public UsuarioPage(final PageParameters parameters) {

		add(CSSPackageResource.getHeaderContribution(BusquedaReclamoPage.class, "../../css/UsuarioPage.css"));

		add(new UsuariosForm("form"));
		
		

	}

}
