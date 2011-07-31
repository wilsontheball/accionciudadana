package ar.com.thinksoft.ac.webac.web.base;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.IPageLink;

import ar.com.thinksoft.ac.webac.seguridad.Permitible;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;

public abstract class BasePage extends Permitible {

	public BasePage() {
		
		add(CSSPackageResource.getHeaderContribution(BasePage.class,"BasePage.css"));
		
		
		this.appendLinks();
		
		
		
	}
	
	private void appendLinks(){
		add(new BookmarkablePageLink<IPageLink>("loginLink", LoginPage.class));
	}

}
