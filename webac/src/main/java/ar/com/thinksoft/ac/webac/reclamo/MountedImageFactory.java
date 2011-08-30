package ar.com.thinksoft.ac.webac.reclamo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DynamicImageResource;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequestCycle;
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

	private static class ImageFromFolderWebResource extends	DynamicImageResource {
		private static final long serialVersionUID = 1L;

		private File folder;

		public ImageFromFolderWebResource(File folder, String mountPoint) {
			this.folder = folder;
			WebApplication.get().getSharedResources().add(mountPoint, this);
			WebApplication.get().mountSharedResource(mountPoint, "org.apache.wicket.Application/" + mountPoint);
		}

		@Override
		protected byte[] getImageData() {
			try {
				String name = WebRequestCycle.get().getRequest().getParameter("name");
				return bytes(new FileInputStream(new File(getFolder().getAbsolutePath() + System.getProperty("file.separator") + (name))));
			} catch (Exception e) {
				// TODO: do this properly
				return null;
			}
		}

		public File getFolder() {
			return folder;
		}
	}

	/**
	 * @return Folder from where images will be retrieved.
	 */
	protected abstract Folder getFolder();

	/**
	 * @return the URL to mount the dynamic WEB resource.e.g.
	 */
	protected abstract String getMountPoint();

	public Image createImage(String id, final String imageName) {
		if (dynamicResource == null)
			dynamicResource = new ImageFromFolderWebResource(getFolder(),getMountPoint());
		
		return new Image(id) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				String path = WebRequestCycle.get().getRequest().getURL();
				path = path.substring(0, path.indexOf('/'));
				add(new AttributeModifier("src", true, new Model<String>("/"+ path + "/" + getMountPoint() + "?name=" + imageName)));
				super.onBeforeRender();
			}
		};
	}
}
