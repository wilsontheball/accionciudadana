package ar.com.thinksoft.ac.webac.web.base;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;

import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.seguridad.Permitible;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;
import ar.com.thinksoft.ac.webac.web.reclamo.AltaReclamoPage;

public abstract class BasePage extends Permitible {

	public BasePage() {
		
		add(CSSPackageResource.getHeaderContribution(BasePage.class,"../css/BasePage.css"));
		add(JavascriptPackageResource.getHeaderContribution(BasePage.class,"../js/jquery.js"));
		this.appendLinks();
		
	}
	
	private void appendLinks(){
		add(new BookmarkablePageLink<IPageLink>("loginLink", LoginPage.class));
		add(new BookmarkablePageLink<IPageLink>("homeLink", HomePage.class));
		add(new BookmarkablePageLink<IPageLink>("altaReclamoLink", AltaReclamoPage.class));
		
	}

}
