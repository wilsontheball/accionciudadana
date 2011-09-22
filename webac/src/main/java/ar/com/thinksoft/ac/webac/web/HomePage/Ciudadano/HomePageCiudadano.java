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
		GPolygon pol1 = new GPolygon("501400FF", 0, 0, "501400FF", 0, 
				new GLatLng(-58.400360,-34.571636),
				new GLatLng(-58.388641,-34.568951),
				new GLatLng(-58.381519,-34.568439),
				new GLatLng(-58.380856,-34.569500),
				new GLatLng(-58.381241,-34.570667),
				new GLatLng(-58.378429,-34.572540),
				new GLatLng(-58.379780,-34.572784),
				new GLatLng(-58.377121,-34.574005),
				new GLatLng(-58.375256,-34.573811),
				new GLatLng(-58.372787,-34.575455),
				new GLatLng(-58.370728,-34.576797),
				new GLatLng(-58.367958,-34.578068),
				new GLatLng(-58.360706,-34.582451),
				new GLatLng(-58.360062,-34.586445),
				new GLatLng(-58.352680,-34.595135),
				new GLatLng(-58.349075,-34.599869),
				new GLatLng(-58.346329,-34.604389),
				new GLatLng(-58.346287,-34.606686),
				new GLatLng(-58.342892,-34.609119),
				new GLatLng(-58.341091,-34.610641),
				new GLatLng(-58.340321,-34.612789),
				new GLatLng(-58.339970,-34.615051),
				new GLatLng(-58.340794,-34.616928),
				new GLatLng(-58.354481,-34.619850),
				new GLatLng(-58.353619,-34.622261),
				new GLatLng(-58.368130,-34.625080),
				new GLatLng(-58.368210,-34.627060),
				new GLatLng(-58.370609,-34.629181),
				new GLatLng(-58.370789,-34.626991),
				new GLatLng(-58.370960,-34.625580),
				new GLatLng(-58.376110,-34.627621),
				new GLatLng(-58.380230,-34.630520),
				new GLatLng(-58.383999,-34.632500),
				new GLatLng(-58.390617,-34.634197),
				new GLatLng(-58.391300,-34.630169),
				new GLatLng(-58.392872,-34.599628),
				new GLatLng(-58.386841,-34.599121),
				new GLatLng(-58.387310,-34.596272),
				new GLatLng(-58.387650,-34.593262),
				new GLatLng(-58.387970,-34.591599),
				new GLatLng(-58.383701,-34.587250),
				new GLatLng(-58.381168,-34.582691),
				new GLatLng(-58.380791,-34.582829),
				new GLatLng(-58.390530,-34.578308),
				new GLatLng(-58.392460,-34.576469),
				new GLatLng(-58.394901,-34.574921),
				new GLatLng(-58.400360,-34.571636));
		map.addOverlay(pol1);
	}
	
}
