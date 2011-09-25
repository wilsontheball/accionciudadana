package ar.com.thinksoft.ac.webac.web.configuracion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import ar.com.thinksoft.ac.webac.repository.Repository;

public class Configuracion {
	
	private static Configuracion instance = null;
	
	public static Configuracion getInstance(){
		if(instance == null){
			instance = new Configuracion();
		}
		return instance;
	}
	
	private String horaUnificador = "0";
	private String minutoUnificador = "0";
	private String manianaOTardeUnificador = "AM";
	
	private String smtp = "smtp.example.com";
	private String port = "puerto";
	private String fromMail ="example@example.com";
	private Boolean TLS = true;
	private Boolean auth = true;
	private String user = "user";
	private String password = "contrasenia";
	
	private String pathTempImages = "/tempImages/";
	private String pathExportDesign = "/export/";
	private String pathConfig = "/pathConfig/configuracion.xml";
	
	public void guardarConfiguracion(){
		Element configElement = new Element("configuracion");
		Document configDocument = new Document(configElement);
		
		configElement.addContent(new Element("horaUnificador").addContent(this.getHoraUnificador()));
		configElement.addContent(new Element("minutoUnificador").addContent(this.getMinutoUnificador()));
		configElement.addContent(new Element("manianaOTardeUnificador").addContent(this.getManianaOTardeUnificador()));
		
		configElement.addContent(new Element("smtp").addContent(this.getSmtp()));
		configElement.addContent(new Element("puerto").addContent(this.getPuerto()));
		configElement.addContent(new Element("desde").addContent(this.getDesdeMail()));
		configElement.addContent(new Element("tls").addContent(this.getTls().toString()));
		configElement.addContent(new Element("auth").addContent(this.getAuth().toString()));
		configElement.addContent(new Element("user").addContent(this.getUser()));
		configElement.addContent(new Element("password").addContent(this.getPassword()));
		
		configElement.addContent(new Element("pathTempImages").addContent(this.getPathTempImages()));
		configElement.addContent(new Element("pathExportDesign").addContent(this.getPathExportDesign()));
		configElement.addContent(new Element("pathConfig").addContent(this.getPathConfig()));
		
		
		XMLOutputter outputter = new XMLOutputter();
		try {
		    outputter.output(configDocument, System.out);
		    File file = new File(this.getPathConfig());
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    
		    FileWriter writer = new FileWriter(this.getPathConfig());
			outputter.output(configDocument, writer);
			writer.close();
		} catch (java.io.IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	public void cargarConfiguracion(){
		
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(this.getPathConfig());
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			this.setHoraUnificador(rootNode.getChildText("horaUnificador"));
			this.setMinutoUnificador(rootNode.getChildText("minutoUnificador"));
			this.setManianaOTardeUnificador(rootNode.getChildText("manianaOTardeUnificador"));
			
			this.setSmtp(rootNode.getChildText("smtp"));
			this.setPuerto(rootNode.getChildText("puerto"));
			this.setDesdeMail(rootNode.getChildText("desde"));
			this.setTLS(Boolean.valueOf(rootNode.getChildText("tls")));
			this.setAuth(Boolean.valueOf(rootNode.getChildText("auth")));
			this.setUser(rootNode.getChildText("user"));
			this.setPassword(rootNode.getChildText("password"));
			
			this.setPathTempImages(rootNode.getChildText("pathTempImages"));
			this.setPathExportDesign(rootNode.getChildText("pathExportDesign"));
			this.setPathConfig(rootNode.getChildText("pathConfig"));
			
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
	public void setPuerto(String port) {
		this.port = port;
	}
	public String getPuerto() {
		return port;
	}
	public void setDesdeMail(String fromMail) {
		this.fromMail = fromMail;
	}
	public String getDesdeMail() {
		return fromMail;
	}
	public void setTLS(Boolean tLS) {
		TLS = tLS;
	}
	public Boolean getTls() {
		return TLS;
	}
	public void setAuth(Boolean auth) {
		this.auth = auth;
	}
	public Boolean getAuth() {
		return auth;
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
