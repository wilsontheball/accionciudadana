package ar.com.thinksoft.ac.webac.web.reclamo.modificarReclamo;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoPage;

public class ModificarReclamoPage extends BasePage{

	@Override
	public IPermiso getPermisoNecesario() {
		return new ModificarReclamoPermiso();
	}
	
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
