package com.nfcfriend.service;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.nfcfriend.LoginActivity;
import com.nfcfriend.MainActivity;
import com.nfcfriend.NfcApp;
import com.nfcfriend.ResultActivity;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class GetMyInfoService extends IntentService{

	public static final String RESULT = "result_to_main";
	
	private SharedPreferences prefs;
	private Facebook facebook;
	private AsyncFacebookRunner runner;
	
	public GetMyInfoService() {
		super("GetMyInfoService");
	}

	@Override
	protected void onHandleIntent(final Intent intent) {
		prefs = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);
		if (prefs != null && prefs.contains(LoginActivity.ACCESS_TOKEN)) {
			facebook = ((NfcApp)getApplication()).getFacebook();
			runner = new AsyncFacebookRunner(facebook);
			Bundle bundle = new Bundle();
			bundle.putString("fields", "friends.fields(id,name),likes.fields(id,name)," +
					"activities,photos.fields(id,comments.fields(id))," +
					"posts.fields(id,story,message),feed.fields(id,story,message)");

		runner.request("me",bundle, new TopicRequestListener());	
		}
	}
	
	private class TopicRequestListener extends BaseRequestListener{

		@Override
		public void onComplete(final String response, final Object state) {
			startActivityWithResult(response);
		}
		
	}
	
	private void startActivityWithResult(final String result) {
		SharedPreferences.Editor editor = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE).edit();
		editor.putString(ResultActivity.RES, result);
		editor.commit();
		Intent main = new Intent(this, MainActivity.class);
		main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		main.putExtra(RESULT, result);
		startActivity(main);
	}


}
