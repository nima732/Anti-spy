package draw;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawCircle extends View implements OnTouchListener {
	
	  private Paint paint = new Paint();
	  private Path path = new Path();

	
//	  List<Point> points = new ArrayList<Point>();
	  
    public DrawCircle(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);
        
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        paint.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas canvas) {
// ==========================
    	//        for (Point point : points) {
//            canvas.drawCircle(point.x, point.y, 5, paint);
//            // Log.d(TAG, "Painting: "+point);
//        }
    	
    	// ==========================
        

		  System.out.println(">>>>>>>>><");
		    canvas.drawPath(path, paint);

       
    }
    
    
    
	public boolean onTouch(View view, MotionEvent event) {
        
	    float eventX = event.getX();
	    float eventY = event.getY();

		
	    switch (event.getAction()) {
	    case MotionEvent.ACTION_DOWN:
	    	System.out.println("*******jskjhg****");
	      path.moveTo(eventX, eventY);
	      return true;
	    case MotionEvent.ACTION_MOVE:
	    	System.out.println("*******jskjhg***222*");
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

}
