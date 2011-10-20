package ar.com.thinksoft.ac.webac.web.registro;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.db4o.ObjectSet;
import com.visural.wicket.component.dialog.Dialog;

import ar.com.thinksoft.ac.webac.exceptions.MailException;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.mail.MailManager;
import ar.com.thinksoft.ac.webac.predicates.registro.PredicateUsuarioExistente;
import ar.com.thinksoft.ac.webac.registro.RegistroManager;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.usuario.UsuarioFactory;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;

@SuppressWarnings("serial")
public class RegistroForm extends Form<Usuario> {

	private RegistroForm _self = this;
	private Dialog dialogUsuarioExistente = null;
	
	@SuppressWarnings("rawtypes")
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
		
		
		dialogUsuarioExistente = new Dialog("dialogUsuarioExistente");
	    add(dialogUsuarioExistente);
	    
	    dialogUsuarioExistente.add(new AjaxLink("volver"){
	    	@Override
	    	public void onClick(AjaxRequestTarget target){
	    		dialogUsuarioExistente.close(target);
	    	}
	    });
	    
	    add(new AjaxLink("guardarRegistro"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				
				Usuario usuario = _self.getModelObject();
				ObjectSet<Usuario> usuarios = Repository.getInstance().query(new PredicateUsuarioExistente().exist(usuario.getNombreUsuario()));
				
				if(usuarios.size()==0 && _self.usuarioIsValido(usuario)){
					new RegistroManager().registrar(usuario);
					try {
						MailManager.getInstance().enviarMail(usuario.getMail(), "Accion Ciudadana - Bienvenido", MailManager.getInstance().armarTextoBienvenida(usuario));
					} catch (MailException e) {
						LogFwk.getInstance(RegistroPage.class).error("No se pudo enviar el mail de bienvenida. Detalle: " + e.getMessage());
					}
					setResponsePage(LoginPage.class);
					setRedirect(true);
				}else{
					dialogUsuarioExistente.open(target);
				}
			}
	    });
	    
	}

	protected boolean usuarioIsValido(Usuario usuario) {
		return 	isStringOrNull(usuario.getApellido()) && isStringOrNull(usuario.getNombre()) &&
				isStringOrNull(usuario.getNombreUsuario()) && isStringOrNull(usuario.getContrasenia()) &&
				isStringOrNull(usuario.getMail());
	}
	
	private boolean isStringOrNull(String string){
		return string == "" || string == null;
	}

	private IModel<String> createStringBind(CompoundPropertyModel<Usuario> model, String property) {
		return model.bind(property);
	}

}
