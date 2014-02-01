package draw;

import com.example.anti_spy.R;
import com.example.anti_spy.R.layout;
import com.example.anti_spy.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class DrawTouch extends Activity {
	   DrawCircle drawView;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        // Set full screen view
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                         WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);

	        drawView = new DrawCircle(this);
	        setContentView(drawView);
	        drawView.requestFocus();
	    }
}
