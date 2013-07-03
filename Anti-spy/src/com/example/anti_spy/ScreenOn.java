package com.example.anti_spy;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class ScreenOn extends Service {

	private BroadcastReceiver yourReceiver;

	@Override
	public void onCreate() {
		super.onCreate();

		System.out
				.println("@@@@@@@@@@@@@@@@@@@@@NIMA@@@222@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		filter.addAction(Intent.ACTION_PACKAGE_INSTALL);
		filter.addAction(Intent.ACTION_PACKAGE_RESTARTED);
		filter.addDataScheme("package"); // add addDataScheme

		BroadcastReceiver pkgRemoveReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// do my stuff
				
				System.out.println("########@@@@@@@@@");
//				if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
					Toast.makeText(context, "ACTION_PACKAGE_REMOVED nima nima joonnnnnnn",
							Toast.LENGTH_LONG).show();
//				}
			}
		};

		registerReceiver(pkgRemoveReceiver, filter);

//		final IntentFilter intentFilter = new IntentFilter();
//		intentFilter.addAction("android.intent.action.TIME_SET");
//		intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
//		intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
//		intentFilter.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
//		intentFilter.addAction("android.intent.action.PACKAGE_FIRST_LAUNCH");
//		intentFilter.addAction("android.intent.action.PACKAGE_FULLY_REMOVED");
//		intentFilter
//				.addAction("android.intent.action.PACKAGE_NEEDS_VERIFICATION");
//		intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
//		intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
//		intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
//		intentFilter.addAction("android.intent.action.PACKAGE_VERIFIED");
//		this.yourReceiver = new BroadcastReceiver() {
//			@Override
//			public void onReceive(Context context, Intent intent) {
//
//				System.out
//						.println("@@@@@@@@@@@@@@@@@@@@@NIMA@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//
//			}
//		};
//
//		this.registerReceiver(yourReceiver, intentFilter);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		this.unregisterReceiver(yourReceiver);
	}
}
