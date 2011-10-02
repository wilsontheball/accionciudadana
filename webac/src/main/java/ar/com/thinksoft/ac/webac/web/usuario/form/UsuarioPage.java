package ar.com.thinksoft.ac.webac.web.usuario.form;

import org.apache.wicket.markup.html.CSSPackageResource;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo.BusquedaReclamoPage;

public class UsuarioPage extends BasePage {

	@Override
	public IPermiso getPermisoNecesario() {
		return new UsuarioPagePermiso();
	}

	public UsuarioPage() {

		add(CSSPackageResource.getHeaderContribution(BusquedaReclamoPage.class, "../../css/UsuarioPage.css"));

		add(new UsuariosForm("form"));
		
		

	}

}
