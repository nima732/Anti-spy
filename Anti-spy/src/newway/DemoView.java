package newway;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DemoView extends View {

    private static final float STROKE_WIDTH = 5f;

    /** Need to track this so the dirty region can accommodate the stroke. **/
    private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;

    private Paint paint = new Paint();
    private Path path = new Path();



    // These matrices will be used to move and zoom image
        Matrix matrix = new Matrix();
        Matrix savedMatrix = new Matrix();

        // We can be in one of these 3 states
        static final int NONE = 0;
        static final int DRAG = 1;
        static final int ZOOM = 0;
        public static float xValue;
        public static float yValue;
        private static final float MAX_ZOOM = 10;
        int mode = NONE;

        // Remember some things for zooming
        PointF start = new PointF();
        PointF mid = new PointF();
        float oldDist = 1f;
        private float globalX;
        private float globalY;
        private float width;
        private float height;

    /**
     * Optimizes painting by invalidating the smallest possible area.
     */
    private float lastTouchX;
    private float lastTouchY;
    private final RectF dirtyRect = new RectF();

    private boolean flag;

    private Bitmap bgBitmap;
    private float mScaleFactor;

    private int mActivePointerId;

    private float mPosX;

    private float mPosY;

    public DemoView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(STROKE_WIDTH);
        bgBitmap = BitmapFactory.decodeResource(context.getResources(), com.example.anti_spy.R.drawable.android_security_apps);
        System.out.println("¤¤¤¤65373246¤¤¤¤¤¤¤¤"+bgBitmap);

    }
    
    @Override
    public void onDraw(Canvas canvas) {
    	canvas.drawColor(0xFFAAAAAA);
        canvas.drawBitmap(bgBitmap, 0, 0, paint);
    }
    
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        canvas.translate(mPosX, mPosY);
        canvas.scale(mScaleFactor, mScaleFactor);

        System.out.println("¤¤¤¤¤¤¤¤¤¤¤¤"+bgBitmap);
        canvas.drawBitmap(bgBitmap, 0,0,null);
        if(mScaleFactor != 1.f)
            canvas.restore();

        if(flag)
            canvas.drawPath(path,paint);

        if(mScaleFactor == 1.f)
            canvas.restore();
        
    }

    /**
     * Erases the signature.
     */
    public void clear() {
        path.reset();

        // Repaints the entire view.
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();


        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            if (flag) {
                path.moveTo(eventX, eventY);
                lastTouchX = eventX;
                lastTouchY = eventY;
                // There is no end point yet, so don't waste cycles invalidating.
                return true;
            }else{
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                mActivePointerId = event.getPointerId(0);
                break;
            }

        case MotionEvent.ACTION_POINTER_DOWN:
            oldDist = spacing(event);
            if (oldDist > 10f) {
                savedMatrix.set(matrix);
                midPoint(mid, event);
                mode = ZOOM;
            }
            break;
        case MotionEvent.ACTION_POINTER_UP:
            mode = NONE;
            break;
        case MotionEvent.ACTION_MOVE:
        case MotionEvent.ACTION_UP:
            if (flag) {
                resetDirtyRect(eventX, eventY);

                // When the hardware tracks events faster than they are delivered,
                // theMatrix
                // event will contain a history of those skipped points.
                int historySize = event.getHistorySize();
                for (int i = 0; i < historySize; i++) {
                    float historicalX = event.getHistoricalX(i);
                    float historicalY = event.getHistoricalY(i);
                    expandDirtyRect(historicalX, historicalY);
                    path.lineTo(historicalX, historicalY);
                }

                // After replaying history, connect the line to the touch point.
                path.lineTo(eventX, eventY);
                break;
            }else{
                // Start tracking the dirty region.
                if (mode == DRAG) {
                    // ...
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY()
                            - start.y);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
//                  final int pointerIndex = event.findPointerIndex((Integer) mActivePointerId);
//                  final float x = event.getX(pointerIndex);
//                  final float y = event.getY(pointerIndex);
//
//                  final float dx = x - globalX;
//                  final float dy = y - globalY;
//                  mPosX += dx;
//                  mPosY += dy;
                }
            }

        default:
            Log.e("Ignored touch event: " ,"i"+ event.toString());
            return false;
        }

        // Include half the stroke width to avoid clipping.
        invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

        lastTouchX = eventX;
        lastTouchY = eventY;

        return true;
    }

    /**
     * Called when replaying history to ensure the dirty region includes all
     * points.
     */
    private void expandDirtyRect(float historicalX, float historicalY) {
        if (historicalX < dirtyRect.left) {
            dirtyRect.left = historicalX;
        } else if (historicalX > dirtyRect.right) {
            dirtyRect.right = historicalX;
        }
        if (historicalY < dirtyRect.top) {
            dirtyRect.top = historicalY;
        } else if (historicalY > dirtyRect.bottom) {
            dirtyRect.bottom = historicalY;
        }
    }

    /**
     * Resets the dirty region when the motion event occurs.
     */
    private void resetDirtyRect(float eventX, float eventY) {

        // The lastTouchX and lastTouchY were set when the ACTION_DOWN
        // motion event occurred.
        dirtyRect.left = Math.min(lastTouchX, eventX);
        dirtyRect.right = Math.max(lastTouchX, eventX);
        dirtyRect.top = Math.min(lastTouchY, eventY);
        dirtyRect.bottom = Math.max(lastTouchY, eventY);
    }


    /** Determine the space between the first two fingers */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    /** Calculate the mid point of the first two fingers */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    public boolean setFlag(boolean b) {
        return flag = b;
    }
    
    public DemoView(Context context){
    	this(context, null);
    }
}