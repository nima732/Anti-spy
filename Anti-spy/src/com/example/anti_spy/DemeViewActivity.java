package com.example.anti_spy;

import newway.DemoView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DemeViewActivity extends Activity {
	DemoView demoView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_view);
		Button buttonDrag = (Button)findViewById(R.id.drag);
		Button buttonPaint = (Button)findViewById(R.id.paint);
		demoView = (DemoView)findViewById(R.id.draw2);
		
		
		buttonDrag.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("&&&&&&&&&&&&drag&&&&&&&&&&&&&");
				demoView.setFlag(false);
			}
		});
		
		buttonPaint.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("&&&&&&&&&&paint&&&&&&&&&&&&&&&");
				demoView.setFlag(true);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deme_view, menu);
		return true;
	}

}
