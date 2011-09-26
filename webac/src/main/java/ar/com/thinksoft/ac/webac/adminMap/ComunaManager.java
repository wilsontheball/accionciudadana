package ar.com.thinksoft.ac.webac.adminMap;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.EnumBarriosReclamo;
import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.webac.reclamo.ReclamoManager;

import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.api.GPolygon;

public class ComunaManager {
	
	private String colorLinea = "#000000";
	private String colorCritico = "#FF0000";
	private String colorIntermedio = "#FFFF00";
	private String colorPasable = "#33FF00";
	private float opacity = Float.valueOf("0.5");
	private static int UMBRAL_MINIMO = 10;
	private static int UMBRAL_MAXIMO = 25;
	private List<Comuna> listaComunas = new ArrayList<Comuna>();
	
	public ComunaManager(){
		crearComunas();
	}
	
	public Comuna crearComuna(String nombre, String colorEstado, GLatLng... gLatLngs){
		GPolygon poligono = new GPolygon(getColorLinea(), 1, 1, colorEstado, getOpacity(), gLatLngs);
		return new Comuna(nombre,poligono);
	}
	
	private String getEstadoComuna(List<IReclamo> reclamos, String comuna) {
		float reclamosNotDown = 0, reclamosComuna = 0;
		for(IReclamo reclamo: reclamos){
			if(reclamo.isNotDown()){
				reclamosNotDown = reclamosNotDown + 1;
				if(comuna.equals(reclamo.getComunaIncidente()))
					reclamosComuna = reclamosComuna + 1;
			}
		}
		
		if(reclamosComuna == 0)
			return getColorPasable();
		else{
			float estadistica = (reclamosComuna/reclamosNotDown)*100;
			if(estadistica<UMBRAL_MINIMO)
				return getColorPasable();
			if(estadistica>UMBRAL_MAXIMO)
				return getColorCritico();
			if(estadistica>=UMBRAL_MINIMO && estadistica<=UMBRAL_MAXIMO)
				return getColorIntermedio();
		}
		return "";
	}
	
