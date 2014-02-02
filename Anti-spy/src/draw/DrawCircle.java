package draw;

import java.util.ArrayList;
import java.util.List;

import com.example.anti_spy.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawCircle extends View implements OnTouchListener {
	
	  private Paint paint = new Paint();
	  private Path path = new Path();

	
/*	private static final String TAG = "DrawView";
	private float mDownX;
	private float mDownY;
	private final float SCROLL_THRESHOLD = 10;
	private boolean isOnClick;
	boolean first = true;
	boolean actionUp = false;
	Point point;
	
    Paint paint = new Paint();
    List<Point> points = new ArrayList<Point>();
*/
    public DrawCircle(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);

//        paint.setColor(Color.WHITE);
//        paint.setAntiAlias(true);
        
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

       
/*
		    Path path = new Path();
		    System.out.println(">>>>>>>>>>>>");
 *        for(Point point : points){
        	
            if(first){
                first = false;
                System.out.println(">>>>>>>>>>>> first = false");
                path.moveTo(mDownX, mDownY);
                canvas.drawCircle(mDownX, mDownY, 5, paint);
            }
            else{
            	System.out.println(">>>>>>>>>>>> path.lineTo");
                path.lineTo(point.x, point.y);
            }
        }
        canvas.drawPath(path, paint);
        
        if (actionUp){

        	points = new ArrayList<DrawCircle.Point>();
            point.x = mDownX;
            point.y = mDownY;
            points.add(point);
            actionUp = false;

        }
*/     // ==========================        
        
//        Path path = new Path();
//
//        if(points.size() > 1){
//            for(int i = points.size() - 2; i < points.size(); i++){
//                if(i >= 0){
//                    Point point = points.get(i);
//
//                    if(i == 0){
//                        Point next = points.get(i + 1);
//                        point.x = ((next.x - point.x) / 3);
//                        point.y = ((next.y - point.y) / 3);
//                    }
//                    else if(i == points.size() - 1){
//                        Point prev = points.get(i - 1);
//                        point.x = ((point.x - prev.x) / 3);
//                        point.y = ((point.y - prev.y) / 3);
//                    }
//                    else{
//                        Point next = points.get(i + 1);
//                        Point prev = points.get(i - 1);
//                        point.x = ((next.x - prev.x) / 3);
//                        point.y = ((next.y - prev.y) / 3);
//                    }
//                }
//            }
//        }
//
//        boolean first = true;
//        for(int i = 0; i < points.size(); i++){
//            Point point = points.get(i);
//            if(first){
//                first = false;
//                path.moveTo(point.x, point.y);
//            }
//            else{
//                Point prev = points.get(i - 1);
//                path.cubicTo(prev.x + prev.x, prev.y + prev.y, point.x - point.x, point.y - point.y, point.x, point.y);
//            }
//        }
//        canvas.drawPath(path, paint);
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
	    
/*		// if(event.getAction() != MotionEvent.ACTION_DOWN)
        // return super.onTouchEvent(event);
        point = new Point();
        point.x = (int) event.getX();
        point.y = (int) event.getY();
        points.add(point);
        invalidate();
        Log.d(TAG, "point: " + point);
        
        
        
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN:
            mDownX = event.getX();
            mDownY = event.getY();
            System.out.println(">>>>>>ghhg>>>>>>"+mDownX);
            first = true;

            isOnClick = true;
            break;
        case MotionEvent.ACTION_CANCEL:
        case MotionEvent.ACTION_UP:
        	System.out.println(">>>>>>>>>>>>"+"ACTION_UP");
            actionUp = true;
            
            if (isOnClick) {
                Log.i(TAG, "onClick ");
                //TODO onClick code
            }
            break;
        case MotionEvent.ACTION_MOVE:
        	System.out.println(">>>>>>>>>>>>"+"ACTION_MOVE");
            if (isOnClick && (Math.abs(mDownX - event.getX()) > SCROLL_THRESHOLD || Math.abs(mDownY - event.getY()) > SCROLL_THRESHOLD)) {
                Log.i(TAG, "movement detected");
                isOnClick = false;
            }
        
            
            break;
        default:
            break;
    }
*/        
	    return true;
    }
    
    class Point {
        float x, y;

        @Override
        public String toString() {
            return x + ", " + y;
        }
    }	
// ==============================================================	
//	Bitmap bitmap;
//	int x,y;
//	
//	public DrawCircle(Context context){
//		super(context);
//		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);
//		x = 0;
//		y = 0;
//	}
//
//	@Override
//	protected void onDraw(Canvas canvas) {
//		// TODO Auto-generated method stub
//		super.onDraw(canvas);
//		
//		Rect rect = new Rect();
//		rect.set(0, 0, canvas.getWidth(), canvas.getHeight()/2);
//		
//		Paint paint = new Paint();
//		paint.setColor(Color.BLUE);
//		paint.setStyle(Paint.Style.FILL);
//		
//		canvas.drawRect(rect, paint);
//		
//		if (x < canvas.getWidth()){
//			
//			x += 10;
//		}else{
//			x = 0;
//		}
//		
//		if (y < canvas.getHeight()){
//			
//			y += 10;
//		}else {
//			y = 0;
//		}
//		
//		canvas.drawBitmap(bitmap, x , y ,new Paint());
//		invalidate();
//	}
//	
	
	// ==============================================================	

}
