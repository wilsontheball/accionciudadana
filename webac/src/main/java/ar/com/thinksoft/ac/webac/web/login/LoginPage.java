package ar.com.thinksoft.ac.webac.web.login;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class LoginPage extends BasePage {

	@Override
	public IPermiso getPermisoNecesario() {
		return new LoginPermiso();
	}

	public LoginPage(final PageParameters parameters) {
		
		add(CSSPackageResource.getHeaderContribution(LoginPage.class,"LoginPage.css"));
		
		Form loginForm = new Form("loginForm"); 
		loginForm.setOutputMarkupId(true); 

        final TextField<String> nicknameString = new TextField<String>("nickname"); 
        nicknameString.setConvertEmptyInputStringToNull(false); 
        nicknameString.setRequired(true); 
        loginForm.add(nicknameString); 

        final PasswordTextField passwordString = new PasswordTextField("password");
        passwordString.setConvertEmptyInputStringToNull(false); 
        passwordString.setRequired(true); 
        loginForm.add(passwordString); 
        
        //loginForm.add(new Button("accederButton"));
        //loginForm.add(new Button("registrarButton"));
        
        add(loginForm);
	}
	
}
