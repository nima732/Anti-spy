package com.example.anti_spy;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ScreenOn extends Service {

	
	private BroadcastReceiver yourReceiver;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@NIMA@@@222@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
		this.yourReceiver = new BroadcastReceiver(){
			@Override
			public void onReceive(Context context, Intent intent) {
				
				System.out.println("@@@@@@@@@@@@@@@@@@@@@NIMA@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

				
			}
		};
		
		this.registerReceiver(yourReceiver, intentFilter);
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
