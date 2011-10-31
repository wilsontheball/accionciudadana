package ar.com.thinksoft.ac.webac.web.usuario.alta;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.db4o.ObjectSet;
import ar.com.thinksoft.ac.webac.exceptions.MailException;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.mail.MailManager;
import ar.com.thinksoft.ac.webac.predicates.registro.PredicateUsuarioExistente;
import ar.com.thinksoft.ac.webac.registro.RegistroManager;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.EnumTiposUsuario;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.web.registro.RegistroPage;
import ar.com.thinksoft.ac.webac.web.usuario.form.UsuarioPage;

public class UsuarioNuevoForm extends Form<Usuario> {

	private static final long serialVersionUID = 4530512782651195546L;
	private UsuarioNuevoForm _self = this;
	
	@SuppressWarnings({ "serial", "rawtypes" })
	public UsuarioNuevoForm(String id) {
		super(id);

		CompoundPropertyModel<Usuario> model = new CompoundPropertyModel<Usuario>(new Usuario());
		this.setModel(model);

		DropDownChoice<String> dropDownTipoUsuario = new DropDownChoice<String>("tipoUsuario", createStringBind(model,"tipo"), EnumTiposUsuario.getlistaTiposUsuarios());
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
		add(new Label("errorLabel",""));
		
		add(new Button("guardarRegistro"){
			@Override
			public void onSubmit() {
				Usuario usuario = _self.getModelObject();
				_self.convertUsuario(usuario.getTipo(), usuario);
				ObjectSet<Usuario> usuarios = Repository.getInstance().query(new PredicateUsuarioExistente().exist(usuario.getNombreUsuario()));
				
				if(usuarios.size()==0 && !_self.usuarioIsValido(usuario)){
					new RegistroManager().registrar(usuario);
					try {
						MailManager.getInstance().enviarMail(usuario.getMail(), "Acción Ciudadana - Bienvenido", MailManager.getInstance().armarTextoBienvenida(usuario));
					} catch (MailException e) {
						LogFwk.getInstance(RegistroPage.class).error("No se pudo enviar el mail de bienvenida. Detalle: " + e.getMessage());
					}
					setResponsePage(UsuarioPage.class);
					setRedirect(true);
				}else{
					_self.addOrReplace(new Label("errorLabel",
							"El nombre de usuario ya se encuentra en nuestra Base de Datos. Por favor, ingrese otro." +
				                    			"Si el problema persiste, no dude en consultar al soporte técnico."));
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

	private IModel<String> createStringBind(CompoundPropertyModel<Usuario> model, String property) {
		return model.bind(property);
	}

	protected void convertUsuario(String tipoUsuario, Usuario usuario) {
		if(EnumTiposUsuario.Ciudadano.getTipoUsuario().equals(tipoUsuario)){
			usuario.addRole("CIUDADANO");
			usuario.addRole("ALL");
		}
		
		if(EnumTiposUsuario.Operador.getTipoUsuario().equals(tipoUsuario)){
			usuario.addRole("OPERADOR");
			usuario.addRole("ALL");
		}
		
		if(EnumTiposUsuario.Administrador.getTipoUsuario().equals(tipoUsuario)){
			usuario.addRole("ADMIN");
			usuario.addRole("ALL");
		}
	}
	
	protected boolean usuarioIsValido(Usuario usuario) {
		return 	isStringOrNull(usuario.getTipo()) && isStringOrNull(usuario.getApellido()) && isStringOrNull(usuario.getNombre()) &&
				isStringOrNull(usuario.getNombreUsuario()) && isStringOrNull(usuario.getContrasenia()) &&
				isStringOrNull(usuario.getMail());
	}
	
	private boolean isStringOrNull(String string){
		return string == "" || string == null;
	}

}
