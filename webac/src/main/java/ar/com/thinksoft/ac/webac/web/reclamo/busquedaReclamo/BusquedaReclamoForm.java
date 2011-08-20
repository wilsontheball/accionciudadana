package ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo;

import java.util.Date;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.PropertyPopulator;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.com.thinksoft.ac.estadosReclamo.EstadoActivo;
import ar.com.thinksoft.ac.intac.*;
import ar.com.thinksoft.ac.webac.HomePage;
import ar.com.thinksoft.ac.webac.reclamo.*;
import ar.com.thinksoft.ac.webac.web.ReclamoDataProvider;

@SuppressWarnings("serial")
public class BusquedaReclamoForm extends Form<IReclamo> {
	
	public BusquedaReclamoForm(String id) {
		super(id);
		CompoundPropertyModel<IReclamo> model = new CompoundPropertyModel<IReclamo>(new Reclamo());
		setModel(model);
		
		add(new TextField<String>("calleIncidente",this.createBind(model,"calleIncidente")));
		add(new TextField<String>("alturaIncidente",this.createBind(model,"alturaIncidente")));
		
		add(new TextField<String>("latitudIncidente",this.createBind(model,"latitudIncidente")));
		add(new TextField<String>("longitudIncidente",this.createBind(model,"longitudIncidente")));
		
		add(new TextField<String>("FechaYHoraReclamo",this.createBind(model,"FechaYHoraReclamo")));
		
		DropDownChoice<String> dropDownListTipo = new DropDownChoice<String>("tipoIncidente", this.createBind(model,"tipoIncidente"),EnumTipoReclamo.getListaTiposReclamo());
		dropDownListTipo.setNullValid(true);
		add(dropDownListTipo);
		
		DropDownChoice<String> dropDownListEstado = new DropDownChoice<String>("Estado", this.createBind(model,"Estado"),EnumEstadosReclamo.getlistaEstadosReclamo());
		dropDownListEstado.setNullValid(true);
		add(dropDownListEstado);
		
		DropDownChoice<String> dropDownListPrioridad = new DropDownChoice<String>("Prioridad", this.createBind(model,"Prioridad"),EnumPrioridadReclamo.getlistaPrioridadReclamo());
		dropDownListPrioridad.setNullValid(true);
		add(dropDownListPrioridad);
		
		//table
		
		/*Reclamo reclamo = new Reclamo("Avellaneda","39","0","0",new Date(),EnumTipoReclamo.abl.getTipo(),"Mati","",null,new EstadoActivo(),EnumPrioridadReclamo.baja.getPrioridad());
		ReclamoManager.getInstance().guardarReclamo(reclamo);
		
		ICellPopulator<IReclamo>[] columns = new ICellPopulator[6];

        columns[0] = new PropertyPopulator<IReclamo>("FechaYHoraReclamo");
        columns[1] = new PropertyPopulator<IReclamo>("tipoIncidente");
        columns[2] = new PropertyPopulator<IReclamo>("Estado");
        columns[3] = new PropertyPopulator<IReclamo>("Prioridad");
        columns[4] = new PropertyPopulator<IReclamo>("calleIncidente");
        columns[5] = new PropertyPopulator<IReclamo>("alturaIncidente");

        add(new DataGridView<IReclamo>("rows", columns, new ReclamoDataProvider(model)));*/
	}

	private IModel<String> createBind(CompoundPropertyModel<IReclamo> model,String property){
		return model.bind(property);
	}
	
	@Override
	protected void onSubmit() {

		IReclamo reclamo = getModelObject();
		//ReclamoManager.getInstance().guardarReclamo(reclamo);
		setResponsePage(BusquedaReclamoPage.class);

	}
}
