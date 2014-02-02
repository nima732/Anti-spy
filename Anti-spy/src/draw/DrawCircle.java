package draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawCircle extends View implements OnTouchListener {

	private Paint paint = new Paint();
	private Path path = new Path();
	private Bitmap mBitmap;
	private Canvas mCanvas;

	// List<Point> points = new ArrayList<Point>();

	
	
	/**
	 * If there is not AttributeSet in constructor, it is not possible to inflate it in 
	 * the Layout ( Draw_touch_layout)
	 * @param context
	 * @param attSet
	 */
	public DrawCircle(Context context,AttributeSet attSet) {
		super(context, attSet);
		setFocusable(true);
		setFocusableInTouchMode(true);

		this.setOnTouchListener(this);

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(8);
		paint.setColor(Color.BLACK);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
	}

	@Override
	public void onDraw(Canvas canvas) {
		// ==========================
		// for (Point point : points) {
		// canvas.drawCircle(point.x, point.y, 5, paint);
		// // Log.d(TAG, "Painting: "+point);
		// }

		// ==========================

		canvas.drawBitmap(mBitmap, 0, 0, paint);
		System.out.println(">>>>>>>>><");
		canvas.drawPath(path, paint);

	}

	public boolean onTouch(View view, MotionEvent event) {

		float eventX = event.getX();
		float eventY = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(eventX, eventY);
			return true;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(eventX, eventY);
			break;
		case MotionEvent.ACTION_UP:
			// nothing to do
			break;
		default:
			return false;
		}
		invalidate();

		return true;
	}

	class Point {
		float x, y;

		@Override
		public String toString() {
			return x + ", " + y;
		}
	}

	  public Bitmap getBitmap()
	    {
	        //this.measure(100, 100);
	        //this.layout(0, 0, 100, 100);
	        this.setDrawingCacheEnabled(true);  
	        this.buildDrawingCache();
	       Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());   
	        this.setDrawingCacheEnabled(false);


	    return bmp;
	    }



	    public void clear(){
	        mBitmap.eraseColor(Color.GREEN);
	        invalidate();
	        System.gc();

	    }
}
