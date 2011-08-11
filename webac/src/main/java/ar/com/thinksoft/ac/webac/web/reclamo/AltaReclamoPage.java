package ar.com.thinksoft.ac.webac.web.reclamo;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.basic.Label;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class AltaReclamoPage extends BasePage{

	@Override
	public IPermiso getPermisoNecesario() {
		return new AltaReclamoPermiso();
	}
	
	public AltaReclamoPage(final PageParameters parameters) {
		
		add(CSSPackageResource.getHeaderContribution(AltaReclamoPage.class,"../css/AltaReclamo.css"));
		
	}

}
