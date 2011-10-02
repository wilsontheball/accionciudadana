package ar.com.thinksoft.ac.webac.web.usuario.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.intac.utils.collections.Comparator;
import ar.com.thinksoft.ac.intac.utils.collections.HArrayList;
import ar.com.thinksoft.ac.intac.utils.string.StringUtils;
import ar.com.thinksoft.ac.webac.predicates.registro.PredicateTodosLosUsuarios;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.UsuarioFactory;
import ar.com.thinksoft.ac.webac.web.usuario.alta.UsuarioNuevoPage;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.IGridColumn;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DefaultDataGrid;

public class UsuariosForm extends Form<UsuarioFilterObject> {

	private DefaultDataGrid grid;
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

		this.add(new TextField<String>("campoBusquedaNombre", this.createBind(
				model, "nombre")));
		this.add(new TextField<String>("campoBusquedaApellido", this
				.createBind(model, "apellido")));
		this.add(new TextField<String>("campoBusquedaNombreUsuario", this
				.createBind(model, "nombreUsuario")));

		this.add(this.createNewButton("botonNuevo"));
		this.createTablaUsuarios("grid");
		this.add(this.grid);

		this.add(new Button("botonBuscar") {

			@Override
			public void onSubmit() {

				final UsuarioFilterObject filterObject = _self.getModelObject();

				HArrayList<IUsuario> list = HArrayList
						.toHArrayList(getTodosLosUsuarios());

				List<IUsuario> data = list.filter(new Comparator<IUsuario>() {
					@Override
					public boolean apply(IUsuario elem) {

						if (filterObject.isNull()) {
							return true;
						} else
							return StringUtils.contains(elem.getApellido(),
									filterObject.getApellido())
									|| StringUtils.contains(elem.getNombre(),
											filterObject.getNombre())
									|| StringUtils.contains(
											elem.getNombreUsuario(),
											filterObject.getNombreUsuario());

					}
				});

				grid.setDefaultModelObject(toDataProvider(data));
			}
		});

		this.add(new Button("eliminar") {

			@Override
			public void onSubmit() {

				IUsuario usuario = (IUsuario) grid.getSelectedItems()
						.iterator().next().getObject();
				Repository.getInstance().delete(usuario);
				_self.setResponsePage(UsuarioPage.class);
			}

		});

		this.add(new Button("bloquear") {

			@Override
			public void onSubmit() {

				IUsuario usuario = (IUsuario) grid.getSelectedItems()
						.iterator().next().getObject();
				new UsuarioFactory().bloquear(usuario);
				_self.setResponsePage(UsuarioPage.class);
			}

		});

	
	}

	/*
	 * COMPONENTS
	 */

	private void createTablaUsuarios(String gridName) {

		ListDataProvider<IUsuario> dataProvider = new ListDataProvider<IUsuario>(
				this.getTodosLosUsuarios());
		List<IGridColumn> columnas = this.crearColumnas();

		DefaultDataGrid grid = new DefaultDataGrid(gridName,
				new DataProviderAdapter(dataProvider), columnas);

		grid.setRowsPerPage(10);
		grid.setClickRowToSelect(true);
		grid.setClickRowToDeselect(true);
		grid.setAllowSelectMultiple(false);
		grid.setCleanSelectionOnPageChange(true);

		this.grid = grid;
	}

	private Button createNewButton(String id) {

		Button button = new Button(id) {
			@Override
			public void onSubmit() {
				setResponsePage(UsuarioNuevoPage.class);
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

	private IModel<String> createBind(
			CompoundPropertyModel<UsuarioFilterObject> model, String property) {
		return model.bind(property);
	}

	private DataProviderAdapter toDataProvider(List<IUsuario> list) {
		ListDataProvider<IUsuario> listDataProvider = new ListDataProvider<IUsuario>(
				list);
		return new DataProviderAdapter(listDataProvider);
	}
}
