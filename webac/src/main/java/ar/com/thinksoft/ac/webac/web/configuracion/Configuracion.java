package ar.com.thinksoft.ac.webac.web.configuracion;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import ar.com.thinksoft.ac.webac.exceptions.ConfiguracionException;
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
	
	private Integer horaUnificador;
	private Integer minutoUnificador;
	private String manianaOTardeUnificador;
	
	private String smtp;
	private String port;
	private String fromMail;
	private Boolean TLS;
	private Boolean auth;
	private String user;
	private String password;
	
	private String ipBD;
	private String portBD;
	
	private String pathTempImages = "src/main/webapp/tempImages/";
	private String pathExportDesign = "src/main/webapp/export/";
	private String pathConfig = "src/main/webapp/configuracion.xml";
	private String pathDownloadApp = "src/main/webapp/download/accionCiudadana.apk";
	private String keyGoogleMap = "ABQIAAAASNhk0DNhWwkPk0Y12RIrThTwM0brOpm-All5BF6PoaKBxRWWERRi58__PuwPgysGGKPkLxYHu8hULg";
	
	public void guardarConfiguracion() throws ConfiguracionException{
		Element configElement = new Element("configuracion");
		Document configDocument = new Document(configElement);
		
		configElement.addContent(new Element("horaUnificador").addContent(this.getHoraUnificador().toString()));
		configElement.addContent(new Element("minutoUnificador").addContent(this.getMinutoUnificador().toString()));
		configElement.addContent(new Element("manianaOTardeUnificador").addContent(this.getManianaOTardeUnificador()));
		
		configElement.addContent(new Element("smtp").addContent(this.getSmtp()));
		configElement.addContent(new Element("puerto").addContent(this.getPuerto()));
		configElement.addContent(new Element("desde").addContent(this.getDesdeMail()));
		configElement.addContent(new Element("tls").addContent(this.getTLS().toString()));
		configElement.addContent(new Element("auth").addContent(this.getAuth().toString()));
		configElement.addContent(new Element("user").addContent(this.getUser()));
		configElement.addContent(new Element("password").addContent(this.getPassword()));
		
		configElement.addContent(new Element("ipBD").addContent(this.getIpBD()));
		configElement.addContent(new Element("portBD").addContent(this.getPortBD()));
		
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
			writer.flush();
            writer.close();
            
		} catch (java.io.IOException e) {
			LogFwk.getInstance(Configuracion.class).error("Error al escribir el archivo de configuracion. Detalle: " + e.getMessage());
			throw new ConfiguracionException("Error al escribir el archivo de configuracion. Detalle: " + e.getMessage());
		}
		
		instance = null;
	}
	
	public void cargarConfiguracion() throws ConfiguracionException{
		
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(this.getPathConfig());
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			this.setHoraUnificador(Integer.valueOf(rootNode.getChildText("horaUnificador")));
			this.setMinutoUnificador(Integer.valueOf(rootNode.getChildText("minutoUnificador")));
			this.setManianaOTardeUnificador(rootNode.getChildText("manianaOTardeUnificador"));
			
			this.setSmtp(rootNode.getChildText("smtp"));
			this.setPuerto(rootNode.getChildText("puerto"));
			this.setDesdeMail(rootNode.getChildText("desde"));
			this.setTLS(Boolean.valueOf(rootNode.getChildText("tls")));
			this.setAuth(Boolean.valueOf(rootNode.getChildText("auth")));
			this.setUser(rootNode.getChildText("user"));
			this.setPassword(rootNode.getChildText("password"));
			
			this.setIpBD(rootNode.getChildText("ipBD"));
			this.setPortBD(rootNode.getChildText("portBD"));
			
			this.setPathTempImages(rootNode.getChildText("pathTempImages"));
			this.setPathExportDesign(rootNode.getChildText("pathExportDesign"));
			this.setPathConfig(rootNode.getChildText("pathConfig"));
			this.setPathDownloadApp(rootNode.getChildText("pathDownloadApp"));
			this.setKeyGoogleMap(rootNode.getChildText("keyGoogleMap"));
			
		  } catch (Exception e) {
			  LogFwk.getInstance(Configuracion.class).error("Error al leer el archivo de configuracion. Detalle: " + e.getMessage());
			  throw new ConfiguracionException("Error al leer el archivo de configuracion. Detalle: " + e.getMessage());
		  }
	}
	
	public void setHoraUnificador(Integer HoraUnificador) {
		this.horaUnificador = HoraUnificador;
	}
	public Integer getHoraUnificador() {
		return this.horaUnificador;
	}
	public void setMinutoUnificador(Integer minutosUnificador) {
		this.minutoUnificador = minutosUnificador;
	}
	public Integer getMinutoUnificador() {
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
	
	public void setAuth(Boolean auth) {
		this.auth = auth;
	}

	public Boolean getAuth() {
		return auth;
	}

	public void setTLS(Boolean tLS) {
		TLS = tLS;
	}

	public Boolean getTLS() {
		return TLS;
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

	public void setIpBD(String ipBD) {
		this.ipBD = ipBD;
	}

	public String getIpBD() {
		return ipBD;
	}

	public void setPortBD(String portBD) {
		this.portBD = portBD;
	}

	public String getPortBD() {
		return portBD;
	}

}
