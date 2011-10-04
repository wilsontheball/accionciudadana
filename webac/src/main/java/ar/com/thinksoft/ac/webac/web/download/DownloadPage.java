package ar.com.thinksoft.ac.webac.web.download;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.resource.FileResourceStream;

import ar.com.thinksoft.ac.webac.exceptions.ConfiguracionException;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class DownloadPage extends BasePage{
	
	public DownloadPage(PageParameters params){
		try {
			Configuracion.getInstance().cargarConfiguracion();
		} catch (ConfiguracionException e) {
			// TODO dialogo error
		}
		File apk = new File(Configuracion.getInstance().getPathDownloadApp());
		FileResourceStream stream = new FileResourceStream(apk);
		RequestCycle.get().setRequestTarget(new ResourceStreamRequestTarget(stream, "accionCiudadana.apk"));
	}

}
