package ar.com.thinksoft.ac.andrac.pantallas;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;

/**
 * Pantalla de la camara de fotos. Saca foto si se hace un clic sobre la
 * pantalla o se presiona el boton de la camara.
 * 
 * @since 14-10-2010
 * @author Paul
 */
public class CamaraView extends Activity implements SurfaceHolder.Callback {
	public static final String FUN = "funcion";
	public static final String SACAR_FOTO = "foto";
	static final int FOTO_MODE = 0;
	private static final String TAG = "CamaraView";
	private Camera camara;
	private final int fotoWidth = 640;
	private final int fotoHeight = 480;
	boolean previewRunning = false;
	// private Context mContext = this;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
		public void onPictureTaken(byte[] imageData, Camera c) {

			if (imageData != null) {

				// Intent mIntent = new Intent();

				// XXX Parece que comprime imagen y graba en archivo
				// storeByteImage(mContext, imageData, 50, "ImageName");

				// XXX Parece que aca hace preview de la foto
				camara.startPreview();

				// Pasa la imagen al repo
				guardarImagenEnRepo(imageData);

				// Carga la devolucion.
				Intent resultado = new Intent();
				resultado.putExtra(FUN, SACAR_FOTO);
				setResult(Activity.RESULT_OK, resultado);
				Log.d(this.getClass().getName(), "Se saco una foto.");
			} else {
				// Carga la devolucion.
				Intent resultado = new Intent();
				resultado.putExtra(FUN, SACAR_FOTO);
				setResult(Activity.RESULT_CANCELED, resultado);
				Log.d(this.getClass().getName(), "Se cancelo sacar foto.");
			}
			finish();
		}
	};

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Log.e(TAG, "onCreate");

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.camera_surface);
		surfaceView = (SurfaceView) findViewById(R.id.surface_camera);
		// mSurfaceView.setOnClickListener(this);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	protected void onResume() {
		Log.e(TAG, "onResume");
		super.onResume();
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	protected void onStop() {
		Log.e(TAG, "onStop");
		super.onStop();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		Log.e(TAG, "surfaceCreated");
		camara = Camera.open();
		if (camara != null) {
			Parameters param = camara.getParameters();
			List<Size> dimensiones = param.getSupportedPictureSizes();
			Size dimension = camara.new Size(this.fotoWidth, this.fotoHeight);
			if (!dimensiones.contains(dimension)) {
				Log.e(TAG, "**** Dimension NO aceptada! ****");
				Log.e(TAG, "**** Dimensiones permitidas ****");
				for (Object o : dimensiones) {
					Size s = (Size) o;
					Log.e(TAG, s.width + " : " + s.height);
				}
				// Se toma la menor dimension.
				dimension = dimensiones.get(0);
				Log.e(TAG, "Dimension aceptada: " + dimension.width + "-"
						+ dimension.height);
			}
			param.setPictureSize(dimension.width, dimension.height);
			camara.setParameters(param);
		} else {
			Intent resultado = new Intent();
			resultado.putExtra(FUN, SACAR_FOTO);
			setResult(Activity.RESULT_CANCELED, resultado);
			Log.d(this.getClass().getName(), "Se cancelo sacar foto.");
			finish();
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		Log.e(TAG, "surfaceChanged");

		// XXX stopPreview() will crash if preview is not running
		if (previewRunning) {
			camara.stopPreview();
		}

		Camera.Parameters p = camara.getParameters();
		p.setPreviewSize(w, h);
		camara.setParameters(p);
		try {
			camara.setPreviewDisplay(holder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		camara.startPreview();
		previewRunning = true;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.e(TAG, "surfaceDestroyed");
		camara.stopPreview();
		previewRunning = false;
		camara.release();
	}

	/**
	 * Atiende los cambios de configuracion, como rotacion de pantalla, etc...
	 * Refresca la imagen de background.
	 * 
	 * @since 07-09-2011
	 * @author Paul
	 * @param newConfig
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		this.getWindow().setBackgroundDrawableResource(R.drawable.wallpaper);
	}

	// /**
	// * Saca foto cuando se hace clic sobre la pantalla.
	// *
	// * @since 07-09-2011
	// * @author Paul
	// * @param v
	// * La View.
	// */
	// public void onClick(View v) {
	// mCamera.takePicture(null, null, mPictureCallback);
	// }

	/**
	 * Saca foto cuando se pulsa el BOTON DE CAMARA.
	 * 
	 * @since 14-10-2011
	 * @author Paul
	 * @param keyCode
	 *            Codigo del boton presionado.
	 * @param event
	 *            Evento del boton presionado.
	 * @return Siempre true.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
			this.camara.takePicture(null, null, this.pictureCallback);
		} else if (keyCode == KeyEvent.KEYCODE_CAMERA) {
			// XXX No hace nada.
		} else if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
			// Se pasa para arriba.
			super.onKeyDown(keyCode, event);
		} else {
			// XXX No hace nada.
		}
		return true;
	}

	public static boolean storeByteImage(Context mContext, byte[] imageData,
			int quality, String expName) {

		File sdImageMainDirectory = new File("/sdcard");
		FileOutputStream fileOutputStream = null;

		try {

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 5;

			Bitmap myImage = BitmapFactory.decodeByteArray(imageData, 0,
					imageData.length, options);

			fileOutputStream = new FileOutputStream(
					sdImageMainDirectory.toString() + "/image.jpg");

			BufferedOutputStream bos = new BufferedOutputStream(
					fileOutputStream);

			myImage.compress(CompressFormat.JPEG, quality, bos);

			bos.flush();
			bos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void guardarImagenEnRepo(byte[] imagen) {
		((Aplicacion) this.getApplication()).getRepositorio().setImagen(imagen);
	}

	/**
	 * Captura foto. Responde al apretar boton Tomar Foto.
	 * 
	 * @since 11-09-2011
	 * @author Hernan
	 * @param v
	 */
	public void capturar(View v) {
		camara.takePicture(null, null, pictureCallback);
	}

	/**
	 * Cierra la ventana de Camara. Responde al apretar boton Cancelar.
	 * 
	 * @since 11-09-2011
	 * @author Hernan
	 * @param v
	 */
	public void cancelar(View v) {
		Intent resultado = new Intent();
		resultado.putExtra(FUN, SACAR_FOTO);
		setResult(Activity.RESULT_CANCELED, resultado);
		Log.d(this.getClass().getName(), "Se cancelo sacar foto.");
		this.finish();
	}
}