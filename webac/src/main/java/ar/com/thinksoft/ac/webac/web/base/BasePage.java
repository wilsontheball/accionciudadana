package ar.com.thinksoft.ac.webac.web.base;

import org.apache.wicket.markup.html.CSSPackageResource;

import ar.com.thinksoft.ac.webac.seguridad.Permitible;

public abstract class BasePage extends Permitible {

	public BasePage() {
		add(CSSPackageResource.getHeaderContribution(BasePage.class,"BasePage.css"));
	}
	
	private void createHeader(){
		
	}

}
