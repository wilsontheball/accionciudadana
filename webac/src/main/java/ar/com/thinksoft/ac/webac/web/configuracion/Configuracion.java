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
	private String pathDownloadApp = "src/main/webapp/download/accionCiudadana.apk";
	private String keyGoogleMap = "ABQIAAAASNhk0DNhWwkPk0Y12RIrThTwM0brOpm-All5BF6PoaKBxRWWERRi58__PuwPgysGGKPkLxYHu8hULg";
	
	public void guardarConfiguracion(){
		Element configElement = new Element("configuracion");
		Document configDocument = new Document(configElement);
		
		configElement.addContent(new Element("horaUnificador").addContent(getHoraUnificador()));
		configElement.addContent(new Element("minutoUnificador").addContent(getMinutoUnificador()));
		configElement.addContent(new Element("manianaOTardeUnificador").addContent(getManianaOTardeUnificador()));
		
		configElement.addContent(new Element("smtp").addContent(getSmtp()));
		configElement.addContent(new Element("puerto").addContent(getPuerto()));
		configElement.addContent(new Element("desde").addContent(getDesdeMail()));
		configElement.addContent(new Element("tls").addContent(getTls().toString()));
		configElement.addContent(new Element("auth").addContent(getAuth().toString()));
		configElement.addContent(new Element("user").addContent(getUser()));
		configElement.addContent(new Element("password").addContent(getPassword()));
		
		configElement.addContent(new Element("pathTempImages").addContent(getPathTempImages()));
		configElement.addContent(new Element("pathExportDesign").addContent(getPathExportDesign()));
		configElement.addContent(new Element("pathConfig").addContent(getPathConfig()));
		configElement.addContent(new Element("pathDownloadApp").addContent(getPathDownloadApp()));
		configElement.addContent(new Element("keyGoogleMap").addContent(getKeyGoogleMap()));
		
		XMLOutputter outputter = new XMLOutputter();
		try {
		    File file = new File(getPathConfig());
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    
		    FileWriter writer = new FileWriter(getPathConfig());
			outputter.output(configDocument, writer);
			outputter.output(configDocument, System.out);
			writer.flush();
            writer.close();
            
		} catch (java.io.IOException e) {
			LogFwk.getInstance(Configuracion.class).error("Error al escribir el archivo de configuracion. Creacion abortada");
			System.out.println("falla el guardado");
		}
		
	}
	
	public void cargarConfiguracion(){
		
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(getPathConfig());
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			setHoraUnificador(rootNode.getChildText("horaUnificador"));
			setMinutoUnificador(rootNode.getChildText("minutoUnificador"));
			setManianaOTardeUnificador(rootNode.getChildText("manianaOTardeUnificador"));
			
			setSmtp(rootNode.getChildText("smtp"));
			setPuerto(rootNode.getChildText("puerto"));
			setDesdeMail(rootNode.getChildText("desde"));
			setTLS(Boolean.valueOf(rootNode.getChildText("tls")));
			setAuth(Boolean.valueOf(rootNode.getChildText("auth")));
			setUser(rootNode.getChildText("user"));
			setPassword(rootNode.getChildText("password"));
			
			setPathTempImages(rootNode.getChildText("pathTempImages"));
			setPathExportDesign(rootNode.getChildText("pathExportDesign"));
			setPathConfig(rootNode.getChildText("pathConfig"));
			setPathDownloadApp(rootNode.getChildText("pathDownloadApp"));
			setKeyGoogleMap(rootNode.getChildText("keyGoogleMap"));
			
		  } catch (Exception io) {
			  LogFwk.getInstance(Configuracion.class).error("No se encontro ningun archivo de configuracion. Se creara uno automaticamente");
			  System.out.println("falla la carga");
		  }
	}
	
	public void setHoraUnificador(String HoraUnificador) {
		horaUnificador = HoraUnificador;
	}
	public String getHoraUnificador() {
		return horaUnificador;
	}
	public void setMinutoUnificador(String minutosUnificador) {
		minutoUnificador = minutosUnificador;
	}
	public String getMinutoUnificador() {
		return minutoUnificador;
	}
	public void setManianaOTardeUnificador(String AMoPMUnificador) {
		manianaOTardeUnificador = AMoPMUnificador;
	}
	public String getManianaOTardeUnificador() {
		return manianaOTardeUnificador;
	}
	public void setSmtp(String Smtp) {
		smtp = Smtp;
	}
	public String getSmtp() {
		return smtp;
	}
	public void setPuerto(String puerto) {
		port = puerto;
	}
	public String getPuerto() {
		return port;
	}
	public void setDesdeMail(String desdeMail) {
		fromMail = desdeMail;
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
	public void setAuth(Boolean autorizado) {
		auth = autorizado;
	}
	public Boolean getAuth() {
		return auth;
	}
	public void setUser(String usuario) {
		user = usuario;
	}
	public String getUser() {
		return user;
	}
	public void setPassword(String pass) {
		password = pass;
	}
	public String getPassword() {
		return password;
	}
	public void setPathTempImages(String pathTemp) {
		pathTempImages = pathTemp;
	}
	public String getPathTempImages() {
		return pathTempImages;
	}
	public void setPathExportDesign(String pathDesign) {
		pathExportDesign = pathDesign;
	}
	public String getPathExportDesign() {
		return pathExportDesign;
	}

	public void setKeyGoogleMap(String keyGoogleMaps) {
		keyGoogleMap = keyGoogleMaps;
	}

	public String getKeyGoogleMap() {
		return keyGoogleMap;
	}

	public void setPathConfig(String pathConfiguracion) {
		pathConfig = pathConfiguracion;
	}

	public String getPathConfig() {
		return pathConfig;
	}

	public void setPathDownloadApp(String pathDownload) {
		pathDownloadApp = pathDownload;
	}

	public String getPathDownloadApp() {
		return pathDownloadApp;
	}

}
