package ar.com.thinksoft.ac.webac.web.gridExtension;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import ar.com.thinksoft.ac.webac.web.reclamo.detalleReclamo.DetalleReclamoPage;

import com.inmethod.grid.column.AbstractColumn;

@SuppressWarnings("serial")
public class ButtonColumn extends AbstractColumn {

	public ButtonColumn(String columnId, IModel headerModel) {
		super(columnId, headerModel);
		setResizable(false);
		setInitialSize(30);
	}

	@Override
	public Component newCell(WebMarkupContainer parent, String componentId,
			IModel rowModel) {
		return new BodyButtonPanel(componentId, rowModel);
	}
	
	
	private class BodyButtonPanel extends Panel {

		private BodyButtonPanel(String id, final IModel model) {
			super(id, model);
			
			Button button = new Button("button");
			button.setOutputMarkupId(true);
			add(button);

			button.add(new AjaxFormSubmitBehavior(getGrid().getForm(),
					"onclick") {

				@Override
				protected void onSubmit(AjaxRequestTarget target) {
					setResponsePage(DetalleReclamoPage.class);
				}

				@Override
				protected void onError(AjaxRequestTarget target) {
				}

			});
		}

	}

}
