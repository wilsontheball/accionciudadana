package ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import ar.com.thinksoft.ac.webac.web.base.BasePage;

@AuthorizeInstantiation({"CIUDADANO","ADMIN","OPERADOR"})
public class DetalleReclamoPage extends BasePage{

	public DetalleReclamoPage(final PageParameters parameters) {
		
		add(CSSPackageResource.getHeaderContribution(DetalleReclamoPage.class,"../../css/DetalleReclamo.css"));
		add(JavascriptPackageResource.getHeaderContribution(DetalleReclamoPage.class,"../../js/detalleReclamo.js"));
		try {
			add(new DetalleReclamoForm("detalleReclamoForm", parameters.getString("reclamoId")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
