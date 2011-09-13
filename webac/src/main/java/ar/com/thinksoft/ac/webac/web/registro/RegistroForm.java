package ar.com.thinksoft.ac.webac.web.registro;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.registro.RegistroManager;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.usuario.UsuarioFactory;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;

public class RegistroForm extends Form<IUsuario> {

	private String repassword;

	public RegistroForm(String id) {
		super(id);

		CompoundPropertyModel<IUsuario> model = new CompoundPropertyModel<IUsuario>(
				new UsuarioFactory().construirCiudadano());
		setModel(model);

		add(this.createUsername(model));
		add(new PasswordTextField("password", this.createStringBind(model,
				"contrasenia")));

		add(new PasswordTextField("re-password", this.createRePasswordModel()));
		add(new TextField<String>("apellido", createStringBind(model,
				"apellido")));
		add(new TextField<String>("nombre", createStringBind(model, "nombre")));
		add(new TextField<Long>("dni", createLongBind(model, "dni")));
		add(this.createEmail(model));
		add(new TextField<Long>("telefono", createLongBind(model, "telefono")));

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -997173136959185992L;

	@Override
	protected void onSubmit() {

		IUsuario usuario = getModelObject();
		if (!usuario.getContrasenia().equals(this.repassword)) {
			System.out.println("NO SON IGUALES");
			error("no son iguales");
		} else {
			new RegistroManager().registrar(usuario);
			setResponsePage(LoginPage.class);
		}

	}

	private IModel<String> createStringBind(
			CompoundPropertyModel<IUsuario> model, String property) {
		return model.bind(property);
	}

	private IModel<Long> createLongBind(CompoundPropertyModel<IUsuario> model,
			String property) {
		return model.bind(property);
	}

	private IModel<String> createRePasswordModel() {
		return new IModel<String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4559650441477758535L;

			@Override
			public void detach() {
				// TODO Auto-generated method stub

			}

			@Override
			public String getObject() {
				return repassword;
			}

			@Override
			public void setObject(String pass) {
				repassword = pass;
			}
		};
	}

	/*
	 * 
	 * CREADORES DE CAMPOS
	 */

	protected TextField<String> createRequiredField(String markup,
			String property, CompoundPropertyModel<IUsuario> model,
			List<IValidator<String>> validadores) {
		TextField<String> field = new TextField<String>(markup,
				this.createStringBind(model, property));

		field.setRequired(true);

		for (IValidator<String> validador : validadores) {
			field.add(validador);
		}

		return field;
	}

	protected TextField<String> createUsername(
			CompoundPropertyModel<IUsuario> model) {

		List<IValidator<String>> validadores = new ArrayList<IValidator<String>>();
		validadores.add(this.spaceValidator("nombre de usuario"));
		return this.createRequiredField("username", "nombreUsuario", model,
				validadores);
	}

	protected TextField<String> createEmail(
			CompoundPropertyModel<IUsuario> model) {

		List<IValidator<String>> validadores = new ArrayList<IValidator<String>>();
		validadores.add(this.emailValidator());
		return this.createRequiredField("mail", "mail", model, validadores);
	}

	// TODO: crear un mejor validador para los emails
	protected IValidator<String> emailValidator() {
		return new IValidator<String>() {

			@Override
			public void validate(IValidatable<String> validatable) {
				if (!validatable.getValue().contains("@"))
					validatable
							.error(createError("El e-mail especificado no es correcto"));
			}
		};
	}

	protected IValidator<String> spaceValidator(final String nombreCampo) {
		return new IValidator<String>() {

			@Override
			public void validate(IValidatable<String> validatable) {
				if (validatable.getValue().contains(" "))
					validatable.error(createError("El " + nombreCampo
							+ "no puede contener espacios"));
			}

		};
	}

	protected IValidationError createError(String mensaje) {
		ValidationError validationError = new ValidationError();
		validationError.setMessage(mensaje);
		return validationError;
	}

}
