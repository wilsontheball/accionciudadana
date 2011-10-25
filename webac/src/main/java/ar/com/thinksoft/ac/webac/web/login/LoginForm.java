package ar.com.thinksoft.ac.webac.web.login;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;

import ar.com.thinksoft.ac.webac.AccionCiudadanaSession;
import ar.com.thinksoft.ac.webac.login.Login;
import ar.com.thinksoft.ac.webac.login.exceptions.UserNotFoundException;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.registro.RegistroPage;

public class LoginForm extends Form<Void> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4069224500703821080L;
	private String nombreUsuario;
	private String contrasenia;

	public LoginForm(String id) {
		super(id);

		final TextField<String> nicknameString = new TextField<String>("nickname", this.createNombreUsuarioModel());
		nicknameString.setConvertEmptyInputStringToNull(false);
		nicknameString.setRequired(true);
		this.add(nicknameString);

		final PasswordTextField passwordString = new PasswordTextField("password", this.createContraseniaModel());
		passwordString.setConvertEmptyInputStringToNull(false);
		passwordString.setRequired(true);
		this.add(passwordString);
		
		add(new BookmarkablePageLink<IPageLink>("registerLink",RegistroPage.class));
		add(new FeedbackPanel("feedback"));
	}

	@Override
	protected void onSubmit() {
		
		Login login = new Login(this.nombreUsuario,this.contrasenia);

		try {
			Usuario usuario = login.login();
			if(usuario.getRoles().size()==0){
				usuario.addRole("CIUDADANO");
				usuario.addRole("ALL");
				Repository.getInstance().store(usuario);
			}
			((AccionCiudadanaSession)getSession()).login(usuario);
			setResponsePage(HomePage.class);
		} catch (UserNotFoundException e) {
			if (!login.isUsuarioExistente())
				error(e.getLocalizedMessage());
			else
				error("El usuario no se encuentra registrado en el sistema o ha ingresado mal la clave.");
		}
	}

	/*
	 * ACA ESTAN LOS MODELS
	 */
	private IModel<String> createNombreUsuarioModel() {
		return new IModel<String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2365265801197256213L;

			@Override
			public void detach() {
			}

			@Override
			public String getObject() {
				return nombreUsuario;
			}

			@Override
			public void setObject(String username) {
				nombreUsuario = username;
			}
		};
	}

	private IModel<String> createContraseniaModel() {
		return new IModel<String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2365265801197256213L;

			@Override
			public void detach() {
			}

			@Override
			public String getObject() {
				return contrasenia;
			}

			@Override
			public void setObject(String password) {
				contrasenia = password;
			}
		};
	}

}

