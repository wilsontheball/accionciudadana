package ar.com.thinksoft.ac.andrac;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

/**
 * Pantalla de la camara de fotos. Saca foto si se hace un clic sobre la
 * pantalla o se presiona el boton de la camara.
 * 
 * @since 16-08-2010
 * @author Paul
 */
public class CamaraView extends Activity implements SurfaceHolder.Callback,
		OnClickListener {
	static final int FOTO_MODE = 0;
	private static final String TAG = "CameraTest";
	Camera mCamera;
	boolean mPreviewRunning = false;
	private Context mContext = this;
	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Log.e(TAG, "onCreate");

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.camera_surface);
		mSurfaceView = (SurfaceView) findViewById(R.id.surface_camera);
		mSurfaceView.setOnClickListener(this);
		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
		public void onPictureTaken(byte[] imageData, Camera c) {

			if (imageData != null) {

				Intent mIntent = new Intent();

				storeByteImage(mContext, imageData, 50, "ImageName");
				mCamera.startPreview();

				// Pasa la imagen al repo
				guardarImagenEnRepo(imageData);

				setResult(Activity.RESULT_OK, mIntent);
				finish();

			}
		}
	};

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
		mCamera = Camera.open();

	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		Log.e(TAG, "surfaceChanged");

		// XXX stopPreview() will crash if preview is not running
		if (mPreviewRunning) {
			mCamera.stopPreview();
		}

		Camera.Parameters p = mCamera.getParameters();
		p.setPreviewSize(w, h);
		mCamera.setParameters(p);
		try {
			mCamera.setPreviewDisplay(holder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mCamera.startPreview();
		mPreviewRunning = true;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.e(TAG, "surfaceDestroyed");
		mCamera.stopPreview();
		mPreviewRunning = false;
		mCamera.release();
	}

	/**
	 * Atiende los cambios de configuracion, como rotacion de pantalla, etc...
	 * 
	 * @since 12-08-2011
	 * @author Paul
	 * @param newConfig
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	/**
	 * Saca foto cuando se hace clic sobre la pantalla.
	 * 
	 * @since 15-08-2011
	 * @author Hernan
	 * @param v
	 *            La View.
	 */
	public void onClick(View v) {
		mCamera.takePicture(null, mPictureCallback, mPictureCallback);
	}

	/**
	 * Saca foto cuando se pulsa el BOTON DE CAMARA.
	 * 
	 * @since 16-08-2011
	 * @author Paul
	 * @param keyCode
	 *            Codigo del boton presionado.
	 * @param event
	 *            Evento del boton presionado.
	 * @return Siempre true.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_CAMERA
				|| keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
			this.mCamera.takePicture(null, this.mPictureCallback,
					this.mPictureCallback);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public void guardarImagenEnRepo(byte[] imagen) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.outWidth = 640;
		opts.outHeight = 480;
		Bitmap bitmap = BitmapFactory.decodeByteArray(imagen, 0, imagen.length,
				opts);

		((Aplicacion) this.getApplication()).getRepositorio().setImagen(bitmap);
	}

}