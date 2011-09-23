package ar.com.thinksoft.ac.webac.web.HomePage.Ciudadano;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.api.GPolygon;


import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class HomePageCiudadano extends BasePage{
	
	private static String KEY = "ABQIAAAASNhk0DNhWwkPk0Y12RIrThTwM0brOpm-All5BF6PoaKBxRWWERRi58__PuwPgysGGKPkLxYHu8hULg";
	
	@Override
	public IPermiso getPermisoNecesario() {
		return new HomePageCiudadanoPermiso();
	}
	
	public HomePageCiudadano(final PageParameters parameters){
		add(CSSPackageResource.getHeaderContribution(HomePage.class,"../css/HomeCiudadano.css"));
		add(JavascriptPackageResource.getHeaderContribution(HomePage.class,"../js/homeCiudadano.js"));
		
		GMap2 map = new GMap2("mapa", KEY);
		
		map.setCenter(new GLatLng(-34.611171,-58.444176));
		map.setZoom(12);
		Float f = Float.valueOf( "0.5" );
		GPolygon pol1 = new GPolygon("#7fff00", 0, 0, "#7fff00", f.floatValue(), 
				new GLatLng(-34.571636,-58.400360),
				new GLatLng(-34.568951,-58.388641),
				new GLatLng(-34.568439,-58.381519),
				new GLatLng(-34.569500,-58.380856),
				new GLatLng(-34.570667,-58.381241),
				new GLatLng(-34.572540,-58.378429),
				new GLatLng(-34.572784,-58.379780),
				new GLatLng(-34.574005,-58.377121),
				new GLatLng(-34.573811,-58.375256),
				new GLatLng(-34.575455,-58.372787),
				new GLatLng(-34.576797,-58.370728),
				new GLatLng(-34.578068,-58.367958),
				new GLatLng(-34.582451,-58.360706),
				new GLatLng(-34.586445,-58.360062),
				new GLatLng(-34.595135,-58.352680),
				new GLatLng(-34.599869,-58.349075),
				new GLatLng(-34.604389,-58.346329),
				new GLatLng(-34.606686,-58.346287),
				new GLatLng(-34.609119,-58.342892),
				new GLatLng(-34.610641,-58.341091),
				new GLatLng(-34.612789,-58.340321),
				new GLatLng(-34.615051,-58.339970),
				new GLatLng(-34.616928,-58.340794),
				new GLatLng(-34.619850,-58.354481),
				new GLatLng(-34.622261,-58.353619),
				new GLatLng(-34.625080,-58.368130),
				new GLatLng(-34.627060,-58.368210),
				new GLatLng(-34.629181,-58.370609),
				new GLatLng(-34.626991,-58.370789),
				new GLatLng(-34.625580,-58.370960),
				new GLatLng(-34.627621,-58.376110),
				new GLatLng(-34.630520,-58.380230),
				new GLatLng(-34.632500,-58.383999),
				new GLatLng(-34.634197,-58.390617),
				new GLatLng(-34.630169,-58.391300),
				new GLatLng(-34.599628,-58.392872),
				new GLatLng(-34.599121,-58.386841),
				new GLatLng(-34.596272,-58.387310),
				new GLatLng(-34.593262,-58.387650),
				new GLatLng(-34.591599,-58.387970),
				new GLatLng(-34.587250,-58.383701),
				new GLatLng(-34.582691,-58.381168),
				new GLatLng(-34.582829,-58.380791),
				new GLatLng(-34.578308,-58.390530),
				new GLatLng(-34.576469,-58.392460),
				new GLatLng(-34.574921,-58.394901),
				new GLatLng(-34.571636,-58.400360));
		
		map.addOverlay(pol1);
		add(map);
	}
	
}
