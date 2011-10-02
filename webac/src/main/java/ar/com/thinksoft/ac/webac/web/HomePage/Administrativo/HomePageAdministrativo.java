package ar.com.thinksoft.ac.webac.web.HomePage.Administrativo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

import com.inmethod.grid.DataProviderAdapter;
import com.inmethod.grid.SizeUnit;
import com.inmethod.grid.column.PropertyColumn;
import com.inmethod.grid.datagrid.DataGrid;
import com.inmethod.grid.datagrid.DefaultDataGrid;

import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.api.GIcon;
import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.api.GMarker;
import wicket.contrib.gmap.api.GMarkerOptions;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.adminMap.Comuna;
import ar.com.thinksoft.ac.webac.adminMap.ComunaManager;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;
import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class HomePageAdministrativo extends BasePage{
	
	private static String KEY = "";
	private DataGrid gridActivos;
	private DataGrid gridUltimosModificados;
	
	@Override
	public IPermiso getPermisoNecesario() {
		return new HomePageAdministrativoPermiso();
	}
	
	public HomePageAdministrativo(final PageParameters parameters){
		
		Configuracion.getInstance().cargarConfiguracion();
		KEY = Configuracion.getInstance().getKeyGoogleMap();
		
		add(CSSPackageResource.getHeaderContribution(HomePage.class,"../css/Home.css"));
		
		armarGrillaActiva();
        add(gridActivos);
        
        armarGrillaUltimosModificados();
        add(gridUltimosModificados);
		
		GMap2 map = crearMapaAdministrativo();
		add(map);
	}

	private GMap2 crearMapaAdministrativo() {
		GMap2 map = new GMap2("mapa", KEY);
		map.setCenter(new GLatLng(-34.611171,-58.444176));
		map.setZoom(12);
		map.setDraggingEnabled(false);
		
		ComunaManager comunaManager = new ComunaManager();
		for(Comuna comuna : comunaManager.getListaComunas()){
			map.addOverlay(comuna.getPoligono());
		}
		crearMarcadoresComunas(map);
		return map;
	}
	
	private void crearMarcadoresComunas(GMap2 map) {
		
		GIcon iconoComuna1 = new GIcon("/images/comuna1.jpg");
		iconoComuna1.setShadow(null);
		iconoComuna1.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.603541,-58.360405), new GMarkerOptions("comuna1", iconoComuna1)));
		
		GIcon iconoComuna2 = new GIcon("/images/comuna2.jpg");
		iconoComuna2.setShadow(null);
		iconoComuna2.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.587856,-58.396797), new GMarkerOptions("comuna2", iconoComuna2)));
		
		GIcon iconoComuna3 = new GIcon("/images/comuna3.jpg");
		iconoComuna3.setShadow(null);
		iconoComuna3.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.60891,-58.401947), new GMarkerOptions("comuna3", iconoComuna3)));
		
		GIcon iconoComuna4 = new GIcon("/images/comuna4.jpg");
		iconoComuna4.setShadow(null);
		iconoComuna4.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.643377,-58.392334), new GMarkerOptions("comuna4", iconoComuna4)));
		
		GIcon iconoComuna5 = new GIcon("/images/comuna5.jpg");
		iconoComuna5.setShadow(null);
		iconoComuna5.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.617952,-58.420143), new GMarkerOptions("comuna5", iconoComuna5)));
		
		GIcon iconoComuna6 = new GIcon("/images/comuna6.jpg");
		iconoComuna6.setShadow(null);
		iconoComuna6.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.615127,-58.444176), new GMarkerOptions("comuna6", iconoComuna6)));
		
		GIcon iconoComuna7 = new GIcon("/images/comuna7.jpg");
		iconoComuna7.setShadow(null);
		iconoComuna7.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.63801,-58.451385), new GMarkerOptions("comuna7", iconoComuna7)));
		
		GIcon iconoComuna8 = new GIcon("/images/comuna8.jpg");
		iconoComuna8.setShadow(null);
		iconoComuna8.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.675006,-58.461342), new GMarkerOptions("comuna8", iconoComuna8)));
		
		GIcon iconoComuna9 = new GIcon("/images/comuna9.jpg");
		iconoComuna9.setShadow(null);
		iconoComuna9.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.654392,-58.507347), new GMarkerOptions("comuna9", iconoComuna9)));
		
		GIcon iconoComuna10 = new GIcon("/images/comuna10.jpg");
		iconoComuna10.setShadow(null);
		iconoComuna10.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.627558,-58.502541), new GMarkerOptions("comuna10", iconoComuna10)));
		
		GIcon iconoComuna11 = new GIcon("/images/comuna11.jpg");
		iconoComuna11.setShadow(null);
		iconoComuna11.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.602694,-58.505287), new GMarkerOptions("comuna11", iconoComuna11)));
		
		GIcon iconoComuna12 = new GIcon("/images/comuna12.jpg");
		iconoComuna12.setShadow(null);
		iconoComuna12.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.565948,-58.491898), new GMarkerOptions("comuna12", iconoComuna12)));
		
		GIcon iconoComuna13 = new GIcon("/images/comuna13.jpg");
		iconoComuna13.setShadow(null);
		iconoComuna13.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.555487,-58.450699), new GMarkerOptions("comuna13", iconoComuna13)));
		
		GIcon iconoComuna14 = new GIcon("/images/comuna14.jpg");
		iconoComuna14.setShadow(null);
		iconoComuna14.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.572733,-58.418427), new GMarkerOptions("comuna14", iconoComuna14)));
		
		GIcon iconoComuna15 = new GIcon("/images/comuna15.jpg");
		iconoComuna15.setShadow(null);
		iconoComuna15.setShadowSize(null);
		map.addOverlay(new GMarker(new GLatLng(-34.588139,-58.468552), new GMarkerOptions("comuna15", iconoComuna15)));
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void armarGrillaActiva() {
		List<IReclamo> listReclamos = ReclamoManager.getInstance().obtenerTodosReclamos();
		List<IReclamo> listNotDownReclamos = new ArrayList<IReclamo>();
		for(IReclamo reclamo : listReclamos){
			if(reclamo.isNotDown())
				listNotDownReclamos.add(reclamo);
		}
			
		ListDataProvider<IReclamo> listDataProvider = new ListDataProvider<IReclamo>(listNotDownReclamos);
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
		
		gridActivos = new DefaultDataGrid("grid", new DataProviderAdapter(listDataProvider), cols);
		gridActivos.setRowsPerPage(7);
        gridActivos.setClickRowToSelect(true);
        gridActivos.setAllowSelectMultiple(true);
        gridActivos.setClickRowToDeselect(true);
        gridActivos.setCleanSelectionOnPageChange(false);
        
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
		
		gridUltimosModificados = new DefaultDataGrid("gridUltimosModificados", new DataProviderAdapter(listDataProvider), cols);
		gridUltimosModificados.setRowsPerPage(7);
		gridUltimosModificados.setClickRowToSelect(true);
		gridUltimosModificados.setAllowSelectMultiple(true);
		gridUltimosModificados.setClickRowToDeselect(true);
		gridUltimosModificados.setCleanSelectionOnPageChange(false);
        
	}

	/*
	 * Lista ordenada por fecha de modificacion = lista invertida, ya que al modificar un reclamo se
	 * esta creando uno nuevo clonado y los atributos actualizados.
	 */
	private List<IReclamo> listaOrdenadaPorFecha() {
		List<IReclamo> lista = ReclamoManager.getInstance().obtenerTodosReclamos();
		List<IReclamo> listaDevolucion = new ArrayList<IReclamo>();
		for(int i = lista.size()-1 ;i>=0;i--){
			listaDevolucion.add(lista.get(i));
		}
		return listaDevolucion;
	
	}
}
