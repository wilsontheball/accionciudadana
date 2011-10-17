package ar.com.thinksoft.ac.webac.web.HomePage.Ciudadano;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.api.GMarker;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.AccionCiudadanaSession;
import ar.com.thinksoft.ac.webac.exceptions.ConfiguracionException;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.SizeUnit;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;
import com.inmethod.grid.datagrid.DefaultDataGrid;

@AuthorizeInstantiation("CIUDADANO")
public class HomePageCiudadano extends BasePage{
	
	private static String KEY = "";
	private DataGrid gridActivosCiudadano;
	private DataGrid gridUltimosModificadosCiudadano;
	
	public HomePageCiudadano(final PageParameters parameters){
		
		try {
			Configuracion.getInstance().cargarConfiguracion();
		} catch (ConfiguracionException e) {
			//TODO dialogo error
		}
		KEY = Configuracion.getInstance().getKeyGoogleMap();
		
		add(CSSPackageResource.getHeaderContribution(HomePage.class,"../css/Home.css"));
		
		armarGrillaActiva();
        add(gridActivosCiudadano);
        
        armarGrillaUltimosModificados();
        add(gridUltimosModificadosCiudadano);
		
		GMap2 map = crearMapaCiudadano();
		add(map);
	}
	
	private GMap2 crearMapaCiudadano() {
		GMap2 map = new GMap2("mapaCiudadano", KEY);
		map.setCenter(new GLatLng(-34.611171,-58.444176));
		map.setZoom(12);
		map.setDraggingEnabled(true);
		map.setDoubleClickZoomEnabled(true);
		map.setScrollWheelZoomEnabled(true);
		List<IReclamo> listReclamos = ReclamoManager.getInstance().obtenerTodosReclamos();
		for(IReclamo reclamo : listReclamos){
			if(reclamo.getCiudadanoGeneradorReclamo().equals(((AccionCiudadanaSession)getSession()).getUsuario().getNombreUsuario()) && reclamo.isNotDown()){
				double latitud = Double.valueOf(reclamo.getLatitudIncidente());
				double longitud = Double.valueOf(reclamo.getLongitudIncidente());
				map.addOverlay(new GMarker(new GLatLng(latitud,longitud)));
			}
		}
		
		return map;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrillaActiva() {
		List<IReclamo> listReclamos = ReclamoManager.getInstance().obtenerTodosReclamos();
		List<IReclamo> listCiudadano = new ArrayList<IReclamo>();
		
		for(IReclamo reclamo : listReclamos){
			if(reclamo.getCiudadanoGeneradorReclamo().equals(((AccionCiudadanaSession)getSession()).getUsuario().getNombreUsuario()) && reclamo.isNotDown())
				listCiudadano.add(reclamo);
		}
		
		ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(listCiudadano);
		List cols = (List) Arrays.asList(
																	
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente").setInitialSize(120)
            																						 .setResizable(false)
            																						 .setWrapText(true)
            																						 .setSizeUnit(SizeUnit.PX),
            																						 
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(50)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																			  
            new PropertyColumn("comunaCol",new Model<String>("Comuna"), "comunaIncidente").setInitialSize(80)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																				
            new PropertyColumn("fechaCol",new Model<String>("Fecha de alta"), "FechaReclamo").setInitialSize(80)
            																				.setReorderable(true)
            																				.setResizable(true)
            																		 		.setSizeUnit(SizeUnit.PX),
            																						
            new PropertyColumn("tipoCol",new Model<String>("Tipo"), "tipoIncidente").setInitialSize(130)
            																		.setReorderable(true)
            																		.setResizable(true)
            																		.setSizeUnit(SizeUnit.PX),
            																					 
            new PropertyColumn("prioridadCol",new Model<String>("Prioridad"), "Prioridad").setInitialSize(80)
            																				 .setReorderable(true)
            																				 .setResizable(true)
            																				 .setSizeUnit(SizeUnit.PX),
            																				 
            new PropertyColumn("estadoCol",new Model<String>("Estado"), "EstadoDescripcion").setInitialSize(80)
                 																				.setReorderable(true)
                 																				.setResizable(true)
                 																				.setSizeUnit(SizeUnit.PX)
            );
		
		gridActivosCiudadano = new DefaultDataGrid("gridCiudadano", new DataProviderAdapter(listDataProvider), cols);
		gridActivosCiudadano.setRowsPerPage(7);
        gridActivosCiudadano.setClickRowToSelect(true);
        gridActivosCiudadano.setAllowSelectMultiple(true);
        gridActivosCiudadano.setClickRowToDeselect(true);
        gridActivosCiudadano.setCleanSelectionOnPageChange(false);
        
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrillaUltimosModificados() {
		List<IReclamo> listReclamos = listaOrdenadaPorFecha();
		
		ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(listReclamos);
		List cols = (List) Arrays.asList(
																	
            new PropertyColumn("calleCol",new Model<String>("Calle del Incidente"), "calleIncidente").setInitialSize(120)
            																						 .setResizable(false)
            																						 .setWrapText(true)
            																						 .setSizeUnit(SizeUnit.PX),
            																						 
            new PropertyColumn("alturaCol",new Model<String>("Altura"), "alturaIncidente").setInitialSize(50)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																			  
            new PropertyColumn("comunaCol",new Model<String>("Comuna"), "comunaIncidente").setInitialSize(80)
            																			  .setWrapText(true)
            																			  .setReorderable(true)
            																			  .setResizable(true)
            																			  .setSizeUnit(SizeUnit.PX),
            																				
            new PropertyColumn("fechaCol",new Model<String>("Fecha de modificacion"), "FechaUltimaModificacionReclamo")
            																				.setInitialSize(80)
            																				.setReorderable(true)
            																				.setResizable(true)
            																		 		.setSizeUnit(SizeUnit.PX),
            																						
            new PropertyColumn("tipoCol",new Model<String>("Tipo"), "tipoIncidente").setInitialSize(130)
            																		.setReorderable(true)
            																		.setResizable(true)
            																		.setSizeUnit(SizeUnit.PX),
            																					 
            new PropertyColumn("prioridadCol",new Model<String>("Prioridad"), "Prioridad").setInitialSize(80)
            																				 .setReorderable(true)
            																				 .setResizable(true)
            																				 .setSizeUnit(SizeUnit.PX),
           
            new PropertyColumn("estadoCol",new Model<String>("Estado"), "EstadoDescripcion").setInitialSize(80)
             																				.setReorderable(true)
             																				.setResizable(true)
             																				.setSizeUnit(SizeUnit.PX)
            );
		
		gridUltimosModificadosCiudadano = new DefaultDataGrid("gridUltimosModificadosCiudadano", new DataProviderAdapter(listDataProvider), cols);
		gridUltimosModificadosCiudadano.setRowsPerPage(7);
		gridUltimosModificadosCiudadano.setClickRowToSelect(true);
		gridUltimosModificadosCiudadano.setAllowSelectMultiple(true);
		gridUltimosModificadosCiudadano.setClickRowToDeselect(true);
		gridUltimosModificadosCiudadano.setCleanSelectionOnPageChange(false);
        
	}

	/*
	 * Lista ordenada por fecha de modificacion = lista invertida, ya que al modificar un reclamo se
	 * esta creando uno nuevo clonado y los atributos actualizados.
	 */
	private List<IReclamo> listaOrdenadaPorFecha() {
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerTodosReclamos();
		List<IReclamo> listaDevolucion = new ArrayList<IReclamo>();
		for(int i = lista.size()-1 ;i>=0;i--){
			if(lista.get(i).getCiudadanoGeneradorReclamo().equals(((AccionCiudadanaSession)getSession()).getUsuario().getNombreUsuario()))
				listaDevolucion.add(lista.get(i));
		}
		return listaDevolucion;
	
	}
}
