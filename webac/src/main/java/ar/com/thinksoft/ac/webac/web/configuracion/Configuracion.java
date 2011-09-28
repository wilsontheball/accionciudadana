package ar.com.thinksoft.ac.webac.web.configuracion;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.ObjectInputStream.GetField;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import ar.com.thinksoft.ac.webac.logging.LogFwk;

public class Configuracion implements Serializable{
	
	private static final long serialVersionUID = -2152931626161062997L;
	private static Configuracion instance = null;
	
	public static Configuracion getInstance(){
		if(instance == null){
			instance = new Configuracion();
		}
		return instance;
	}
	
	private String horaUnificador;
	private String minutoUnificador;
	private String manianaOTardeUnificador;
	
	private String smtp;
	private String port;
	private String fromMail;
	
	private String pathTempImages = "src/main/webapp/tempImages/";
	private String pathExportDesign = "src/main/webapp/export/";
	private String pathConfig = "src/main/webapp/configuracion.xml";
	private String pathDownloadApp = "src/main/webapp/download/accionCiudadana.apk";
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
		
		configElement.addContent(new Element("pathTempImages").addContent(this.getPathTempImages()));
		configElement.addContent(new Element("pathExportDesign").addContent(this.getPathExportDesign()));
		configElement.addContent(new Element("pathConfig").addContent(this.getPathConfig()));
		configElement.addContent(new Element("pathDownloadApp").addContent(this.getPathDownloadApp()));
		configElement.addContent(new Element("keyGoogleMap").addContent(this.getKeyGoogleMap()));
		
		XMLOutputter outputter = new XMLOutputter();
		try {
		    File file = new File(this.getPathConfig());
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    
		    FileWriter writer = new FileWriter(this.getPathConfig());
			outputter.output(configDocument, writer);
			outputter.output(configDocument, System.out);
			writer.flush();
            writer.close();
            
		} catch (java.io.IOException e) {
			LogFwk.getInstance(Configuracion.class).error("Error al escribir el archivo de configuracion. Creacion abortada");
			System.out.println("falla el guardado");
		}
		
		instance = null;
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
			
			this.setPathTempImages(rootNode.getChildText("pathTempImages"));
			this.setPathExportDesign(rootNode.getChildText("pathExportDesign"));
			this.setPathConfig(rootNode.getChildText("pathConfig"));
			this.setPathDownloadApp(rootNode.getChildText("pathDownloadApp"));
			this.setKeyGoogleMap(rootNode.getChildText("keyGoogleMap"));
			
		  } catch (Exception io) {
			  LogFwk.getInstance(Configuracion.class).error("No se encontro ningun archivo de configuracion. Se creara uno automaticamente");
			  System.out.println("falla la carga");
		  }
	}
	
	public void setHoraUnificador(String HoraUnificador) {
		this.horaUnificador = HoraUnificador;
	}
	public String getHoraUnificador() {
		return this.horaUnificador;
	}
	public void setMinutoUnificador(String minutosUnificador) {
		this.minutoUnificador = minutosUnificador;
	}
	public String getMinutoUnificador() {
		return this.minutoUnificador;
	}
	public void setManianaOTardeUnificador(String AMoPMUnificador) {
		this.manianaOTardeUnificador = AMoPMUnificador;
	}
	public String getManianaOTardeUnificador() {
		return this.manianaOTardeUnificador;
	}
	public void setSmtp(String Smtp) {
		this.smtp = Smtp;
	}
	public String getSmtp() {
		return this.smtp;
	}
	public void setPuerto(String puerto) {
		this.port = puerto;
	}
	public String getPuerto() {
		return this.port;
	}
	public void setDesdeMail(String desdeMail) {
		this.fromMail = desdeMail;
	}
	public String getDesdeMail() {
		return this.fromMail;
	}
	public void setPathTempImages(String pathTemp) {
		this.pathTempImages = pathTemp;
	}
	public String getPathTempImages() {
		return this.pathTempImages;
	}
	public void setPathExportDesign(String pathDesign) {
		this.pathExportDesign = pathDesign;
	}
	public String getPathExportDesign() {
		return this.pathExportDesign;
	}

	public void setKeyGoogleMap(String keyGoogleMaps) {
		this.keyGoogleMap = keyGoogleMaps;
	}

	public String getKeyGoogleMap() {
		return this.keyGoogleMap;
	}

	public void setPathConfig(String pathConfiguracion) {
		this.pathConfig = pathConfiguracion;
	}

	public String getPathConfig() {
		return this.pathConfig;
	}

	public void setPathDownloadApp(String pathDownload) {
		this.pathDownloadApp = pathDownload;
	}

	public String getPathDownloadApp() {
		return this.pathDownloadApp;
	}
	
	public void clone(Configuracion config){
		this.fromMail = config.getDesdeMail();
		this.horaUnificador = config.getHoraUnificador();
		this.keyGoogleMap = config.getKeyGoogleMap();
		this.manianaOTardeUnificador = config.getManianaOTardeUnificador();
		this.minutoUnificador = config.getMinutoUnificador();
		this.pathConfig = config.getPathConfig();
		this.pathDownloadApp = config.getPathDownloadApp();
		this.pathExportDesign = config.getPathExportDesign();
		this.pathTempImages = config.getPathTempImages();
		this.port = config.getPuerto();
		this.smtp = config.getSmtp();
	}

}
