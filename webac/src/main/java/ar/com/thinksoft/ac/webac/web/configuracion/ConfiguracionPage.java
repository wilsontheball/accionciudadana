package ar.com.thinksoft.ac.webac.web.configuracion;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import ar.com.thinksoft.ac.webac.web.base.BasePage;

@AuthorizeInstantiation("ADMIN")
public class ConfiguracionPage extends BasePage{

	public ConfiguracionPage(final PageParameters parameters) {
		
		add(CSSPackageResource.getHeaderContribution(ConfiguracionPage.class,"../css/Configuracion.css"));
		add(JavascriptPackageResource.getHeaderContribution(ConfiguracionPage.class,"../js/configuracion.js"));
		add(new ConfiguracionForm("configuracionForm"));
	}
	
	

}
