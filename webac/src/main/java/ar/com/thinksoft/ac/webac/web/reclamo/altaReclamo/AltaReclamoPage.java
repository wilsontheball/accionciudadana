package ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import ar.com.thinksoft.ac.webac.web.base.BasePage;

@AuthorizeInstantiation({"CIUDADANO","ADMIN","OPERADOR"})
public class AltaReclamoPage extends BasePage{

	public AltaReclamoPage(final PageParameters parameters) {
		
		add(CSSPackageResource.getHeaderContribution(AltaReclamoPage.class,"../../css/AltaReclamo.css"));
		add(JavascriptPackageResource.getHeaderContribution(AltaReclamoPage.class,"../../js/altaReclamo.js"));
		add(new AltaReclamoForm("altaReclamoForm"));
	}
	
	

}
