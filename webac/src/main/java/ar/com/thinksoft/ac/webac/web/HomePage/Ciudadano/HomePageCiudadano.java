package ar.com.thinksoft.ac.webac.web.HomePage.Ciudadano;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;

import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.api.GLatLng;


import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.adminMap.Comuna;
import ar.com.thinksoft.ac.webac.adminMap.ComunaManager;
import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class HomePageCiudadano extends BasePage{
	
	private static String KEY = "ABQIAAAASNhk0DNhWwkPk0Y12RIrThTwM0brOpm-All5BF6PoaKBxRWWERRi58__PuwPgysGGKPkLxYHu8hULg";
	
	@Override
	public IPermiso getPermisoNecesario() {
		return new HomePageCiudadanoPermiso();
	}
	
	@SuppressWarnings("static-access")
	public HomePageCiudadano(final PageParameters parameters){
		add(CSSPackageResource.getHeaderContribution(HomePage.class,"../css/HomeCiudadano.css"));
		add(JavascriptPackageResource.getHeaderContribution(HomePage.class,"../js/homeCiudadano.js"));
		
		GMap2 map = new GMap2("mapa", KEY);
		
		map.setCenter(new GLatLng(-34.611171,-58.444176));
		map.setZoom(12);
		for(Comuna comuna : ComunaManager.getInstance().getListaComunas()){
			map.addOverlay(comuna.getPoligono());
		}
		add(map);
	}
	
}
