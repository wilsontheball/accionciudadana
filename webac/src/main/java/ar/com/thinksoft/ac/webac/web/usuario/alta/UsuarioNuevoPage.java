package ar.com.thinksoft.ac.webac.web.usuario.alta;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoPage;

public class UsuarioNuevoPage extends BasePage {

	public UsuarioNuevoPage() {
		add(CSSPackageResource.getHeaderContribution(AltaReclamoPage.class,
				"../../css/usuarioNuevo.css"));
		add(CSSPackageResource.getHeaderContribution(UsuarioNuevoPage.class,"../../css/RegistroPage.css"));
		add(JavascriptPackageResource.getHeaderContribution(UsuarioNuevoPage.class,"../../js/usuarioNuevo.js"));
		this.add(new UsuarioNuevoForm("form"));
	}

}