	/*
	 * METODO ASQUEROSO CREADOR DE LAS 15 COMUNAS DE LA CIUDAD
	 */
	private void crearComunas() {
		List<IReclamo> reclamos = ReclamoManager.getInstance().obtenerTodosReclamos();
		String estado = "";
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna1.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna1.getComuna(),estado,
				new GLatLng(-34.571636,-58.400360),	new GLatLng(-34.568951,-58.388641), new GLatLng(-34.568439,-58.381519),
				new GLatLng(-34.569500,-58.380856), new GLatLng(-34.570667,-58.381241), new GLatLng(-34.572540,-58.378429),
				new GLatLng(-34.572784,-58.379780), new GLatLng(-34.574005,-58.377121), new GLatLng(-34.573811,-58.375256),
				new GLatLng(-34.575455,-58.372787), new GLatLng(-34.576797,-58.370728), new GLatLng(-34.578068,-58.367958),
				new GLatLng(-34.582451,-58.360706), new GLatLng(-34.586445,-58.360062), new GLatLng(-34.595135,-58.352680),
				new GLatLng(-34.599869,-58.349075), new GLatLng(-34.604389,-58.346329), new GLatLng(-34.606686,-58.346287),
				new GLatLng(-34.609119,-58.342892), new GLatLng(-34.610641,-58.341091), new GLatLng(-34.612789,-58.340321),
				new GLatLng(-34.615051,-58.339970), new GLatLng(-34.616928,-58.340794), new GLatLng(-34.619850,-58.354481),
				new GLatLng(-34.622261,-58.353619), new GLatLng(-34.625080,-58.368130), new GLatLng(-34.627060,-58.368210),
				new GLatLng(-34.629181,-58.370609), new GLatLng(-34.626991,-58.370789), new GLatLng(-34.625580,-58.370960),
				new GLatLng(-34.627621,-58.376110), new GLatLng(-34.630520,-58.380230), new GLatLng(-34.632500,-58.383999),
				new GLatLng(-34.634197,-58.390617), new GLatLng(-34.630169,-58.391300), new GLatLng(-34.599628,-58.392872),
				new GLatLng(-34.599121,-58.386841), new GLatLng(-34.596272,-58.387310), new GLatLng(-34.593262,-58.387650),
				new GLatLng(-34.591599,-58.387970), new GLatLng(-34.587250,-58.383701), new GLatLng(-34.582691,-58.381168),
				new GLatLng(-34.582829,-58.380791), new GLatLng(-34.578308,-58.390530), new GLatLng(-34.576469,-58.392460),
				new GLatLng(-34.574921,-58.394901), new GLatLng(-34.571636,-58.400360)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna2.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna2.getComuna(),estado,
				new GLatLng(-34.576302,-58.392689), new GLatLng(-34.578239,-58.390701), new GLatLng(-34.582748,-58.381390),
				new GLatLng(-34.587891,-58.384178), new GLatLng(-34.591671,-58.387691), new GLatLng(-34.594921,-58.387482),
				new GLatLng(-34.599190,-58.386879), new GLatLng(-34.599579,-58.391991), new GLatLng(-34.599720,-58.398338),
				new GLatLng(-34.599300,-58.402088), new GLatLng(-34.597809,-58.404598), new GLatLng(-34.597988,-58.411041),
				new GLatLng(-34.597809,-58.417049), new GLatLng(-34.593571,-58.414902), new GLatLng(-34.589413,-58.410442),
				new GLatLng(-34.586720,-58.408718), new GLatLng(-34.583542,-58.406319), new GLatLng(-34.583359,-58.403568),
				new GLatLng(-34.583328,-58.402069), new GLatLng(-34.578880,-58.396881), new GLatLng(-34.576302,-58.392689)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna3.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna3.getComuna(),estado,
				new GLatLng(-34.597988,-58.413361), new GLatLng(-34.597881,-58.404770), new GLatLng(-34.599350,-58.401989),
				new GLatLng(-34.599701,-58.398380), new GLatLng(-34.599651,-58.392929), new GLatLng(-34.607780,-58.392239),
				new GLatLng(-34.611729,-58.391991), new GLatLng(-34.613708,-58.392071), new GLatLng(-34.624237,-58.391518),
				new GLatLng(-34.627239,-58.391209), new GLatLng(-34.627731,-58.394901), new GLatLng(-34.628399,-58.398510),
				new GLatLng(-34.629391,-58.408119), new GLatLng(-34.630379,-58.411812), new GLatLng(-34.614170,-58.412930),
				new GLatLng(-34.610809,-58.414429), new GLatLng(-34.605900,-58.414989), new GLatLng(-34.597988,-58.413361)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna4.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna4.getComuna(),estado,
				new GLatLng(-34.619717,-58.354355), new GLatLng(-34.617100,-58.341171), new GLatLng(-34.618835,-58.338345),
				new GLatLng(-34.620529,-58.337532), new GLatLng(-34.621979,-58.337017), new GLatLng(-34.622791,-58.337746),
				new GLatLng(-34.626534,-58.335342), new GLatLng(-34.627419,-58.335342), new GLatLng(-34.633984,-58.349503),
				new GLatLng(-34.638115,-58.357960), new GLatLng(-34.639774,-58.361607), new GLatLng(-34.641998,-58.358131),
				new GLatLng(-34.644402,-58.357616), new GLatLng(-34.645599,-58.359417), new GLatLng(-34.647789,-58.362164),
				new GLatLng(-34.648109,-58.364140), new GLatLng(-34.650368,-58.368645), new GLatLng(-34.651711,-58.370361),
				new GLatLng(-34.653687,-58.370106), new GLatLng(-34.653889,-58.371731), new GLatLng(-34.653679,-58.370190),
				new GLatLng(-34.655979,-58.373837), new GLatLng(-34.657040,-58.378986), new GLatLng(-34.656288,-58.376881),
				new GLatLng(-34.657497,-58.380875), new GLatLng(-34.657536,-58.384438), new GLatLng(-34.662289,-58.392159),
				new GLatLng(-34.662010,-58.397991), new GLatLng(-34.662052,-58.398342), new GLatLng(-34.659538,-58.395851),
				new GLatLng(-34.658482,-58.396790), new GLatLng(-34.658340,-58.398602), new GLatLng(-34.660320,-58.400230),
				new GLatLng(-34.660179,-58.407440), new GLatLng(-34.657848,-58.411732), new GLatLng(-34.659473,-58.417828),
				new GLatLng(-34.661690,-58.424129), new GLatLng(-34.661041,-58.424858), new GLatLng(-34.660210,-58.425522),
				new GLatLng(-34.658821,-58.426750), new GLatLng(-34.653049,-58.434490), new GLatLng(-34.655708,-58.438011),
				new GLatLng(-34.650860,-58.444000), new GLatLng(-34.650860,-58.443958), new GLatLng(-34.649799,-58.443272),
				new GLatLng(-34.646591,-58.436062), new GLatLng(-34.645031,-58.432110), new GLatLng(-34.641708,-58.432461),
				new GLatLng(-34.638149,-58.411301), new GLatLng(-34.635250,-58.410179), new GLatLng(-34.632641,-58.411980),
				new GLatLng(-34.630451,-58.411900), new GLatLng(-34.629318,-58.408119), new GLatLng(-34.628609,-58.399971),
				new GLatLng(-34.627346,-58.391304), new GLatLng(-34.634258,-58.390610), new GLatLng(-34.632500,-58.383919),
				new GLatLng(-34.630241,-58.379845), new GLatLng(-34.628796,-58.378109), new GLatLng(-34.627411,-58.376019),
				new GLatLng(-34.625614,-58.371071), new GLatLng(-34.629360,-58.370682), new GLatLng(-34.627258,-58.368172),
				new GLatLng(-34.624939,-58.367950), new GLatLng(-34.622227,-58.353752), new GLatLng(-34.620476,-58.354568),
				new GLatLng(-34.619717,-58.354355)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna5.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna5.getComuna(),estado,
				new GLatLng(-34.597961,-58.426731), new GLatLng(-34.598869,-58.429089), new GLatLng(-34.599350,-58.429920),
				new GLatLng(-34.600151,-58.430462), new GLatLng(-34.602051,-58.432178), new GLatLng(-34.602741,-58.433441),
				new GLatLng(-34.605919,-58.430740), new GLatLng(-34.610870,-58.430309), new GLatLng(-34.615421,-58.430031),
				new GLatLng(-34.615089,-58.429455), new GLatLng(-34.615139,-58.429298), new GLatLng(-34.628540,-58.426659),
				new GLatLng(-34.640339,-58.423740), new GLatLng(-34.639172,-58.417561), new GLatLng(-34.638081,-58.411282),
				new GLatLng(-34.635380,-58.410461), new GLatLng(-34.632759,-58.411621), new GLatLng(-34.630959,-58.411732),
				new GLatLng(-34.629181,-58.411919), new GLatLng(-34.614059,-58.412930), new GLatLng(-34.610458,-58.414471),
				new GLatLng(-34.606060,-58.414921), new GLatLng(-34.602551,-58.414349), new GLatLng(-34.597969,-58.413361),
				new GLatLng(-34.597778,-58.418190), new GLatLng(-34.597691,-58.423660), new GLatLng(-34.597961,-58.426731)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna6.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna6.getComuna(),estado,
				new GLatLng(-34.626991,-58.427094), new GLatLng(-34.615189,-58.429581), new GLatLng(-34.615162,-58.429371),
				new GLatLng(-34.615444,-58.430058), new GLatLng(-34.605942,-58.430870), new GLatLng(-34.602901,-58.433182),
				new GLatLng(-34.605511,-58.439190), new GLatLng(-34.607529,-58.445839), new GLatLng(-34.604279,-58.458759),
				new GLatLng(-34.607250,-58.462799), new GLatLng(-34.617451,-58.457989), new GLatLng(-34.630379,-58.451641),
				new GLatLng(-34.626991,-58.427094)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna7.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna7.getComuna(),estado,
				new GLatLng(-34.627129,-58.427132), new GLatLng(-34.640091,-58.423962), new GLatLng(-34.641640,-58.432541),
				new GLatLng(-34.644932,-58.432110), new GLatLng(-34.647469,-58.438381), new GLatLng(-34.648991,-58.441250),
				new GLatLng(-34.649738,-58.442928), new GLatLng(-34.650379,-58.443588), new GLatLng(-34.650841,-58.443939),
				new GLatLng(-34.647110,-58.449059), new GLatLng(-34.656609,-58.460140), new GLatLng(-34.651169,-58.466961),
				new GLatLng(-34.648769,-58.464939), new GLatLng(-34.646870,-58.463139), new GLatLng(-34.641251,-58.469231),
				new GLatLng(-34.622189,-58.477859), new GLatLng(-34.617420,-58.468590), new GLatLng(-34.613323,-58.459927),
				new GLatLng(-34.630402,-58.451698), new GLatLng(-34.627129,-58.427132)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna8.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna8.getComuna(),estado,
				new GLatLng(-34.661591,-58.424091), new GLatLng(-34.663712,-58.425636), new GLatLng(-34.664558,-58.427696),
				new GLatLng(-34.666950,-58.429321), new GLatLng(-34.671120,-58.432411), new GLatLng(-34.704361,-58.460991),
				new GLatLng(-34.699390,-58.468498), new GLatLng(-34.694271,-58.475632), new GLatLng(-34.674011,-58.501720),
				new GLatLng(-34.671909,-58.497990), new GLatLng(-34.669250,-58.494701), new GLatLng(-34.663422,-58.488800),
				new GLatLng(-34.662441,-58.485859), new GLatLng(-34.659142,-58.480610), new GLatLng(-34.655621,-58.476212),
				new GLatLng(-34.652451,-58.472820), new GLatLng(-34.649090,-58.469749), new GLatLng(-34.656651,-58.459961),
				new GLatLng(-34.647190,-58.448978), new GLatLng(-34.655521,-58.437820), new GLatLng(-34.652981,-58.434212),
				new GLatLng(-34.657349,-58.428890), new GLatLng(-34.661591,-58.424091)  
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna9.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna9.getComuna(),estado,
				new GLatLng(-34.673851,-58.501549), new GLatLng(-34.671970,-58.498180), new GLatLng(-34.669209,-58.494640),
				new GLatLng(-34.663460,-58.488819), new GLatLng(-34.662380,-58.485802), new GLatLng(-34.660252,-58.482456),
				new GLatLng(-34.659599,-58.481297), new GLatLng(-34.658821,-58.480221), new GLatLng(-34.655689,-58.476398),
				new GLatLng(-34.652679,-58.473164), new GLatLng(-34.649380,-58.469795), new GLatLng(-34.650860,-58.467091),
				new GLatLng(-34.647015,-58.463142), new GLatLng(-34.645672,-58.464561), new GLatLng(-34.644402,-58.465935),
				new GLatLng(-34.643024,-58.467350), new GLatLng(-34.641819,-58.469059), new GLatLng(-34.636528,-58.471558),
				new GLatLng(-34.638927,-58.476234), new GLatLng(-34.637020,-58.478802), new GLatLng(-34.638988,-58.483181),
				new GLatLng(-34.644821,-58.495281), new GLatLng(-34.643410,-58.497189), new GLatLng(-34.645840,-58.502338),
				new GLatLng(-34.640049,-58.509701), new GLatLng(-34.634399,-58.511631), new GLatLng(-34.633099,-58.515110),
				new GLatLng(-34.633099,-58.517559), new GLatLng(-34.633244,-58.520435), new GLatLng(-34.633739,-58.521339),
				new GLatLng(-34.634258,-58.524078), new GLatLng(-34.634972,-58.527340), new GLatLng(-34.634602,-58.529835),
				new GLatLng(-34.634708,-58.529942), new GLatLng(-34.634548,-58.530041), new GLatLng(-34.654388,-58.528801),
				new GLatLng(-34.673851,-58.501549)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna10.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna10.getComuna(),estado,
				new GLatLng(-34.636978,-58.478630), new GLatLng(-34.638889,-58.476101), new GLatLng(-34.636452,-58.471550),
				new GLatLng(-34.622261,-58.477901), new GLatLng(-34.624691,-58.482712), new GLatLng(-34.622959,-58.484299),
				new GLatLng(-34.615261,-58.492279), new GLatLng(-34.611980,-58.496010), new GLatLng(-34.608521,-58.500179),
				new GLatLng(-34.612541,-58.506660), new GLatLng(-34.616749,-58.513088), new GLatLng(-34.620419,-58.517040),
				new GLatLng(-34.611382,-58.529011), new GLatLng(-34.615829,-58.531071), new GLatLng(-34.629601,-58.530392),
				new GLatLng(-34.632359,-58.529999), new GLatLng(-34.634499,-58.529984), new GLatLng(-34.634789,-58.527340),
				new GLatLng(-34.634190,-58.523819), new GLatLng(-34.633827,-58.522003), new GLatLng(-34.633171,-58.520351),
				new GLatLng(-34.633060,-58.515018), new GLatLng(-34.634441,-58.511551), new GLatLng(-34.639912,-58.509750),
				new GLatLng(-34.645760,-58.502346), new GLatLng(-34.643440,-58.497169), new GLatLng(-34.644749,-58.495201),
				new GLatLng(-34.636978,-58.478630)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna11.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna11.getComuna(),estado,
				new GLatLng(-34.624660,-58.482540), new GLatLng(-34.618690,-58.471249), new GLatLng(-34.613430,-58.459881),
				new GLatLng(-34.607250,-58.462879), new GLatLng(-34.604382,-58.458931), new GLatLng(-34.601768,-58.469021),
				new GLatLng(-34.603958,-58.471809), new GLatLng(-34.605160,-58.474079), new GLatLng(-34.600960,-58.477772),
				new GLatLng(-34.600182,-58.476830), new GLatLng(-34.600201,-58.476849), new GLatLng(-34.599442,-58.479229),
				new GLatLng(-34.597729,-58.483311), new GLatLng(-34.596828,-58.497089), new GLatLng(-34.594585,-58.502712),
				new GLatLng(-34.593933,-58.503571), new GLatLng(-34.588821,-58.508202), new GLatLng(-34.581989,-58.515369),
				new GLatLng(-34.586884,-58.517517), new GLatLng(-34.589462,-58.518719), new GLatLng(-34.591812,-58.519794),
				new GLatLng(-34.611309,-58.529011), new GLatLng(-34.620239,-58.517170), new GLatLng(-34.616821,-58.513050),
				new GLatLng(-34.608410,-58.500050), new GLatLng(-34.617378,-58.490002), new GLatLng(-34.624660,-58.482540)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna12.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna12.getComuna(),estado,
				new GLatLng(-34.549969,-58.500130), new GLatLng(-34.548576,-58.497433), new GLatLng(-34.544319,-58.488079),
				new GLatLng(-34.541508,-58.481964), new GLatLng(-34.538822,-58.475868), new GLatLng(-34.543892,-58.471981),
				new GLatLng(-34.548771,-58.468288), new GLatLng(-34.550388,-58.471119), new GLatLng(-34.552299,-58.474731),
				new GLatLng(-34.562130,-58.466492), new GLatLng(-34.566509,-58.473610), new GLatLng(-34.572731,-58.468029),
				new GLatLng(-34.593719,-58.503391), new GLatLng(-34.582062,-58.515320), new GLatLng(-34.549969,-58.500130)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna13.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna13.getComuna(),estado,
				new GLatLng(-34.572041,-58.440701), new GLatLng(-34.567989,-58.448910), new GLatLng(-34.566280,-58.447559),
				new GLatLng(-34.564251,-58.443939), new GLatLng(-34.562222,-58.440498), new GLatLng(-34.563148,-58.436119),
				new GLatLng(-34.562038,-58.435051), new GLatLng(-34.561771,-58.437820), new GLatLng(-34.561329,-58.438999),
				new GLatLng(-34.560822,-58.440029), new GLatLng(-34.559299,-58.441380), new GLatLng(-34.558651,-58.441448),
				new GLatLng(-34.558060,-58.441189), new GLatLng(-34.557289,-58.440369), new GLatLng(-34.556839,-58.439602),
				new GLatLng(-34.555752,-58.437069), new GLatLng(-34.554951,-58.436039), new GLatLng(-34.554211,-58.434731),
				new GLatLng(-34.553951,-58.434132), new GLatLng(-34.553680,-58.434521), new GLatLng(-34.553200,-58.434940),
				new GLatLng(-34.549831,-58.428459), new GLatLng(-34.547211,-58.430401), new GLatLng(-34.544529,-58.431938),
				new GLatLng(-34.544689,-58.431389), new GLatLng(-34.544670,-58.431473), new GLatLng(-34.544636,-58.431602),
				new GLatLng(-34.543541,-58.433060), new GLatLng(-34.540428,-58.436581), new GLatLng(-34.538857,-58.440292),
				new GLatLng(-34.536663,-58.442780), new GLatLng(-34.535690,-58.446472), new GLatLng(-34.534966,-58.449047),
				new GLatLng(-34.531750,-58.452457), new GLatLng(-34.532009,-58.456871), new GLatLng(-34.532333,-58.460613),
				new GLatLng(-34.534824,-58.466232), new GLatLng(-34.538761,-58.475670), new GLatLng(-34.548981,-58.468079),
				new GLatLng(-34.552509,-58.474640), new GLatLng(-34.562408,-58.466320), new GLatLng(-34.566620,-58.473438),
				new GLatLng(-34.569439,-58.470909), new GLatLng(-34.572689,-58.468121), new GLatLng(-34.572300,-58.467388),
				new GLatLng(-34.574711,-58.463310), new GLatLng(-34.578259,-58.460442), new GLatLng(-34.578331,-58.457802),
				new GLatLng(-34.580818,-58.449959), new GLatLng(-34.583118,-58.444599), new GLatLng(-34.580818,-58.442410),
				new GLatLng(-34.578449,-58.439991), new GLatLng(-34.575401,-58.444149), new GLatLng(-34.572041,-58.440701)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna14.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna14.getComuna(),estado,
				new GLatLng(-34.568981,-58.388721), new GLatLng(-34.570366,-58.395145), new GLatLng(-34.571636,-58.400402),
				new GLatLng(-34.576191,-58.392841), new GLatLng(-34.579369,-58.397652), new GLatLng(-34.583050,-58.401859),
				new GLatLng(-34.583328,-58.406319), new GLatLng(-34.589260,-58.410229), new GLatLng(-34.593788,-58.415199),
				new GLatLng(-34.597778,-58.417179), new GLatLng(-34.597672,-58.423569), new GLatLng(-34.596050,-58.426960),
				new GLatLng(-34.594250,-58.430141), new GLatLng(-34.588951,-58.438332), new GLatLng(-34.584278,-58.444561),
				new GLatLng(-34.583359,-58.444511), new GLatLng(-34.582340,-58.444000), new GLatLng(-34.581532,-58.443272),
				new GLatLng(-34.578449,-58.440010), new GLatLng(-34.575340,-58.444038), new GLatLng(-34.574039,-58.442841),
				new GLatLng(-34.572021,-58.440651), new GLatLng(-34.567848,-58.448891), new GLatLng(-34.566330,-58.447651),
				new GLatLng(-34.564571,-58.444340), new GLatLng(-34.562199,-58.440571), new GLatLng(-34.563049,-58.436272),
				new GLatLng(-34.563400,-58.436489), new GLatLng(-34.561989,-58.434990), new GLatLng(-34.561699,-58.438210),
				new GLatLng(-34.560291,-58.440739), new GLatLng(-34.559120,-58.441380), new GLatLng(-34.557671,-58.440948),
				new GLatLng(-34.556969,-58.439838), new GLatLng(-34.555660,-58.436958), new GLatLng(-34.554562,-58.435371),
				new GLatLng(-34.553959,-58.434090), new GLatLng(-34.553329,-58.434940), new GLatLng(-34.551449,-58.432030),
				new GLatLng(-34.549812,-58.428425), new GLatLng(-34.557236,-58.411560), new GLatLng(-34.563469,-58.403999),
				new GLatLng(-34.563610,-58.400051), new GLatLng(-34.565731,-58.396790), new GLatLng(-34.568981,-58.388721)
		));
		
		estado = getEstadoComuna(reclamos,EnumBarriosReclamo.Comuna15.getComuna());
		getListaComunas().add(crearComuna(EnumBarriosReclamo.Comuna15.getComuna(),estado,
				new GLatLng(-34.593929,-58.430649), new GLatLng(-34.595982,-58.427052), new GLatLng(-34.597672,-58.423740),
				new GLatLng(-34.598061,-58.427181), new GLatLng(-34.599232,-58.429710), new GLatLng(-34.600960,-58.431210),
				new GLatLng(-34.601910,-58.432152), new GLatLng(-34.602551,-58.433231), new GLatLng(-34.604099,-58.436062),
				new GLatLng(-34.605412,-58.439190), new GLatLng(-34.607559,-58.446270), new GLatLng(-34.604351,-58.458462),
				new GLatLng(-34.601768,-58.469059), new GLatLng(-34.603642,-58.471470), new GLatLng(-34.605061,-58.474079),
				new GLatLng(-34.600990,-58.477730), new GLatLng(-34.600040,-58.476921), new GLatLng(-34.599651,-58.478630),
				new GLatLng(-34.597710,-58.483269), new GLatLng(-34.596790,-58.497089), new GLatLng(-34.595058,-58.501808),
				new GLatLng(-34.594318,-58.503010), new GLatLng(-34.593891,-58.503609), new GLatLng(-34.581421,-58.483139),
				new GLatLng(-34.577080,-58.475281), new GLatLng(-34.574001,-58.470219), new GLatLng(-34.572411,-58.467430),
				new GLatLng(-34.574818,-58.463337), new GLatLng(-34.576511,-58.461941), new GLatLng(-34.578308,-58.460350),
				new GLatLng(-34.578281,-58.458809), new GLatLng(-34.578300,-58.457886), new GLatLng(-34.578812,-58.456429),
				new GLatLng(-34.579510,-58.454041), new GLatLng(-34.580639,-58.450439), new GLatLng(-34.583191,-58.444599),
				new GLatLng(-34.584530,-58.444431), new GLatLng(-34.588242,-58.439449), new GLatLng(-34.591629,-58.434132),
				new GLatLng(-34.593929,-58.430649)
		));
		
	}

	/*
	 * GETTERS Y SETTERS
	 */
	public void setColorLinea(String colorLinea) {
		this.colorLinea = colorLinea;
	}

	public String getColorLinea() {
		return colorLinea;
	}

	public void setColorCritico(String colorCritico) {
		this.colorCritico = colorCritico;
	}

	public String getColorCritico() {
		return colorCritico;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}

	public float getOpacity() {
		return opacity;
	}

	public void setColorPasable(String colorPasable) {
		this.colorPasable = colorPasable;
	}

	public String getColorPasable() {
		return colorPasable;
	}

	public  void setColorIntermedio(String colorIntermedio) {
		this.colorIntermedio = colorIntermedio;
	}

	public String getColorIntermedio() {
		return colorIntermedio;
	}

	public void setListaComunas(List<Comuna> listaComunas) {
		this.listaComunas = listaComunas;
	}

	public List<Comuna> getListaComunas() {
		return listaComunas;
	}
	
}
