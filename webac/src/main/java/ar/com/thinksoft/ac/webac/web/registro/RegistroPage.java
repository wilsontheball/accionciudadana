package ar.com.thinksoft.ac.webac.web.registro;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class RegistroPage extends BasePage {

	public RegistroPage(final PageParameters parameters) {
		add(CSSPackageResource.getHeaderContribution(RegistroPage.class,"../../css/RegistroPage.css"));
		add(JavascriptPackageResource.getHeaderContribution(RegistroPage.class,"../../js/registro.js"));
		add(new RegistroForm("registroForm"));
	}
	
	

}
