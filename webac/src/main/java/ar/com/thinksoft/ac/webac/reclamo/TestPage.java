package ar.com.thinksoft.ac.webac.reclamo;

import java.io.IOException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.util.file.Folder;

public class TestPage extends WebPage {

	private static final MountedImageFactory IMAGE_FACTORY = new MountedImageFactory() {
		@Override
		protected Folder getFolder() {
			return new Folder(System.getProperties().getProperty("user.dir"));
		}

	};

	/**
	 * @throws IOException 
*
*/
	public TestPage() throws IOException {
		Image img = IMAGE_FACTORY.createImage("img", "test.png",null);
		add(img);
	}
}
