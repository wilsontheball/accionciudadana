package ar.com.thinksoft.ac.wilsond.configuracion;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

@SuppressWarnings("serial")
public class ConfiguracionWilsonD implements Serializable{

	private static ConfiguracionWilsonD instance = null;
	
	public static ConfiguracionWilsonD getInstance(){
		if(instance == null){
			instance = new ConfiguracionWilsonD();
		}
		return instance;
	}
	
	private static final String ruta = "./config.xml";
	//BD
	private String ipBD = "127.0.0.1";
	private String portBD = "5555";
	//Mail
	private String smtp = "smtp.gmail.com";
	private String port = "587";
	private String fromMail = "accionciudadana.gcba@gmail.com";
	private Boolean TLS = true;
	private Boolean auth = true;
	private String user = "accionciudadana.gcba@gmail.com";
	private String password;
	
	private String googleKey;
	
	
	public void guardarConfiguracion() throws Exception{
		Element configElement = new Element("configuracion");
		Document configDocument = new Document(configElement);
		//BD
		configElement.addContent(new Element("ipBD").addContent(this.getIpBD()));
		configElement.addContent(new Element("portBD").addContent(this.getPortBD()));
		//Mail
		configElement.addContent(new Element("smtp").addContent(this.getSmtp()));
		configElement.addContent(new Element("puerto").addContent(this.getPuerto()));
		configElement.addContent(new Element("desde").addContent(this.getDesdeMail()));
		configElement.addContent(new Element("tls").addContent(this.getTLS().toString()));
		configElement.addContent(new Element("auth").addContent(this.getAuth().toString()));
		configElement.addContent(new Element("user").addContent(this.getUser()));
		configElement.addContent(new Element("password").addContent(this.getPassword()));
		
		configElement.addContent(new Element("googleKey").addContent(this.getGoogleKey()));
		
		XMLOutputter outputter = new XMLOutputter();
		try {
		    File file = new File(ruta);
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    
		    FileWriter writer = new FileWriter(ruta);
			outputter.output(configDocument, writer);
			writer.flush();
            writer.close();
            
		} catch (java.io.IOException e) {
			throw new Exception("Error al escribir el archivo de configuracion. Detalle: " + e.getMessage());
		}
		
		instance = null;
	}
	
	public void cargarConfiguracion() throws Exception{
		
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(ruta);
			if(!xmlFile.exists()){
				this.setIpBD("127.0.0.1");
				this.setPortBD("5555");
				this.guardarConfiguracion();
			}
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			//BD
			this.setIpBD(rootNode.getChildText("ipBD"));
			this.setPortBD(rootNode.getChildText("portBD"));
			//Mail
			this.setSmtp(rootNode.getChildText("smtp"));
			this.setPuerto(rootNode.getChildText("puerto"));
			this.setDesdeMail(rootNode.getChildText("desde"));
			this.setTLS(Boolean.valueOf(rootNode.getChildText("tls")));
			this.setAuth(Boolean.valueOf(rootNode.getChildText("auth")));
			this.setUser(rootNode.getChildText("user"));
			this.setPassword(rootNode.getChildText("password"));
			
			this.setGoogleKey(rootNode.getChildText("googleKey"));
			
		  } catch (Exception e) {
			  throw new Exception("Error al leer el archivo de configuracion. Detalle: " + e.getMessage());
		  }
	}

	public void setPortBD(String portBD) {
		this.portBD = portBD;
	}

	public String getPortBD() {
		return portBD;
	}
	
	public String getIpBD(){
		return this.ipBD;
	}
	
	public void setIpBD(String ip){
		this.ipBD = ip;
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

	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}

	public String getGoogleKey() {
		return googleKey;
	}

}
