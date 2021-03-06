package ar.com.thinksoft.ac.webac.web.base;

import org.apache.wicket.Component;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.IPageLink;

import ar.com.thinksoft.ac.webac.AccionCiudadanaSession;
import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.ayuda.AyudaPage;
import ar.com.thinksoft.ac.webac.web.configuracion.ConfiguracionPage;
import ar.com.thinksoft.ac.webac.web.download.DownloadPage;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;
import ar.com.thinksoft.ac.webac.web.logout.LogoutPage;
import ar.com.thinksoft.ac.webac.web.reclamo.altaReclamo.AltaReclamoPage;
import ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo.BusquedaReclamoPage;
import ar.com.thinksoft.ac.webac.web.usuario.form.UsuarioPage;
import ar.com.thinksoft.ac.webac.web.usuario.perfil.PerfilPage;

public abstract class BasePage extends WebPage {

	public BasePage() {
			
			add(CSSPackageResource.getHeaderContribution(BasePage.class,"../css/BasePage.css"));
			add(CSSPackageResource.getHeaderContribution(BasePage.class,"../css/jquery-ui.css"));
			add(JavascriptPackageResource.getHeaderContribution(BasePage.class,"../js/jquery.js"));
			add(JavascriptPackageResource.getHeaderContribution(BasePage.class,"../js/jquery-ui.js"));
			add(JavascriptPackageResource.getHeaderContribution(BasePage.class,"../js/jquery.blockUI.js"));
			add(JavascriptPackageResource.getHeaderContribution(BasePage.class,"../js/basePage.js"));
			this.appendLinks();

			add(this.userNameComponent());

			BookmarkablePageLink<IPageLink> logout = new BookmarkablePageLink<IPageLink>("logout", LogoutPage.class);
			BookmarkablePageLink<IPageLink> login = new BookmarkablePageLink<IPageLink>("login", LoginPage.class);
			add(logout);
			add(login);

			if (((AccionCiudadanaSession)getSession()).isSignedIn()) {
				login.setVisible(false);
				logout.setVisible(true);
			} else {
				login.setVisible(true);
				logout.setVisible(false);
			}

	}

	private Component userNameComponent() {
		try {
			String username = ((AccionCiudadanaSession)getSession()).getUsuario().getNombreUsuario();
			return new Label("username", "Usuario: "+ username);
		} catch (NullPointerException e) {

			return new Label("username", "");
		}
	}

	private void appendLinks() {
		add(new BookmarkablePageLink<IPageLink>("homeLink", HomePage.class));
		
		BookmarkablePageLink<IPageLink> perfilLink = new BookmarkablePageLink<IPageLink>("perfilLink", PerfilPage.class);
		MetaDataRoleAuthorizationStrategy.authorize(perfilLink, RENDER, "ADMIN");
		MetaDataRoleAuthorizationStrategy.authorize(perfilLink, RENDER,"OPERADOR");
		MetaDataRoleAuthorizationStrategy.authorize(perfilLink, RENDER,"CIUDADANO");
		add(perfilLink);
		
		BookmarkablePageLink<IPageLink> reclamos = new BookmarkablePageLink<IPageLink>("reclamos",this.getClass());
		MetaDataRoleAuthorizationStrategy.authorize(reclamos, RENDER, "ADMIN");
		MetaDataRoleAuthorizationStrategy.authorize(reclamos, RENDER,"OPERADOR");
		MetaDataRoleAuthorizationStrategy.authorize(reclamos, RENDER,"CIUDADANO");
		add(reclamos);
		
		add(new BookmarkablePageLink<IPageLink>("altaReclamoLink",AltaReclamoPage.class));
		
		add(new BookmarkablePageLink<IPageLink>("busquedaReclamoLink",BusquedaReclamoPage.class));
		
		BookmarkablePageLink<IPageLink> usuarioLink = new BookmarkablePageLink<IPageLink>("usuariosLink", UsuarioPage.class);
		MetaDataRoleAuthorizationStrategy.authorize(usuarioLink, RENDER, "ADMIN");
		add(usuarioLink);
		
		add(new BookmarkablePageLink<IPageLink>("downloadLink", this.getClass()));
		add(new BookmarkablePageLink<IPageLink>("manualAndroidLink",DownloadPage.class).setParameter("type", "manualAndroid"));
		add(new BookmarkablePageLink<IPageLink>("descargarAppLink",DownloadPage.class).setParameter("type", "appAndroid"));
		
		BookmarkablePageLink<IPageLink> configLink = new BookmarkablePageLink<IPageLink>("configLink",ConfiguracionPage.class);
		MetaDataRoleAuthorizationStrategy.authorize(configLink, RENDER, "ADMIN");
		add(configLink);
		
		BookmarkablePageLink<IPageLink> ayudaLink = new BookmarkablePageLink<IPageLink>("ayudaLink", AyudaPage.class);
		add(ayudaLink);

	}

}
