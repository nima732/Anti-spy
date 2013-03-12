package com.example.anti_spy;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

public class ListRunningApp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_running_app);
		
		StringBuffer stringBuffer = new StringBuffer("");
		
		final   ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final List<RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

		
		String foregroundTaskPackageName = (recentTasks.get(0).topActivity.getPackageName());
		PackageManager pm = getPackageManager();
		PackageInfo foregroundAppPackageInfo = null;
		try {
			foregroundAppPackageInfo = pm.getPackageInfo(foregroundTaskPackageName, 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String foregroundTaskAppName = foregroundAppPackageInfo.applicationInfo.loadLabel(pm).toString();
				
		
	    for (int i = 0; i < recentTasks.size(); i++) 
	    {
	        Log.d("Executed app", "Application executed : " +recentTasks.get(i).baseActivity.toShortString()+ "\t\t ID: "+recentTasks.get(i).id+"");
	        
	        // Add all the task with ID into the list.
	        stringBuffer.append(recentTasks.get(i).baseActivity.toShortString()+ "\t\t ID: "+recentTasks.get(i).id+" \n\n\n ");
	    }
	    
		    
	    TextView textView = new TextView(this);
	    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
	    textView.setText(stringBuffer.toString());
		
		String[] value = {stringBuffer.toString()};
		ListView listView = (ListView) findViewById(R.id.mylist);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,value);
		listView.setAdapter(arrayAdapter);
		    
//=====================
		
		
		    
		// Show the Up button in the action bar.
		setupActionBar();
		
//		setContentView(textView);
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
		getMenuInflater().inflate(R.menu.list_running_app, menu);
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
