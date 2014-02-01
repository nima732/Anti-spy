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
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(new SingleTouchEventView(this, null));
	  }
	}
