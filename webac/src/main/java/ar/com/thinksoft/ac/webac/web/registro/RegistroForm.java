package ar.com.thinksoft.ac.webac.web.registro;


import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.com.thinksoft.ac.webac.registro.RegistroManager;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.usuario.UsuarioFactory;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;

@SuppressWarnings("serial")
public class RegistroForm extends Form<Usuario> {

	public RegistroForm(String id) {
		super(id);

		CompoundPropertyModel<Usuario> model = new CompoundPropertyModel<Usuario>(new UsuarioFactory().construirCiudadano());
		setModel(model);

		add(new TextField<String>("username", createStringBind(model,"nombreUsuario")));
		add(new PasswordTextField("password", this.createStringBind(model,"contrasenia")));
		add(new PasswordTextField("re-password", new Model<String>()));
		add(new TextField<String>("apellido", createStringBind(model,"apellido")));
		add(new TextField<String>("nombre", createStringBind(model, "nombre")));
		add(new TextField<String>("dni", createStringBind(model, "dni")));
		add(new TextField<String>("mail", createStringBind(model, "mail")));
		add(new TextField<String>("re-mail", new Model<String>()));
		add(new TextField<String>("telefono", createStringBind(model, "telefono")));

	}

	@Override
	protected void onSubmit() {

		Usuario usuario = getModelObject();
		new RegistroManager().registrar(usuario);
		setResponsePage(LoginPage.class);

	}

	private IModel<String> createStringBind(CompoundPropertyModel<Usuario> model, String property) {
		return model.bind(property);
	}

}
