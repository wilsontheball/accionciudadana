package ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.predicates.PredicatePorUUID;
import ar.com.thinksoft.ac.webac.reclamo.Reclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;

@SuppressWarnings("serial")
public class DetalleReclamoForm  extends Form<Reclamo>{

	public DetalleReclamoForm(String id, String idReclamo) throws Exception {
		super(id);
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerReclamosFiltradosConPredicates(new PredicatePorUUID().filtrar(idReclamo));
		
		if(lista.size()!= 1)
			throw new Exception("error en la base de datos, por favor, comuniquese con el equipo de soporte tecnico");
		
		CompoundPropertyModel<Reclamo> model = new CompoundPropertyModel<Reclamo>(lista.get(0));
		setModel(model);
		
		add(new Label("calleIncidente", this.createBind(model,"calleIncidente")));
		add(new Label("alturaIncidente",this.createBind(model,"alturaIncidente")));
		add(new Label("latitudIncidente",this.createBind(model,"latitudIncidente")));
		add(new Label("longitudIncidente",this.createBind(model,"longitudIncidente")));
		add(new Label("ciudadanoReclamo",this.createBind(model,"ciudadanoReclamo")));
		add(new Label("prioridad",this.createBind(model,"prioridad")));
		add(new Label("estadoDescripcion",this.createBind(model,"EstadoDescripcion")));
		add(new Label("tipoIncidente", this.createBind(model,"tipoIncidente")));
		add(new Label("observaciones",this.createBind(model, "observaciones")));
		
		setMultiPart(true);
	}
	
	private IModel<String> createBind(CompoundPropertyModel<Reclamo> model,String property){
		return model.bind(property);
	}
	
}
