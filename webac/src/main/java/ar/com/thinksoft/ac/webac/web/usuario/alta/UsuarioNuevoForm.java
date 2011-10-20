package ar.com.thinksoft.ac.webac.web.usuario.alta;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
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
import ar.com.thinksoft.ac.webac.web.registro.RegistroPage;
import ar.com.thinksoft.ac.webac.web.usuario.form.UsuarioPage;

public class UsuarioNuevoForm extends Form<Usuario> {

	private static final long serialVersionUID = 4530512782651195546L;
	private String tipoUsuario;
	private UsuarioNuevoForm _self = this;
	private Dialog dialogUsuarioExistente = null;
	
	@SuppressWarnings({ "serial", "rawtypes" })
	public UsuarioNuevoForm(String id) {
		super(id);

		CompoundPropertyModel<Usuario> model = new CompoundPropertyModel<Usuario>(new Usuario());
		this.setModel(model);

		DropDownChoice<String> dropDownTipoUsuario = new DropDownChoice<String>("tipoUsuario", this.createDropDownModel(),
				TipoUsuario.getValues());
		dropDownTipoUsuario.setNullValid(true);
		add(dropDownTipoUsuario);

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
				
				
				if(usuarios.size()==0){
					new RegistroManager().registrar(usuario);
					try {
						MailManager.getInstance().enviarMail(usuario.getMail(), "Accion Ciudadana - Bienvenido", MailManager.getInstance().armarTextoBienvenida(usuario));
					} catch (MailException e) {
						LogFwk.getInstance(RegistroPage.class).error("No se pudo enviar el mail de bienvenida. Detalle: " + e.getMessage());
					}
					setResponsePage(UsuarioPage.class);
					setRedirect(true);
				}else{
					dialogUsuarioExistente.open(target);
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
	 }

	private IModel<String> createStringBind(CompoundPropertyModel<Usuario> model, String property) {
		return model.bind(property);
	}

	@SuppressWarnings("serial")
	private IModel<String> createDropDownModel() {
		return new IModel<String>() {

			@Override
			public void detach() {

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

	protected void convertUsuario(String tipoUsuario, Usuario usuario) {
		if("Ciudadano".equals(tipoUsuario)){
			usuario.addRole("CIUDADANO");
			usuario.addRole("ALL");
		}
		
		if("Operario".equals(tipoUsuario)){
			usuario.addRole("OPERADOR");
			usuario.addRole("ALL");
		}
		
		if("Administrador".equals(tipoUsuario)){
			usuario.addRole("ADMIN");
			usuario.addRole("ALL");
		}
	}

}
