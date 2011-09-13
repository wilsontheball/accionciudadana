package ar.com.thinksoft.ac.webac.web.usuario.alta;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.intac.utils.collections.Comparator;
import ar.com.thinksoft.ac.intac.utils.collections.HArrayList;
import ar.com.thinksoft.ac.webac.predicates.registro.PredicateTodosLosUsuarios;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.repository.Repository;
import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.IGridColumn;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;

public class UsuariosForm extends Form<UsuarioFilterObject> {

	private DataGrid grid;
	private UsuariosForm _self = this;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5848749487206180482L;

	@SuppressWarnings("serial")
	public UsuariosForm(String id) {

		super(id);
		CompoundPropertyModel<UsuarioFilterObject> model = new CompoundPropertyModel<UsuarioFilterObject>(
			new UsuarioFilterObject());
		this.setModel(model);


		this.add(new TextField<String>("campoBusquedaNombre", this.createBind(model, "nombre")));
		this.add(new TextField<String>("campoBusquedaApellido", this.createBind(model, "apellido")));
		
		this.add(new Button("botonBuscar"){
			
			@Override
			public void onSubmit() {

				final UsuarioFilterObject filterObject = _self.getModelObject();

				HArrayList<IUsuario> list = HArrayList.toHArrayList(getTodosLosUsuarios());

				List<IUsuario> data = list.filter(new Comparator<IUsuario>() {
					@Override
					public boolean apply(IUsuario elem) {
						return filterObject.getApellido().toLowerCase().contains(elem.getApellido().toLowerCase()) ||
							filterObject.getNombre().toLowerCase().contains(elem.getNombre().toLowerCase());
					}
				});

				grid.setDefaultModelObject(toDataProvider(data));
			}
		});
		
		this.add(this.createNewButton("botonNuevo"));
		this.add(createTablaUsuarios("grid"));

	}

	/*
	 * COMPONENTS
	 */

	private DataGrid createTablaUsuarios(String gridName) {

		ListDataProvider<IUsuario> dataProvider = new ListDataProvider<IUsuario>(
			this.getTodosLosUsuarios());
		List<IGridColumn> columnas = this.crearColumnas();

		DataGrid grid = new DataGrid(gridName, new DataProviderAdapter(
			dataProvider), columnas);

		grid.setRowsPerPage(10);
		grid.setClickRowToSelect(true);
		grid.setClickRowToDeselect(true);
		grid.setAllowSelectMultiple(false);
		grid.setCleanSelectionOnPageChange(true);

		return grid;
	}

	private Button createNewButton(String id) {

		Button button = new Button(id) {
			@Override
			public void onSubmit() {
				setDefaultFormProcessing(true);
			}
		};

		button.setDefaultFormProcessing(false);
		return button;
	}


	private List<IUsuario> getTodosLosUsuarios() {
		return Repository.getInstance().query(new PredicateTodosLosUsuarios());
	}

	private List<IGridColumn> crearColumnas() {
		List<IGridColumn> columnas = new ArrayList<IGridColumn>();

		columnas.add(new PropertyColumn("apellido", new Model<String>(
			"Apellido"), "apellido"));
		columnas.add(new PropertyColumn("nombre", new Model<String>("Nombre"),
			"nombre"));
		columnas.add(new PropertyColumn("nombreUsuario", new Model<String>(
			"Nombre de Usuario"), "nombreUsuario"));
		columnas.add(new PropertyColumn("dni", new Model<String>("DNI"), "dni"));
		columnas.add(new PropertyColumn("mail", new Model<String>("E-Mail"),
			"mail"));
		columnas.add(new PropertyColumn("telefono", new Model<String>(
			"Telefono"), "telefono"));

		return columnas;
	}

	/*
	 * AUXILIARES
	 */

	private IModel<String> createBind(CompoundPropertyModel<UsuarioFilterObject> model, String property) {
		return model.bind(property);
	}

	private DataProviderAdapter toDataProvider(List<IUsuario> list) {
		ListDataProvider<IUsuario> listDataProvider = new ListDataProvider<IUsuario>(list);
		return new DataProviderAdapter(listDataProvider);
	}
}
