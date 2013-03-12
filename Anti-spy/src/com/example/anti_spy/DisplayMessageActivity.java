package com.example.anti_spy;

import java.util.List;

import org.apache.http.protocol.HTTP;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;

public class DisplayMessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		
		// Get message from intent.
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		// Create text view.
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(message);
		
		
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		// The intent does not have a URI, so declare the "text/plain" MIME type
		emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"nima7321@yahoo.com"}); // recipients
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
		// You can also attach multiple items by passing an ArrayList of Uris
		
		
		// Build the intent
		Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
		Intent mapInten = new  Intent(Intent.ACTION_VIEW,location);
		
		// Verify it resolves
		PackageManager packageManager = getPackageManager();
		List<ResolveInfo> activities =  packageManager.queryIntentActivities(mapInten, 0);
		boolean isIntentSafe = activities.size() > 0;
		
		// Start activity if it is safe
		if(isIntentSafe){
			startActivity(mapInten);
		}
		
		// Make sure we are using HoneyComb or higher to use Action's bar API
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		setContentView(textView);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
