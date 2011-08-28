package ar.com.thinksoft.ac.webac.web.usuario.alta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.intac.utils.collections.Comparator;
import ar.com.thinksoft.ac.intac.utils.collections.HArrayList;
import ar.com.thinksoft.ac.webac.predicates.registro.PredicateTodosLosUsuarios;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.reclamo.busquedaReclamo.BusquedaReclamoPage;
import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.IGridColumn;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;

public class UsuarioPage extends BasePage {

	@Override
	public IPermiso getPermisoNecesario() {
		return new UsuarioPagePermiso();
	}

	private String selectedApellido;
	private String selectedNombre;

	public UsuarioPage() {

		add(CSSPackageResource.getHeaderContribution(BusquedaReclamoPage.class, "../../css/UsuarioPage.css"));

		add(new UsuariosForm("form"));
		
		
//		DataGrid grid = this.createTablaUsuarios("grid");
//		this.add(new Label("labelNombre", "Nombre"));
//		this.add(new TextField<String>("campoBusquedaNombre", this.createNameFieldModel()));
//		this.add(new TextField<String>("campoBusquedaApellido", this.createSurnameFieldModel()));
//		this.add(this.createSearchButton("botonBuscar", grid));
//		this.add(this.createNewButton("botonNuevo"));
//		this.add(this.createTablaUsuarios("grid"));

	}

//	private IModel<String> createSurnameFieldModel() {
//		return new IModel<String>() {
//
//			@Override
//			public void detach() {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public String getObject() {
//				return selectedApellido;
//			}
//
//			@Override
//			public void setObject(String apellido) {
//				selectedApellido = apellido;
//			}
//		};
//	}
//
//	private IModel<String> createNameFieldModel() {
//		return new IModel<String>() {
//
//			@Override
//			public void detach() {
//			}
//
//			@Override
//			public String getObject() {
//				return selectedNombre;
//			}
//
//			@Override
//			public void setObject(String nombre) {
//				selectedNombre = nombre;
//			}
//		};
//	}
//
//	private Button createNewButton(String id) {
//
//		Button button = new Button(id) {
//			@Override
//			public void onSubmit() {
//				// HACE ALGO
//			}
//		};
//		return button;
//	}
//
//	private Button createSearchButton(String id, final DataGrid grid) {
//
//		Button button = new Button(id) {
//			@Override
//			public void onSubmit() {
//
//				
//				System.out.println(selectedApellido);
//				System.out.println(selectedNombre);
//				
//				HArrayList<IUsuario> list = HArrayList.toHArrayList(getTodosLosUsuarios());
//				list.filter(new Comparator<IUsuario>() {
//
//					@Override
//					public boolean apply(IUsuario elem) {
//						return elem.getNombre().contains(selectedNombre) ||
//								elem.getApellido().contains(selectedNombre);
//					}
//				});
//
//				ListDataProvider<IUsuario> dataProvider = new ListDataProvider<IUsuario>(list);
//				grid.setDefaultModelObject(new DataProviderAdapter(dataProvider));
//				grid.markAllItemsDirty();
//				grid.update();
//
//			}
//		};
//		return button;
//
//	}
//
//	private DataGrid createTablaUsuarios(String gridName) {
//
//		ListDataProvider<IUsuario> dataProvider = new ListDataProvider<IUsuario>(
//			this.getTodosLosUsuarios());
//		List<IGridColumn> columnas = this.crearColumnas();
//
//		DataGrid grid = new DataGrid(gridName, new DataProviderAdapter(
//			dataProvider), columnas);
//
//		grid.setRowsPerPage(10);
//		grid.setClickRowToSelect(true);
//		grid.setClickRowToDeselect(true);
//		grid.setAllowSelectMultiple(false);
//		grid.setCleanSelectionOnPageChange(true);
//
//		return grid;
//	}
//
//	private List<IUsuario> getTodosLosUsuarios() {
//		return Repository.getInstance().query(new PredicateTodosLosUsuarios());
//	}
//
//	private List<IGridColumn> crearColumnas() {
//		List<IGridColumn> columnas = new ArrayList<IGridColumn>();
//
//		columnas.add(new PropertyColumn("apellido", new Model<String>(
//			"Apellido"), "apellido"));
//		columnas.add(new PropertyColumn("nombre", new Model<String>("Nombre"),
//			"nombre"));
//		columnas.add(new PropertyColumn("nombreUsuario", new Model<String>(
//			"Nombre de Usuario"), "nombreUsuario"));
//		columnas.add(new PropertyColumn("dni", new Model<String>("DNI"), "dni"));
//		columnas.add(new PropertyColumn("mail", new Model<String>("E-Mail"),
//			"mail"));
//		columnas.add(new PropertyColumn("telefono", new Model<String>(
//			"Telefono"), "telefono"));
//
//		return columnas;
//	}
//
}
