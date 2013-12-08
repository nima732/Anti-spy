package com.example.anti_spy;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE="come.example.anti_spy.MESSAGE";
	
	
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startService(new Intent(this,ScreenOn.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void listResources(View view){
//		Intent listResouce = new Intent(this, ListResources.class);
//		startActivity(listResouce);
		
		Intent displayResourceTest = new Intent(this, DisplayResourceTest.class);
		startActivity(displayResourceTest);

	}
	
	public void layoutTutorial(View view){
		Intent displayLayoutTutorial = new Intent(this,LayoutTutorial.class);
		startActivity(displayLayoutTutorial);
	}
	
	/**
	 * Called when the user clicks the user Send button
	 */
	public void sendMessage(View view){

		Intent listIntent = new Intent(this,ListRunningApp.class);
//		EditText editText = (EditText) findViewById(R.id.edit_message);
//		String message = editText.getText().toString();
//		listIntent.putExtra(EXTRA_MESSAGE, message);
		startActivity(listIntent);
		
//		Intent intent = new Intent(this, DisplayMessageActivity.class);
//		EditText editText = (EditText) findViewById(R.id.edit_message);
//		String message = editText.getText().toString();
//		intent.putExtra(EXTRA_MESSAGE, message);
//		startActivity(intent);
	}

}
