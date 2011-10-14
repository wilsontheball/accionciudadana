package ar.com.thinksoft.ac.webac.web.usuario.alta;

import org.apache.wicket.markup.html.CSSPackageResource;

import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoPage;

public class UsuarioNuevoPage extends BasePage {

	public UsuarioNuevoPage() {
		add(CSSPackageResource.getHeaderContribution(AltaReclamoPage.class,
				"../../css/usuarioNuevo.css"));
		this.add(new UsuarioNuevoForm("form"));

	}

}
