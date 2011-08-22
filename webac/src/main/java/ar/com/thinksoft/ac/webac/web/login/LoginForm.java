/*package ar.com.thinksoft.ac.webac.web.login;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.login.Login;
import ar.com.thinksoft.ac.webac.login.exceptions.UserNotFoundException;
import ar.com.thinksoft.ac.webac.web.Context;

public class LoginForm extends Form<Void> {


	private static final long serialVersionUID = 4069224500703821080L;
	private String nombreUsuario;
	private String contrasenia;

	public LoginForm(String id) {
		super(id);

		final TextField<String> nicknameString = new TextField<String>(
				"nickname", this.createNombreUsuarioModel());
		nicknameString.setConvertEmptyInputStringToNull(false);
		nicknameString.setRequired(true);
		this.add(nicknameString);

		final PasswordTextField passwordString = new PasswordTextField(
				"password", this.createContraseniaModel());
		passwordString.setConvertEmptyInputStringToNull(false);
		passwordString.setRequired(true);
		this.add(passwordString);
	}

	@Override
	protected void onSubmit() {

		Login login = new Login(this.nombreUsuario,this.contrasenia);

		try {
			IUsuario usuario = login.login();
			// TODO: aca faltaria ver donde nos metemos el usuario registrado.
			// Deberia haber algo como un contexto o algo parecido, un
			// singleton.
			Context.getInstance().setUsuario(usuario);
			setResponsePage(HomePage.class);
		} catch (UserNotFoundException e) {
			if (!login.isUsuarioExistente())
				error(e.getLocalizedMessage());
			else
				error("La contraseÃ±a ingresada no es valida.");
		}
	}


	private IModel<String> createNombreUsuarioModel() {
		return new IModel<String>() {

			
			private static final long serialVersionUID = 2365265801197256213L;

			@Override
			public void detach() {
				// TODO Auto-generated method stub

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

			
			private static final long serialVersionUID = 2365265801197256213L;

			@Override
			public void detach() {
				// TODO Auto-generated method stub

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

}*/

package ar.com.thinksoft.ac.webac.web.login;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.login.Login;
import ar.com.thinksoft.ac.webac.login.exceptions.UserNotFoundException;
import ar.com.thinksoft.ac.webac.web.Context;

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
	}

	@Override
	protected void onSubmit() {

		Login login = new Login(this.nombreUsuario,this.contrasenia);

		try {
			IUsuario usuario = login.login();
			// TODO: aca faltaria ver donde nos metemos el usuario registrado.
			// Deberia haber algo como un contexto o algo parecido, un
			// singleton.
			Context.getInstance().setUsuario(usuario);
			setResponsePage(HomePage.class);
		} catch (UserNotFoundException e) {
			if (!login.isUsuarioExistente())
				error(e.getLocalizedMessage());
			else
				error("La contrasenia ingresada no es valida.");
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
				// TODO Auto-generated method stub

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
				// TODO Auto-generated method stub

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

