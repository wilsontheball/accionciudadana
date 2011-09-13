package ar.com.thinksoft.ac.webac.web.usuario.alta;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.web.usuario.form.UsuarioFilterObject;

public class UsuarioNuevoForm extends Form<IUsuario> {

	private static final long serialVersionUID = 4530512782651195546L;

	public UsuarioNuevoForm(String id) {
		super(id);

		CompoundPropertyModel<IUsuario> model = new CompoundPropertyModel<IUsuario>(
				new UsuarioFilterObject());
		this.setModel(model);

	}

}
