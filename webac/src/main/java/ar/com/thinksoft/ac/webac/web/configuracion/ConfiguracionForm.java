package ar.com.thinksoft.ac.webac.web.configuracion;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

@SuppressWarnings("serial")
public class ConfiguracionForm extends Form<Configuracion> {
	
	public ConfiguracionForm(String id) {
		super(id);
		setMultiPart(false);
		
		CompoundPropertyModel<Configuracion> model = new CompoundPropertyModel<Configuracion>(new Configuracion());
		setModel(model);
		
		
	}
}
