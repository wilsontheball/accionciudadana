package ar.com.thinksoft.ac.webac.web.download;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.resource.FileResourceStream;

import ar.com.thinksoft.ac.webac.exceptions.ConfiguracionException;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class DownloadPage extends BasePage{
	
	public DownloadPage(PageParameters params){
		try {
			Configuracion.getInstance().cargarConfiguracion();
		} catch (ConfiguracionException e) {
			LogFwk.getInstance(DownloadPage.class).error("Error leyendo archivo de configuracion. Detalle: " + e.getMessage());
		}
		
		String parametro = params.getString("type");
		
		if(parametro.equals("appAndroid")){
			File apk = new File(Configuracion.getInstance().getPathDownloadApp() + "accionCiudadana.apk");
			FileResourceStream stream = new FileResourceStream(apk);
			RequestCycle.get().setRequestTarget(new ResourceStreamRequestTarget(stream, "accionCiudadana.apk"));
		}else{
			if(parametro.equals("manualAndroid")){
				String nombreArchivo = "Accion Ciudadana - Aplicacion Android.pdf";
				File manual = new File(Configuracion.getInstance().getPathDownloadApp() + nombreArchivo);
				FileResourceStream stream = new FileResourceStream(manual);
				RequestCycle.get().setRequestTarget(new ResourceStreamRequestTarget(stream, nombreArchivo));
			}
		}
		
	}

}
