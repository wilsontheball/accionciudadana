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
	private String ipBD = "127.0.0.1";
	private String portBD = "5555";
	
	
	public void guardarConfiguracion() throws Exception{
		Element configElement = new Element("configuracion");
		Document configDocument = new Document(configElement);
		
		configElement.addContent(new Element("ipBD").addContent(this.getIpBD()));
		configElement.addContent(new Element("portBD").addContent(this.getPortBD()));
		
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
			this.setIpBD(rootNode.getChildText("ipBD"));
			this.setPortBD(rootNode.getChildText("portBD"));
			
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
}
