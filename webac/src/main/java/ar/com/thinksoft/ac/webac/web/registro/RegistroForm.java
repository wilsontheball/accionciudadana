package ar.com.thinksoft.ac.webac.web.registro;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;

public class RegistroForm extends Form<IUsuario> {

	private String repassword;

	public RegistroForm(String id) {
		super(id);
		CompoundPropertyModel<IUsuario> model = new CompoundPropertyModel<IUsuario>(
				new Usuario());
		setModel(model);

		add(new TextField<String>("username", this.createStringBind(model,
				"nombreUsuario")));
		add(new PasswordTextField("password", this.createStringBind(model,
				"contrasenia")));
		
		add(new PasswordTextField("re-password", this.createRePasswordModel()));
		add(new TextField<String>("apellido", createStringBind(model, "apellido")));
		add(new TextField<String>("nombre", createStringBind(model, "nombre")));
		add(new TextField<Long>("dni", createLongBind(model, "dni")));
		add(new TextField<String>("mail", createStringBind(model, "mail")));
		add(new TextField<Long>("telefono", createLongBind(model, "telefono")));

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -997173136959185992L;

	@Override
	protected void onSubmit() {

		IUsuario usuario = getModelObject();
		System.out.println(usuario.getNombreUsuario());
		System.out.println(usuario.getContrasenia());

		if (!usuario.getContrasenia().equals(this.repassword)) {
			System.out.println("NO SON IGUALES");
			error("no son iguales");
		}

		Repository.getInstance().store(usuario);
		setResponsePage(HomePage.class);

	}

	private IModel<String> createStringBind(CompoundPropertyModel<IUsuario> model,
			String property) {
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

}
