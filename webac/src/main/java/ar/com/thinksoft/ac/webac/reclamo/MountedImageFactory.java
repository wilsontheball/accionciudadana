package ar.com.thinksoft.ac.webac.reclamo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DynamicImageResource;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.Folder;

public abstract class MountedImageFactory {

	static int BUFFER_SIZE = 10 * 1024;

	/**
	 * Copies one stream into the other..
	 * 
	 * @param is
	 *            source Stream
	 * @param os
	 *            destination Stream
	 * */
	static public void copy(InputStream is, OutputStream os) throws IOException {
		byte[] buf = new byte[BUFFER_SIZE];
		while (true) {
			int tam = is.read(buf);
			if (tam == -1) {
				return;
			}
			os.write(buf, 0, tam);
		}
	}

	public static byte[] bytes(InputStream is) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(is, out);
		return out.toByteArray();
	}

	private static ImageFromFolderWebResource dynamicResource;

	@SuppressWarnings("serial")
	private static class ImageFromFolderWebResource extends	DynamicImageResource {

		private File folder;
		private byte[] imageData;

		public ImageFromFolderWebResource(File folder) {
			this.folder = folder;
		}

		@Override
		protected byte[] getImageData() {
			return this.imageData;
		}
		
		public File getFolder() {
			return folder;
		}
	}

	/**
	 * @return Folder from where images will be retrieved.
	 */
	protected abstract Folder getFolder();

	@SuppressWarnings("serial")
	public Image createImage(String id, final String imageName) throws IOException {
		
		if (dynamicResource == null)
			dynamicResource = new ImageFromFolderWebResource(getFolder());
		
		return new Image(id,dynamicResource) {
			@Override
			protected void onBeforeRender() {
				add(new AttributeModifier("src", true, new Model<String>("/"+ dynamicResource.getFolder().getAbsolutePath()+ "/" + imageName)));
				super.onBeforeRender();
			}
		};
	}
}
