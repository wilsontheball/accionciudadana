package ar.com.thinksoft.ac.wilsond;

import wicket.contrib.gmap.util.Geocoder;
import java.io.IOException;
import java.io.InputStream;

public class GeocoderChild extends Geocoder{

	private static final long serialVersionUID = 1L;

	public GeocoderChild(String gMapKey) {
		super(gMapKey);
	}
	
	public String[] geocodeCoordenadas(final String coordenadas) throws IOException {
		InputStream is = invokeService(encode(coordenadas));
		if (is != null) {
			try {
				String content = org.apache.wicket.util.io.IOUtils.toString(is);
				String[] lista = content.split(",");
				String calleYAlturaString = lista[2].substring(1);
				String direccion = calleYAlturaString.substring(0, calleYAlturaString.indexOf("-"));
				String[] calleYAltura = new String[2];
				calleYAltura[0] = direccion.substring(0,direccion.lastIndexOf(" "));
				calleYAltura[1] = direccion.substring(direccion.lastIndexOf(" "), direccion.length());
				return calleYAltura;
			} finally {
				is.close();
			}
		}
		return null;
	}

}
