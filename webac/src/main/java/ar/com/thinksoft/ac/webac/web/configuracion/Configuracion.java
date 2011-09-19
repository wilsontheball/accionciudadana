package ar.com.thinksoft.ac.webac.web.configuracion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class Configuracion {
	
	private String horaUnificador = "0";
	private String minutoUnificador = "0";
	private String manianaOTardeUnificador = "AM";
	
	private String smtp = "smtp.example.com";
	private String port = "puerto";
	private String fromMail ="example@example.com";
	private boolean TLS = true;
	private boolean auth = true;
	private String user = "user";
	private String password = "contrasenia";
	
	private String pathTempImages = "";
	private String pathExportDesign = "";
	private String pathConfig = "";
	
	public void guardarConfiguracion(){
		Element configElement = new Element("configuracion");
		Document configDocument = new Document(configElement);
		
		configElement.addContent(new Element("horaUnificador").addContent(this.getHoraUnificador()));
		configElement.addContent(new Element("minutoUnificador").addContent(this.getMinutoUnificador()));
		configElement.addContent(new Element("manianaOTardeUnificador").addContent(this.getManianaOTardeUnificador()));
		
		configElement.addContent(new Element("smtp").addContent(this.getSmtp()));
		configElement.addContent(new Element("puerto").addContent(this.getPort()));
		configElement.addContent(new Element("desde").addContent(this.getFromMail()));
		configElement.addContent(new Element("tls").addContent(this.getTls()));
		configElement.addContent(new Element("auth").addContent(this.getAuth()));
		configElement.addContent(new Element("user").addContent(this.getUser()));
		configElement.addContent(new Element("password").addContent(this.getPassword()));
		
		configElement.addContent(new Element("pathTempImages").addContent(this.getPathTempImages()));
		configElement.addContent(new Element("pathExportDesign").addContent(this.getPathExportDesign()));
		configElement.addContent(new Element("pathConfig").addContent(this.getPathConfig()));
		
		
		XMLOutputter outputter = new XMLOutputter();
		try {
		    outputter.output(configDocument, System.out);
		    FileWriter writer = new FileWriter("/some/directory/myFile.xml");
			outputter.output(configDocument, writer);
			writer.close();
		} catch (java.io.IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	public void cargarConfiguracion(){
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("c:\\file.xml");
		
		try {
			 
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			Element node = rootNode.getChild("staff");
	 
	 
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
	}
	
	public void setHoraUnificador(String horaUnificador) {
		this.horaUnificador = horaUnificador;
	}
	public String getHoraUnificador() {
		return horaUnificador;
	}
	public void setMinutoUnificador(String minutoUnificador) {
		this.minutoUnificador = minutoUnificador;
	}
	public String getMinutoUnificador() {
		return minutoUnificador;
	}
	public void setManianaOTardeUnificador(String manianaOTardeUnificador) {
		this.manianaOTardeUnificador = manianaOTardeUnificador;
	}
	public String getManianaOTardeUnificador() {
		return manianaOTardeUnificador;
	}
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	public String getSmtp() {
		return smtp;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPort() {
		return port;
	}
	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}
	public String getFromMail() {
		return fromMail;
	}
	public void setTLS(String tLS) {
		TLS = Boolean.getBoolean(tLS);
	}
	public String getTls() {
		return String.valueOf(TLS);
	}
	public void setAuth(String auth) {
		this.auth = Boolean.getBoolean(auth);
	}
	public String getAuth() {
		return String.valueOf(auth);
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUser() {
		return user;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPathTempImages(String pathTempImages) {
		this.pathTempImages = pathTempImages;
	}
	public String getPathTempImages() {
		return pathTempImages;
	}
	public void setPathExportDesign(String pathExportDesign) {
		this.pathExportDesign = pathExportDesign;
	}
	public String getPathExportDesign() {
		return pathExportDesign;
	}
	public void setPathConfig(String pathConfig) {
		this.pathConfig = pathConfig;
	}
	public String getPathConfig() {
		return pathConfig;
	}
	
	

}
