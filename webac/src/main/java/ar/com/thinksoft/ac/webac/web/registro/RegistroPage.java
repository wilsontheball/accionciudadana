package ar.com.thinksoft.ac.webac.web.registro;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoPage;

public class RegistroPage extends BasePage {

	public RegistroPage(final PageParameters parameters) {
		add(CSSPackageResource.getHeaderContribution(AltaReclamoPage.class,"../../css/RegistroPage.css"));
		add(JavascriptPackageResource.getHeaderContribution(AltaReclamoPage.class,"../../js/registro.js"));
		add(new RegistroForm("registroForm"));
	}
	
	

}
