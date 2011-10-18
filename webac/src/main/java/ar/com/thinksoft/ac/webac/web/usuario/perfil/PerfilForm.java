package ar.com.thinksoft.ac.webac.web.usuario.perfil;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.db4o.ObjectSet;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.predicates.registro.PredicateUsuarioExistente;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.web.usuario.modificarPerfil.ModificarPerfilPage;

@SuppressWarnings("serial")
public class PerfilForm extends Form<Usuario> {

	private IUsuario usuario = new Usuario();
	
	public PerfilForm(String id,String nicknameUsuario) throws Exception {
		super(id);
		
		ObjectSet<Usuario> usuarios = Repository.getInstance().query(new PredicateUsuarioExistente().exist(nicknameUsuario));
		
		if(usuarios.size()!= 1)
			throw new Exception("error en la base de datos, por favor, comuniquese con el equipo de soporte tecnico");
	
		usuario = usuarios.get(0);
		
		CompoundPropertyModel<Usuario> model = new CompoundPropertyModel<Usuario>(usuario);
		setModel(model);

		
		add(new Label("apellido", createStringBind(model,"apellido")));
		add(new Label("nombre", createStringBind(model, "nombre")));
		add(new Label("username", createStringBind(model,"nombreUsuario")));
		add(new Label("dni", createStringBind(model, "dni")));
		add(new Label("mail", createStringBind(model, "mail")));
		add(new Label("telefono", createStringBind(model, "telefono")));

		add(new Button("modificarUsuario") {
			@Override
			public void onSubmit() {
				
				PageParameters params =new PageParameters();
		        params.add("nicknameUsuario", usuario.getNombreUsuario());
	            
		        setResponsePage(ModificarPerfilPage.class, params);
		        setRedirect(true);
			}
		});
		
	}
	
	private IModel<String> createStringBind(CompoundPropertyModel<Usuario> model, String property) {
		return model.bind(property);
	}

}
