package com.nfcfriend;

import com.nfcfriend.service.BeamTranslateService;
import com.nfcfriend.service.BeamTranslateService.NfcBinder;
import com.nfcfriend.service.GetMyInfoService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public class ResultActivity extends Activity{
	
	protected BeamTranslateService service;
	protected boolean bound = false;
	
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
		if(service != null) {
			Intent response = getIntent();
			if (response != null && response.getExtras() != null) {
				if(response.hasExtra(GetMyInfoService.RESULT)) {
					service.parseWithAsyncTask(response.getStringExtra(GetMyInfoService.RESULT));
				}
			}
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		if(bound) {
			unbindService(connection);
			bound = false;
		}
	}
	
	private ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(final ComponentName name) {
			bound = false;
		}
		
		@Override
		public void onServiceConnected(final ComponentName name, final IBinder service) {
			NfcBinder binder = (NfcBinder) service;
			ResultActivity.this.service = binder.getService();
			bound = true;
		}
	};
	

}
