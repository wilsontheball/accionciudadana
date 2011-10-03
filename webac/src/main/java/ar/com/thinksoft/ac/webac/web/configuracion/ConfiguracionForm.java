package ar.com.thinksoft.ac.webac.web.configuracion;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.visural.wicket.behavior.beautytips.BeautyTipBehavior;
import com.visural.wicket.behavior.beautytips.TipPosition;

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
		
		//CONFIGURACION DE RUTAS
		add(new TextField<String>("pathTempImages",this.createBind(model,"pathTempImages")));
		add(new TextField<String>("pathExportDesign",this.createBind(model,"pathExportDesign")));
		add(new TextField<String>("pathConfig",this.createBind(model,"pathConfig")));
		add(new TextField<String>("pathDownloadApp",this.createBind(model,"pathDownloadApp")));
		
		WebComponent help1 = new WebComponent("help1");
		BeautyTipBehavior tooltip1 = new BeautyTipBehavior("Ingrese una ruta v&aacute;lida y el nombre del archivo correcto");
		tooltip1.setPositionPreference(TipPosition.right).setBackgroundColor("white").setDropShadow(true).setShrinkToFit(true);
		help1.add(tooltip1);
		add(help1);
		
		WebComponent help2 = new WebComponent("help2");
		BeautyTipBehavior tooltip2 = new BeautyTipBehavior("Ingrese una ruta v&aacute;lida y el nombre del archivo correcto");
		tooltip2.setPositionPreference(TipPosition.right).setBackgroundColor("white").setDropShadow(true).setShrinkToFit(true);
		help2.add(tooltip2);
		add(help2);
		
		WebComponent help3 = new WebComponent("help3");
		BeautyTipBehavior tooltip3 = new BeautyTipBehavior("Ingrese una ruta v&aacute;lida y el nombre del archivo correcto");
		tooltip3.setPositionPreference(TipPosition.right).setBackgroundColor("white").setDropShadow(true).setShrinkToFit(true);
		help3.add(tooltip3);
		add(help3);
		
		WebComponent help4 = new WebComponent("help4");
		BeautyTipBehavior tooltip4 = new BeautyTipBehavior("Ingrese una ruta v&aacute;lida y el nombre del archivo correcto");
		tooltip4.setPositionPreference(TipPosition.right).setBackgroundColor("white").setDropShadow(true).setShrinkToFit(true);
		help4.add(tooltip4);
		add(help4);
		// FIN RUTAS
		
		
		//GOOGLE MAPS
		add(new TextArea<String>("keyGoogleMap",this.createBind(model, "keyGoogleMap")));
		
		WebComponent helpGoogle = new WebComponent("helpGoogle");
		BeautyTipBehavior tooltipGoogle = new BeautyTipBehavior("Ingrese una clave v&aacute;lida para el dominio");
		tooltipGoogle.setPositionPreference(TipPosition.right).setBackgroundColor("white").setDropShadow(true);
		helpGoogle.add(tooltipGoogle);
		add(helpGoogle);
		//FIN GOOGLE MAPS
		
		
		add(new Button("guardarConfig") {
				@Override
				public void onSubmit() {
					Configuracion config = _self.getModelObject();
					config.guardarConfiguracion();
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
