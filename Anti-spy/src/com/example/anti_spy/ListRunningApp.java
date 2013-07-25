package com.example.anti_spy;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import com.example.anti_spy.entity.PackageContainer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListRunningApp extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_running_app);

		StringBuffer stringBuffer = new StringBuffer("");

//		Use with getSystemService(String) to retrieve a ActivityManager for interacting with the global system state.
		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		
//		Return a list of the tasks that are currently running, with the most recent being first and older ones after in order.
//		final List<RunningTaskInfo> runningTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
//		
//		
//		for (int i = 0; i < runningTasks.size(); i++) {
//			Log.d("Executed app",
//					"Application executed : "
//							+ runningTasks.get(i).baseActivity.toShortString()
//							+ "\t\t ID: " + runningTasks.get(i).id + "");
//			
//			// Add all the task with ID into the list.
//			stringBuffer.append(runningTasks.get(i).baseActivity.toShortString()
//					+ "\t\t ID: " + runningTasks.get(i).id + " \n\n\n ");
//		}
		
//		Return a list of the tasks that are currently running, with the most recent being first and older ones after in order.
		final List<RunningServiceInfo> runningTasks = activityManager.getRunningServices(Integer.MAX_VALUE);
		
		
		for (int i = 0; i < runningTasks.size(); i++) {
			
			Log.d("Executed app", "Application executed : "  + runningTasks.get(i).service.getPackageName()  + "\t\t ID: " + runningTasks.get(i).pid + "");
			
			
			// Add all the task with ID into the list.
			stringBuffer.append(runningTasks.get(i).service.getClassName() + "\t\t ID: " + runningTasks.get(i).pid+ " \n\n\n ");
		}
		

		
//		to get all the packages. It is important to specify GET_PERMISSIONS flag to access to permission, otherwise it is null.
		List<PackageInfo> packageInfos = getPackageManager().getInstalledPackages(PackageManager.GET_PERMISSIONS);
		for (PackageInfo value : packageInfos) {
			// Get information from ManiFest.xml
			ActivityInfo[] activityInfo = null;
			try {
//				Retrieve overall information about packages that are installed on the device.
//				Array of all <activity> tags included under <application>, or null if there were none. This is only filled in if the flag GET_ACTIVITIES was set.
				activityInfo = getPackageManager().getPackageInfo(
						value.packageName, PackageManager.GET_ACTIVITIES).activities;
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			Log.i("Pranay", value.packageName + " has total "
					+ ((activityInfo == null) ? 0 : activityInfo.length)
					+ " activities");
			if (activityInfo != null) {
				
						
//	TODO check how to define permission in development procedure for avoiding iteration for all the activities 
				for (int i = 0; i < activityInfo.length; i++) {
						Log.i(">>>>>>>>> Activity Name >>>>>>>>>>>>>> ", value.packageName + " ::: "
								+ activityInfo[i].name);
						if (value.requestedPermissions != null) {
							if (value.packageName.contains("InCallScreen"))
								activityManager.restartPackage(value.packageName);
							for (String myPermission : value.requestedPermissions) {
								String temp[] = myPermission.split("\\.");
								myPermission = temp[temp.length-1];
								if (myPermission.equals("CAMERA") || myPermission.equals("RECORD_AUDIO") || myPermission.equals("CALL_PHONE")){
								Log.d(" Check permission ",myPermission + " ::: " + activityInfo[i].name);

					
								activityManager.restartPackage(value.packageName);
																
								}
							}
						}
					}
				
			}
		}

		// ===========================


		TextView textView = new TextView(this);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		textView.setText(stringBuffer.toString());

		String[] value = { stringBuffer.toString() };
		ListView listView = (ListView) findViewById(R.id.mylist);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, value);
		listView.setAdapter(arrayAdapter);

		// Show the Up button in the action bar.
		setupActionBar();

		// setContentView(textView);
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
