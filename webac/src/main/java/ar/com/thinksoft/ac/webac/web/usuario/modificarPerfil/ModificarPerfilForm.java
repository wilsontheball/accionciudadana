package ar.com.thinksoft.ac.webac.web.usuario.modificarPerfil;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.db4o.ObjectSet;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.predicates.registro.PredicateUsuarioExistente;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.web.usuario.perfil.PerfilPage;

@SuppressWarnings("serial")
public class ModificarPerfilForm extends Form<Usuario>{
	
	private Usuario usuarioOriginal = new Usuario();
	private ModificarPerfilForm _self = this;
	
	@SuppressWarnings("rawtypes")
	public ModificarPerfilForm(String id,String nicknameUsuario) throws Exception {
		super(id);
		
		ObjectSet<Usuario> usuarios = Repository.getInstance().query(new PredicateUsuarioExistente().exist(nicknameUsuario));
		
		if(usuarios.size()!= 1)
			throw new Exception("error en la base de datos, por favor, comuniquese con el equipo de soporte tecnico");
		
		usuarioOriginal = usuarios.get(0);
		
		IUsuario usuario = usuarioClon();
		
		CompoundPropertyModel<Usuario> model = new CompoundPropertyModel<Usuario>(usuario);
		setModel(model);

		add(new PasswordTextField("password", this.createStringBind(model,"contrasenia")));
		add(new PasswordTextField("re-password", new Model<String>()));
		add(new TextField<String>("apellido", createStringBind(model,"apellido")));
		add(new TextField<String>("nombre", createStringBind(model, "nombre")));
		add(new TextField<String>("dni", createStringBind(model, "dni")));
		add(new TextField<String>("mail", createStringBind(model, "mail")));
		add(new TextField<String>("re-mail", new Model<String>()));
		add(new TextField<String>("telefono", createStringBind(model, "telefono")));
		
		add(new AjaxLink("cancelar") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(PerfilPage.class);
				setRedirect(true);
			}
		});
		
		add(new Button("actualizarUsuario") {
			@Override
			public void onSubmit() {
				Usuario usuarioModificado = _self.getModelObject();
				for(String rol : usuarioOriginal.getRoles()){
					usuarioModificado.addRole(rol);
				}
				Repository.getInstance().delete(usuarioOriginal);
				Repository.getInstance().store(usuarioModificado);
				setResponsePage(PerfilPage.class);
				setRedirect(true);
			}
		});

	}

	private IModel<String> createStringBind(CompoundPropertyModel<Usuario> model, String property) {
		return model.bind(property);
	}
	
	private IUsuario usuarioClon() {
		IUsuario usuario = new Usuario();
		usuario.setApellido(usuarioOriginal.getApellido());
		usuario.setContrasenia(usuarioOriginal.getContrasenia());
		usuario.setDni(usuarioOriginal.getDni());
		usuario.setMail(usuarioOriginal.getMail());
		usuario.setNombre(usuarioOriginal.getNombre());
		usuario.setNombreUsuario(usuarioOriginal.getNombreUsuario());
		usuario.setTelefono(usuarioOriginal.getTelefono());
		return usuario;
	}

}
