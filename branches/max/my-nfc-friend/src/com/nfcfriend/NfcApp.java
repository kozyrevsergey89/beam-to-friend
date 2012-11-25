package com.nfcfriend;

import com.facebook.android.Facebook;

import android.app.Application;

public class NfcApp extends Application{

	private Facebook facebook;
	
	@Override
	public void onCreate() {
		super.onCreate();
		facebook = new Facebook(LoginActivity.APP_ID);
	}
	
	public Facebook getFacebook() { return facebook; }
	
}
