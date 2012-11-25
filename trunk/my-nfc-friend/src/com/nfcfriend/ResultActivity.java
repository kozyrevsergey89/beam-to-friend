package com.nfcfriend;

import com.nfcfriend.jsonhandler.FacebookJSONObject;
import com.nfcfriend.service.BeamTranslateService;
import com.nfcfriend.service.BeamTranslateService.NfcBinder;
import com.nfcfriend.service.GetMyInfoService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class ResultActivity extends Activity{

	protected BeamTranslateService beamService;
	protected boolean bound = false;
	protected FacebookJSONObject result;
	protected String responseString;
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = new Intent(this, BeamTranslateService.class);
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
		new Handler().postDelayed(serviceWaiter, 500);
	}
	
	final Runnable serviceWaiter = new Runnable() {
		@Override
		public void run() {
			Intent response = getIntent();
			if (beamService != null) {
				if (response != null && response.getExtras() != null) {
					if(response.hasExtra(GetMyInfoService.RESULT)) {
						responseString = response.getStringExtra(GetMyInfoService.RESULT);
						result = beamService.parseWithAsyncTask(response.getStringExtra(GetMyInfoService.RESULT));
						Log.i("NFCFriend", "ResultActivity - " + result.getId());
						Log.i("NFCFriend", "ResultActivity - " + responseString);
					}
				}
			}

		}
	};
	
	@Override
	protected void onStop() {
		super.onStop();
		if(bound) {
			unbindService(connection);
			bound = false;
		}
	}
	
	final ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(final ComponentName name) {
			bound = false;
		}
		
		@Override
		public void onServiceConnected(final ComponentName name, final IBinder service) {
			NfcBinder binder = (NfcBinder) service;
			beamService = binder.getService();
			bound = true;
		}
	};
	

}
