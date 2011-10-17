package ar.com.thinksoft.ac.webac.web.configuracion;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;


import ar.com.thinksoft.ac.webac.exceptions.ConfiguracionException;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;

@SuppressWarnings("serial")
public class ConfiguracionForm extends Form<Configuracion> {
	private ConfiguracionForm _self = this;
	
	@SuppressWarnings("rawtypes")
	public ConfiguracionForm(String id) {
		super(id);
		setMultiPart(false);
		
		try {
			Configuracion.getInstance().cargarConfiguracion();
		} catch (ConfiguracionException e) {
			// TODO dialogo error
		}
		CompoundPropertyModel<Configuracion> model = new CompoundPropertyModel<Configuracion>(Configuracion.getInstance());
		setModel(model);
		
		//CONFIGURACION DE PROCESO UNIFICADOR
		add(new TextField<String>("horaUnificador",this.createBind(model,"horaUnificador")));
		add(new TextField<String>("minutoUnificador",this.createBind(model,"minutoUnificador")));
		add(new TextField<String>("manianaOTardeUnificador",this.createBind(model,"manianaOTardeUnificador")));
		//FIN PROCESO UNIFICADOR
		
		//CONFIGURACION DE MAIL
		add(new TextField<String>("smtp",this.createBind(model,"smtp")));
		add(new TextField<String>("puerto",this.createBind(model,"puerto")));
		add(new TextField<String>("desdeMail",this.createBind(model,"desdeMail")));
		add(new CheckBox("TLS", this.createBindBoolean(model,"TLS")));
		add(new CheckBox("auth", this.createBindBoolean(model,"auth")));
		add(new TextField<String>("user",this.createBind(model,"user")));
		add(new PasswordTextField("password",this.createBind(model,"password")));
		//FIN MAIL
		
		//BASE DE DATOS
		add(new TextField<String>("ipBD",this.createBind(model,"ipBD")));
		add(new TextField<String>("portBD",this.createBind(model,"portBD")));
		//FIN BASE DE DATOS
		
		//CONFIGURACION DE RUTAS
		add(new TextField<String>("pathTempImages",this.createBind(model,"pathTempImages")));
		add(new TextField<String>("pathExportDesign",this.createBind(model,"pathExportDesign")));
		add(new TextField<String>("pathConfig",this.createBind(model,"pathConfig")));
		add(new TextField<String>("pathDownloadApp",this.createBind(model,"pathDownloadApp")));
		// FIN RUTAS
		
		
		//GOOGLE MAPS
		add(new TextArea<String>("keyGoogleMap",this.createBind(model, "keyGoogleMap")));
		//FIN GOOGLE MAPS
		
		
		add(new Button("guardarConfig") {
				@Override
				public void onSubmit() {
					Configuracion config = _self.getModelObject();
					try {
						config.guardarConfiguracion();
					} catch (ConfiguracionException e) {
						// TODO dialogo error
					}
					setResponsePage(LoginPage.class);
					}
		        }
	    );
		
		add(new AjaxLink("cancelar") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(LoginPage.class);
			}
		});
		
	}
	
	private IModel<Boolean> createBindBoolean(CompoundPropertyModel<Configuracion> model, String property) {
		return model.bind(property);
	}

	private IModel<String> createBind(CompoundPropertyModel<Configuracion> model,String property){
		return model.bind(property);
	}
	
}
