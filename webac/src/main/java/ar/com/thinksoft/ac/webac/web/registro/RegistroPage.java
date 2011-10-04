package ar.com.thinksoft.ac.webac.web.registro;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import ar.com.thinksoft.ac.webac.web.base.BasePage;

public class RegistroPage extends BasePage {

	public RegistroPage(final PageParameters parameters) {
		add(new RegistroForm("registroForm"));
		add(new FeedbackPanel("feedback"));
	}
	
	

}
