package ar.com.thinksoft.ac.webac.web.download;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.resource.FileResourceStream;
import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class DownloadPage extends BasePage{
	
	@Override
	public IPermiso getPermisoNecesario() {
		return new DownloadPagePermiso();
	}
	
	public DownloadPage(PageParameters params){
		Configuracion.getInstance().cargarConfiguracion();
		File apk = new File(Configuracion.getInstance().getPathDownloadApp());
		FileResourceStream stream = new FileResourceStream(apk);
		RequestCycle.get().setRequestTarget(new ResourceStreamRequestTarget(stream, "accionCiudadana.apk"));
	}

}
