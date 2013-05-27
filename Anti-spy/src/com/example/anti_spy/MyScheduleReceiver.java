package com.example.anti_spy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyScheduleReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Intent service = new Intent(arg0, MainActivity.class);
//		service.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		arg0.startActivity(service);
	}

}
