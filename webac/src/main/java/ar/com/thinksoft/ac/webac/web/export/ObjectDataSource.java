package ar.com.thinksoft.ac.webac.web.export;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


/**
 * ObjectDataSource class is used to extract object field values for the report.
 * <br><br>
 * usage:<br>
 * List pilots = ...<br>
 * ObjectDataSource dataSource = new ObjectDataSource(pilots);<br>  
 * In the report (*.jrxml) you will need to define fields. For example: <br>
 *   <field name="Name" class="java.lang.String"/><br>
 *   where field name should correspond to your getter method:<br>
 *   "Name" - for getName()<br>
 *   "Id" - for getId()<br> 
 *
 */
@SuppressWarnings("rawtypes")
public class ObjectDataSource implements JRDataSource {

private Iterator iterator;

  private Object currentValue;

  public ObjectDataSource(List list) {
	  this.iterator = list.iterator();
  }
  // end ObjectDataSource

  public Object getFieldValue(JRField field) throws JRException {
    Object value = null;
    try {
    	// getter method signature is assembled from "get" + field name 
    	// as specified in the report
    	Method fieldAccessor = currentValue.getClass().getMethod("get" + field.getName(), null);
    	value = fieldAccessor.invoke(currentValue, null);
    } catch (IllegalAccessException iae) {
    	iae.printStackTrace();
    } catch (InvocationTargetException ite) {
    	ite.printStackTrace();
    } catch (NoSuchMethodException nsme) {
    	nsme.printStackTrace();
    }
    return value;
  }
  // end getFieldValue

  public boolean next() throws JRException {
	  currentValue = iterator.hasNext() ? iterator.next() : null;
	  return (currentValue != null);
  }
  // end next
  
}