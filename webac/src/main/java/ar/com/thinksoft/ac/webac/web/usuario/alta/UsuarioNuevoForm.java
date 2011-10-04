package ar.com.thinksoft.ac.webac.web.usuario.alta;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import ar.com.thinksoft.ac.webac.registro.RegistroManager;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.usuario.UsuarioFactory;
import ar.com.thinksoft.ac.webac.web.usuario.form.UsuarioPage;

public class UsuarioNuevoForm extends Form<Usuario> {

	private static final long serialVersionUID = 4530512782651195546L;
	private String repassword;
	private String tipoUsuario;
	private UsuarioNuevoForm self;

	public UsuarioNuevoForm(String id) {
		super(id);

		this.self = this;

		CompoundPropertyModel<Usuario> model = new CompoundPropertyModel<Usuario>(
				new Usuario());
		this.setModel(model);

		DropDownChoice<String> dropDownTipoUsuario = new DropDownChoice<String>(
				"tipoUsuario", this.createDropDownModel(),
				TipoUsuario.getValues());
		dropDownTipoUsuario.setNullValid(true);
		add(dropDownTipoUsuario);

		add(this.createUsername(model));
		add(new PasswordTextField("password", this.createBind(model,
				"contrasenia")));

		add(new PasswordTextField("re-password", this.createRePasswordModel()));
		add(new TextField<String>("apellido", createBind(model, "apellido")));
		add(new TextField<String>("nombre", createBind(model, "nombre")));
		add(new TextField<String>("dni", createBind(model, "dni")));
		add(this.createEmail(model));
		add(new TextField<String>("telefono", createBind(model, "telefono")));

		add(new Button("guardar") {

			@Override
			public void onSubmit() {
				Usuario usuario = self.getModelObject();
				if (!usuario.getContrasenia().equals(self.repassword)) {
					System.out.println("NO SON IGUALES");
					error("no son iguales");
				} else {
					self.convertUsuario(self.tipoUsuario, usuario);
					new RegistroManager().registrar(usuario);
					this.setResponsePage(UsuarioPage.class);
				}
			}
		});

		add(new AjaxLink("cancelar") {

			@Override
			public void onClick(AjaxRequestTarget ajaxRequestTarget) {
				setResponsePage(UsuarioPage.class);
			}
		});


	}

	 @Override
	 protected void onSubmit() {

		 System.out.println("mati");
	// Usuario usuario = getModelObject();
	// if (!usuario.getContrasenia().equals(this.repassword)) {
	// System.out.println("NO SON IGUALES");
	// error("no son iguales");
	// } else {
	// this.convertUsuario(this.tipoUsuario, usuario);
	// new RegistroManager().registrar(usuario);
	// this.setResponsePage(UsuarioPage.class);
	// }

	 }

	private IModel<String> createBind(CompoundPropertyModel<Usuario> model,
			String property) {
		return model.bind(property);
	}

	private IModel<String> createRePasswordModel() {
		return new IModel<String>() {

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

	@SuppressWarnings("serial")
	private IModel<String> createDropDownModel() {
		return new IModel<String>() {

			@Override
			public void detach() {
				// TODO Auto-generated method stub

			}

			@Override
			public String getObject() {
				return tipoUsuario;
			}

			@Override
			public void setObject(String object) {
				tipoUsuario = object;
			}
		};
	}

	/*
	 * 
	 * CREADORES DE CAMPOS
	 */

	protected TextField<String> createRequiredField(String markup,
			String property, CompoundPropertyModel<Usuario> model,
			List<IValidator<String>> validadores) {
		TextField<String> field = new TextField<String>(markup,
				this.createBind(model, property));

		field.setRequired(true);

		for (IValidator<String> validador : validadores) {
			field.add(validador);
		}

		return field;
	}

	protected TextField<String> createUsername(
			CompoundPropertyModel<Usuario> model) {

		List<IValidator<String>> validadores = new ArrayList<IValidator<String>>();
		validadores.add(this.spaceValidator("nombre de usuario"));
		return this.createRequiredField("username", "nombreUsuario", model,
				validadores);
	}

	protected TextField<String> createEmail(
			CompoundPropertyModel<Usuario> model) {

		List<IValidator<String>> validadores = new ArrayList<IValidator<String>>();
		validadores.add(this.emailValidator());
		return this.createRequiredField("mail", "mail", model, validadores);
	}

	// TODO: crear un mejor validador para los emails
	protected IValidator<String> emailValidator() {
		return new IValidator<String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5300811646328464566L;

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

			/**
			 * 
			 */
			private static final long serialVersionUID = 2425479829881845882L;

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

	protected void convertUsuario(String tipoUsuario, Usuario usuario) {
		if (TipoUsuario.Administrador.toString().equals(tipoUsuario))
			new UsuarioFactory().toAdministrador(usuario);
		if (TipoUsuario.Ciudadano.toString().equals(tipoUsuario))
			new UsuarioFactory().toCiudadano(usuario);
		if (TipoUsuario.Operario.toString().equals(tipoUsuario))
			new UsuarioFactory().toOperario(usuario);
	}

}
