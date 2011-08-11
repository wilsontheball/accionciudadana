package ar.com.thinksoft.ac.webac.web.registro;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;

public class RegistroForm extends Form<IUsuario> {

	public RegistroForm(String id) {
		super(id);
		CompoundPropertyModel<IUsuario> model = new CompoundPropertyModel<IUsuario>(
				new Usuario());
		setModel(model);
		
		TextField<String> username = new TextField<String>("username",this.createBind(model,"nombreUsuario"));
		add(username);
		
		PasswordTextField password = new PasswordTextField("password",this.createBind(model, "contrasenia"));
		add(password);
		
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
		Repository.getInstance().store(usuario);

	}
	
	private IModel<String> createBind(CompoundPropertyModel<IUsuario> model,String property){
		return model.bind(property);
	}

}
