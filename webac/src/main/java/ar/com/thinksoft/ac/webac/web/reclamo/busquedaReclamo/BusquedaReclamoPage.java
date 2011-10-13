package ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import ar.com.thinksoft.ac.webac.web.base.BasePage;

@AuthorizeInstantiation({"CIUDADANO","ADMIN","OPERADOR"})
public class BusquedaReclamoPage extends BasePage{

	public BusquedaReclamoPage(final PageParameters parameters) {
		
		add(CSSPackageResource.getHeaderContribution(BusquedaReclamoPage.class,"../../css/BusquedaReclamo.css"));
		add(JavascriptPackageResource.getHeaderContribution(BusquedaReclamoPage.class,"../../js/busquedaReclamo.js"));
		
		//Calendar: no modificar el orden
		add(CSSPackageResource.getHeaderContribution(BusquedaReclamoPage.class,"../../css/datepickercontrol.css"));
		add(JavascriptPackageResource.getHeaderContribution(BusquedaReclamoPage.class,"../../js/datepickercontrol.js"));
		
		
		add(new BusquedaReclamoForm("busquedaReclamoForm"));
	}
	
	

}
