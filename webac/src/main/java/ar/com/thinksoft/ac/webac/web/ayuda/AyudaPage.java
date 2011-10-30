package ar.com.thinksoft.ac.webac.web.ayuda;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.resource.FileResourceStream;

import ar.com.thinksoft.ac.webac.AccionCiudadanaSession;
import ar.com.thinksoft.ac.webac.exceptions.ConfiguracionException;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.web.base.BasePage;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class AyudaPage  extends BasePage{
	
	public AyudaPage(PageParameters params){
		try {
			Configuracion.getInstance().cargarConfiguracion();
		} catch (ConfiguracionException e) {
			LogFwk.getInstance(AyudaPage.class).error("Problema al cargar la configuracion. Detalle: " + e.getMessage());
		}
		
		String nombreArchivo = getNombreArchivoPorTipoUsuario();
		
		File manual = new File(Configuracion.getInstance().getPathDownloadApp() + nombreArchivo);
		FileResourceStream stream = new FileResourceStream(manual);
		RequestCycle.get().setRequestTarget(new ResourceStreamRequestTarget(stream, nombreArchivo));
	}

	private String getNombreArchivoPorTipoUsuario() {
		
		AccionCiudadanaSession session = (AccionCiudadanaSession) getSession();
		Roles rolesAdmin = new Roles();
		rolesAdmin.add("ADMIN");
		
		if (session.getRoles().hasAnyRole(rolesAdmin)){
			return "Accion Ciudadana - Administrador.pdf";
		}
		
		Roles rolesOper = new Roles();
		rolesOper.add("OPERADOR");
		if (session.getRoles().hasAnyRole(rolesOper)){
			return "Accion Ciudadana - Operador.pdf";
		}
		
		Roles rolesCiudadano = new Roles();
		rolesCiudadano.add("CIUDADANO");
		if (session.getRoles().hasAnyRole(rolesCiudadano)){
			return "Accion Ciudadana - Ciudadano.pdf";
		}
		
		Roles noneRole = new Roles();
		noneRole.add("NONE");
		if (session.getRoles().hasAnyRole(noneRole)){
			return "Accion Ciudadana - Ciudadano.pdf";
		}
		
		return "";
	}

}
