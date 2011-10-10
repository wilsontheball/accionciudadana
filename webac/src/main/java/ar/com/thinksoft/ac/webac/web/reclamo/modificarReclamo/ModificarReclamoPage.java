package ar.com.thinksoft.ac.webac.web.reclamo.modificarReclamo;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoPage;

@AuthorizeInstantiation({"CIUDADANO","ADMIN","OPERADOR"})
public class ModificarReclamoPage extends BasePage{

public ModificarReclamoPage(final PageParameters parameters) {
		
		add(CSSPackageResource.getHeaderContribution(DetalleReclamoPage.class,"../../css/ModificarReclamo.css"));
		add(JavascriptPackageResource.getHeaderContribution(DetalleReclamoPage.class,"../../js/modificarReclamo.js"));
		try {
			add(new ModificarReclamoForm("modificarReclamoForm", parameters.getString("reclamoId")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
