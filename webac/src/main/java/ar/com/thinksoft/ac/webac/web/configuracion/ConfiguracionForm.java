package ar.com.thinksoft.ac.webac.web.configuracion;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import ar.com.thinksoft.ac.webac.web.login.LoginPage;

@SuppressWarnings("serial")
public class ConfiguracionForm extends Form<Configuracion> {
	
	private ConfiguracionForm _self = this;
	
	public ConfiguracionForm(String id) {
		super(id);
		setMultiPart(false);
		
		CompoundPropertyModel<Configuracion> model = new CompoundPropertyModel<Configuracion>(new Configuracion());
		setModel(model);
		
		//UNIFICADOR CONFIGURATION
		add(new TextField<String>("horaUnificador",this.createBind(model,"horaUnificador")));
		
		add(new TextField<String>("minutoUnificador",this.createBind(model,"minutoUnificador")));
		
		add(new TextField<String>("manianaOTardeUnificador",this.createBind(model,"manianaOTardeUnificador")));
		
		//MAIL CONFIGURATION
		add(new TextField<String>("smtp",this.createBind(model,"smtp")));
		
		add(new TextField<String>("puerto",this.createBind(model,"puerto")));
		
		add(new TextField<String>("desdeMail",this.createBind(model,"desdeMail")));
		
		//PATHS CONFIGURATION
		add(new TextField<String>("pathTempImages",this.createBind(model,"pathTempImages")));
		
		add(new TextField<String>("pathExportDesign",this.createBind(model,"pathExportDesign")));
		
		add(new TextField<String>("pathConfig",this.createBind(model,"pathConfig")));
		
		add(new TextField<String>("pathDownloadApp",this.createBind(model,"pathDownloadApp")));
		
		add(new TextArea<String>("keyGoogleMap",this.createBind(model,"keyGoogleMap")));
		
		Button guardar =new Button("guardarConfig"){
			@SuppressWarnings("unused")
			@Override
			public void onSubmit(){
				Configuracion config = _self.getModelObject();
			}
		};
		guardar.setDefaultFormProcessing(false);
		add(guardar);
		
		
		SubmitLink cancelar = new SubmitLink("cancelar"){
			public void onSubmit() {
				_self.setResponsePage(LoginPage.class);
			}
		};
		add(cancelar);
		
	}
	
	private IModel<String> createBind(CompoundPropertyModel<Configuracion> model,String property){
		return model.bind(property);
	}
	
}
