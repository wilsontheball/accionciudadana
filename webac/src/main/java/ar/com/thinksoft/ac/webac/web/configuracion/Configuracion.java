package ar.com.thinksoft.ac.webac.web.configuracion;

import java.io.File;
import java.io.FileWriter;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import ar.com.thinksoft.ac.webac.logging.LogFwk;

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
	
	private String pathTempImages = "src/main/webapp/tempImages/";
	private String pathExportDesign = "src/main/webapp/export/";
	private String pathConfig = "src/main/webapp/configuracion.xml";
	private String keyGoogleMap = "ABQIAAAASNhk0DNhWwkPk0Y12RIrThTwM0brOpm-All5BF6PoaKBxRWWERRi58__PuwPgysGGKPkLxYHu8hULg";
	
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
		configElement.addContent(new Element("keyGoogleMap").addContent(this.getKeyGoogleMap()));
		
		XMLOutputter outputter = new XMLOutputter();
		try {
		    File file = new File(this.pathConfig);
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    
		    FileWriter writer = new FileWriter(this.pathConfig);
			outputter.output(configDocument, writer);
			writer.close();
		} catch (java.io.IOException e) {
			LogFwk.getInstance(Configuracion.class).error("Error al escribir el archivo de configuracion. Creacion abortada");
		}
		
	}
	
	public void cargarConfiguracion(){
		
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(this.pathConfig);
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
			this.setKeyGoogleMap(rootNode.getChildText("keyGoogleMap"));
			
		  } catch (Exception io) {
			  LogFwk.getInstance(Configuracion.class).error("No se encontro ningun archivo de configuracion. Se creara uno automaticamente");
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

	public void setKeyGoogleMap(String keyGoogleMap) {
		this.keyGoogleMap = keyGoogleMap;
	}

	public String getKeyGoogleMap() {
		return keyGoogleMap;
	}

}
