package ar.com.thinksoft.ac.webac.web.configuracion;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;

@SuppressWarnings("serial")
public class ConfiguracionForm extends Form<Configuracion> {
	
	private ConfiguracionForm _self = this;
	
	@SuppressWarnings("rawtypes")
	public ConfiguracionForm(String id) {
		super(id);
		setMultiPart(false);
		
		Configuracion.getInstance().cargarConfiguracion();
		
		CompoundPropertyModel<Configuracion> model = new CompoundPropertyModel<Configuracion>(Configuracion.getInstance());
		setModel(model);
		
		//UNIFICADOR CONFIGURATION
		TextField<String> horaUnificador = new TextField<String>("horaUnificador",this.createBind(model,"horaUnificador"));
		add(horaUnificador);
		
		TextField<String> minutoUnificador = new TextField<String>("minutoUnificador",this.createBind(model,"minutoUnificador"));
		add(minutoUnificador);
		
		TextField<String> manianaOTardeUnificador = new TextField<String>("manianaOTardeUnificador",this.createBind(model,"manianaOTardeUnificador"));
		add(manianaOTardeUnificador);
		
		//MAIL CONFIGURATION
		TextField<String> smtp = new TextField<String>("smtp",this.createBind(model,"smtp"));
		add(smtp);
		
		TextField<String> puerto = new TextField<String>("puerto",this.createBind(model,"puerto"));
		add(puerto);
		
		TextField<String> desde = new TextField<String>("desdeMail",this.createBind(model,"desdeMail"));
		add(desde);
		
		CheckBox tls = new CheckBox("tls",this.createBindBoolean(model,"tls"));
		add(tls);
		
		CheckBox auth = new CheckBox("auth",this.createBindBoolean(model,"auth"));
		add(auth);
		
		TextField<String> user = new TextField<String>("user",this.createBind(model,"user"));
		add(user);
		
		PasswordTextField password = new PasswordTextField("password",this.createBind(model,"password"));
		add(password);
		
		//PATHS CONFIGURATION
		TextField<String> pathTempImages = new TextField<String>("pathTempImages",this.createBind(model,"pathTempImages"));
		add(pathTempImages);
		
		TextField<String> pathExportDesign = new TextField<String>("pathExportDesign",this.createBind(model,"pathExportDesign"));
		add(pathExportDesign);
		
		TextField<String> pathConfig = new TextField<String>("pathConfig",this.createBind(model,"pathConfig"));
		add(pathConfig);
		
		TextField<String> pathDownloadApp = new TextField<String>("pathDownloadApp",this.createBind(model,"pathDownloadApp"));
		add(pathDownloadApp);
		
		TextArea<String> keyGoogleMaps = new TextArea<String>("keyGoogleMap",this.createBind(model,"keyGoogleMap"));
		add(keyGoogleMaps);
		
		add(new AjaxLink("guardarConfig"){
			@Override
			public void onClick(AjaxRequestTarget target){
				Configuracion configuracion = _self.getModelObject();
				configuracion.guardarConfiguracion();
				_self.setResponsePage(LoginPage.class);
			}
		});
		
		add(new AjaxLink("cancelar"){
			@Override
			public void onClick(AjaxRequestTarget target){
				_self.setResponsePage(LoginPage.class);
			}
		});
		
	}
	
	private IModel<String> createBind(CompoundPropertyModel<Configuracion> model,String property){
		return model.bind(property);
	}
	
	private IModel<Boolean> createBindBoolean(CompoundPropertyModel<Configuracion> model,String property){
		return model.bind(property);
	}
}
