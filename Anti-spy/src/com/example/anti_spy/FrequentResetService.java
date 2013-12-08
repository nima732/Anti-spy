package com.example.anti_spy;

import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FrequentResetService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Service is context, so I used this way
		final ActivityManager activityManager = (ActivityManager) getSystemService(this.ACTIVITY_SERVICE);
		final Intent myIntent = intent;
		long delay = 1000;
		long period = 5000;

		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
//			get durration of executaion from avtivity and from user
			int durationExcu = Integer.parseInt(myIntent.getStringExtra("durationExcu") == null ? "1": myIntent.getStringExtra("durationExcu"));
			
			int counter = 1;
			int uperLimit = 12 * durationExcu; // The period is 5000 ms is equal to 5 s. 1
									// minuts is equal to 12 time execution.

			@Override
			public void run() {

				for(String value:myIntent.getStringExtra("strPackageReset").split(",")){
//					System.out.println("#############"+ value);
					activityManager.restartPackage(value);
				}
				counter++;
				if (counter == uperLimit) {
					this.cancel();
					stopSelf();
				}

			}
		}, delay, period);

		return START_STICKY;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		for (int i = 0; i < 3; i++) {
			System.out.println("#############");
		}
	}

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

}
