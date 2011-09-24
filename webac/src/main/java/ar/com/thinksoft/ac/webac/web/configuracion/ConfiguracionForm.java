package ar.com.thinksoft.ac.webac.web.configuracion;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;

@SuppressWarnings("serial")
public class ConfiguracionForm extends Form<Configuracion> {
	
	private ConfiguracionForm _self = this;
	
	public ConfiguracionForm(String id) {
		super(id);
		setMultiPart(false);
		
		CompoundPropertyModel<Configuracion> model = new CompoundPropertyModel<Configuracion>(new Configuracion());
		setModel(model);
		
		Configuracion.getInstance().cargarConfiguracion();
		
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
		
		TextField<String> password = new TextField<String>("password",this.createBind(model,"password"));
		add(password);
		
		//PATHS CONFIGURATION
		TextField<String> pathTempImages = new TextField<String>("pathTempImages",this.createBind(model,"pathTempImages"));
		add(pathTempImages);
		
		TextField<String> pathExportDesign = new TextField<String>("pathExportDesign",this.createBind(model,"pathExportDesign"));
		add(pathExportDesign);
		
		TextField<String> pathConfig = new TextField<String>("pathConfig",this.createBind(model,"pathConfig"));
		add(pathConfig);
		
		add(new Button("guardarConfig"){
			@Override
			public void onSubmit(){
				Configuracion configuracion = _self.getModelObject();
				configuracion.guardarConfiguracion();
			}
		});
		
		add(new Button("cancelar"){
			@Override
			public void onSubmit(){
				_self.setResponsePage(HomePage.class);
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
