package draw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.anti_spy.R;
import com.example.anti_spy.R.layout;
import com.example.anti_spy.R.menu;

import extLibrary.PhotoViewAttacher;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DrawTouch extends Activity {
	DrawCircle drawView;
	ImageView myImageView;
	PhotoViewAttacher mAttacher;

	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState); // Set full screen view
	 * getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	 * WindowManager.LayoutParams.FLAG_FULLSCREEN);
	 * requestWindowFeature(Window.FEATURE_NO_TITLE);
	 * 
	 * drawView = new DrawCircle(this); setContentView(drawView);
	 * drawView.requestFocus(); }
	 */
	// @Override
	// public void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(new SingleTouchEventView(this, null));
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		System.out.println("@@@@@@@@@@@@1");

		// myDrawView = new MyDrawView(this, null);
		setContentView(R.layout.activity_draw_touch);
		System.out.println("@@@@@@@@@@@@2");
		drawView = (DrawCircle) findViewById(R.id.draw);
		System.out.println("@@@@@@@@@@@@3");
		Button button1 = (Button) findViewById(R.id.buttonTouch);
		Button button2 = (Button) findViewById(R.id.buttonTouch2);
		Button button3 = (Button) findViewById(R.id.buttonLoad);
		System.out.println("@@@@@@@@@@@@4");
		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				String filePath = getFilesDir().getPath().toString();
				File folder = new File(Environment
						.getExternalStorageDirectory().toString());
				folder = new File(filePath);
				boolean success = false;
				if (!folder.exists()) {
					success = folder.mkdirs();
				}

				System.out.println(success + "folder");

				// File file = new
				// File(Environment.getExternalStorageDirectory()
				// .toString() + "/sample.png");

				File file = new File(folder, "/sample.png");

				if (!file.exists()) {
					try {
						success = file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				System.out.println(success
						+ "file>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

				String filename = "/sample.png";
				File myDir = getFilesDir();
				File secondFile = null;

				try {
					secondFile = new File(myDir, filename);
					secondFile.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				FileOutputStream ostream = null;
				// FileOutputStream outputStream = null;
				try {
					// ostream = new FileOutputStream(secondFile);
					ostream = new FileOutputStream(file);

					// outputStream = openFileOutput("nimajan",
					// Context.MODE_PRIVATE);

					System.out.println(ostream);
					View targetView = drawView;

					// myDrawView.setDrawingCacheEnabled(true);
					// Bitmap save =
					// Bitmap.createBitmap(myDrawView.getDrawingCache());
					// myDrawView.setDrawingCacheEnabled(false);
					// copy this bitmap otherwise distroying the cache will
					// destroy
					// the bitmap for the referencing drawable and you'll not
					// get the captured view
					// Bitmap save = b1.copy(Bitmap.Config.ARGB_8888, false);
					// BitmapDrawable d = new BitmapDrawable(b);
					// canvasView.setBackgroundDrawable(d);
					// myDrawView.destroyDrawingCache();
					// Bitmap save = myDrawView.getBitmapFromMemCache("0");
					// myDrawView.setDrawingCacheEnabled(true);
					// Bitmap save = myDrawView.getDrawingCache(false);
					Bitmap well = drawView.getBitmap();
					Bitmap save = Bitmap.createBitmap(320, 480,
							Config.ARGB_8888);
					Paint paint = new Paint();
					paint.setColor(Color.WHITE);
					Canvas now = new Canvas(save);
					now.drawRect(new Rect(0, 0, 320, 480), paint);
					now.drawBitmap(well,
							new Rect(0, 0, well.getWidth(), well.getHeight()),
							new Rect(0, 0, 320, 480), null);

					// Canvas now = new Canvas(save);
					// myDrawView.layout(0, 0, 100, 100);
					// myDrawView.draw(now);
					if (save == null) {
						System.out.println("NULL bitmap save\n");
					}
					save.compress(Bitmap.CompressFormat.PNG, 100, ostream);
					// bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
					// ostream.flush();
					// ostream.close();
				} catch (NullPointerException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Null error",
							Toast.LENGTH_SHORT).show();
				}

				catch (FileNotFoundException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "File error",
							Toast.LENGTH_SHORT).show();
				}

				catch (IOException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "IO error",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File folder = new File(Environment
						.getExternalStorageDirectory().toString());
				boolean success = false;
				if (!folder.exists()) {
					success = folder.mkdirs();
				}

				System.out.println(success + "folder");

				File file = new File(Environment.getExternalStorageDirectory()
						.toString() + "/sample.png");

				if (!file.exists()) {
					try {
						success = file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				FileOutputStream ostream = null;
				// FileOutputStream outputStream = null;
				try {
					ostream = new FileOutputStream(file);

					// outputStream = openFileOutput("nimajan",
					// Context.MODE_PRIVATE);

					System.out.println(ostream);
					View targetView = drawView;

					// myDrawView.setDrawingCacheEnabled(true);
					// Bitmap save =
					// Bitmap.createBitmap(myDrawView.getDrawingCache());
					// myDrawView.setDrawingCacheEnabled(false);
					// copy this bitmap otherwise distroying the cache will
					// destroy
					// the bitmap for the referencing drawable and you'll not
					// get the captured view
					// Bitmap save = b1.copy(Bitmap.Config.ARGB_8888, false);
					// BitmapDrawable d = new BitmapDrawable(b);
					// canvasView.setBackgroundDrawable(d);
					// myDrawView.destroyDrawingCache();
					// Bitmap save = myDrawView.getBitmapFromMemCache("0");
					// myDrawView.setDrawingCacheEnabled(true);
					// Bitmap save = myDrawView.getDrawingCache(false);
					Bitmap well = drawView.getBitmap();
					Bitmap save = Bitmap.createBitmap(320, 480,
							Config.ARGB_8888);
					Paint paint = new Paint();
					paint.setColor(Color.WHITE);
					Canvas now = new Canvas(save);
					now.drawRect(new Rect(0, 0, 320, 480), paint);
					now.drawBitmap(well,
							new Rect(0, 0, well.getWidth(), well.getHeight()),
							new Rect(0, 0, 320, 480), null);

					// Canvas now = new Canvas(save);
					// myDrawView.layout(0, 0, 100, 100);
					// myDrawView.draw(now);
					if (save == null) {
						System.out.println("NULL bitmap save\n");
					}
					save.compress(Bitmap.CompressFormat.PNG, 100, ostream);
					// bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
					// ostream.flush();
					// ostream.close();
				} catch (NullPointerException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Null error",
							Toast.LENGTH_SHORT).show();
				}

				catch (FileNotFoundException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "File error",
							Toast.LENGTH_SHORT).show();
				}

				catch (IOException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "IO error",
							Toast.LENGTH_SHORT).show();
				}
			}

		});

		button3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				/*
				 * File imgFile = new
				 * File("/data/data/com.example.anti_spy/files/sample.png");
				 * if(imgFile.exists()){
				 * 
				 * Bitmap myBitmap =
				 * BitmapFactory.decodeFile(imgFile.getAbsolutePath());
				 * 
				 * ImageView myImage = (ImageView)
				 * findViewById(R.id.imageView1);
				 * myImage.setImageBitmap(myBitmap);
				 * 
				 * }
				 */

				drawView.handleLoadImage();

				// DrawCircle drawCircle = new DrawCircle(Context context,
				// AttributeSet attSet);
				// drawCircle.handleLoadImage();

				// Bitmap mBitmap ;
				// Canvas mCanvas;
				// Paint paint = new Paint();
				//
				// mBitmap = Bitmap.createBitmap(320, 480,
				// Bitmap.Config.ARGB_8888);
				// mCanvas = new Canvas(mBitmap);
				//
				// Bitmap mBitmapSave = null;
				//
				// File imgFile = new
				// File("/data/data/com.example.anti_spy/files/sample.png");
				//
				// try {
				// mBitmapSave =
				// BitmapFactory.decodeFile(imgFile.getAbsolutePath());
				//
				//
				// // mBitmapSave.compress(Bitmap.CompressFormat.PNG, 100, new
				// FileOutputStream(imgFile));
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				//
				// mCanvas.drawBitmap(mBitmapSave, 0, 0, null);

			}
		});

//		myImageView = (ImageView) findViewById(R.id.imageView1);
//
//		Drawable bitmap = getResources().getDrawable(R.drawable.background_image);
//		myImageView.setImageDrawable(bitmap);
//
//		
//		mAttacher = new PhotoViewAttacher(myImageView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
