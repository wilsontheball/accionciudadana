package ar.com.thinksoft.ac.webac.web.download;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.resource.FileResourceStream;
import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class DownloadPage extends BasePage{
	
	private static final String PATH = "src/main/webapp/download/";
	
	@Override
	public IPermiso getPermisoNecesario() {
		return new DownloadPagePermiso();
	}
	
	public DownloadPage(PageParameters params){
		File apk = new File(PATH + "accionCiudadana.apk");
		FileResourceStream stream = new FileResourceStream(apk);
		RequestCycle.get().setRequestTarget(new ResourceStreamRequestTarget(stream, "accionCiudadana.apk"));
	}

}
