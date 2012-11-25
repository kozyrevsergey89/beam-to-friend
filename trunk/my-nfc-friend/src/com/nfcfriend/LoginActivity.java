package com.nfcfriend;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.nfcfriend.service.GetMyInfoService;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class LoginActivity extends Activity{

	public static final String APP_ID = "384386814977181";
	public static final String PREFS_NAME = "LoginPrefs";
	public static final String ACCESS_EXPIRES = "access_expires";
	public static final String ACCESS_TOKEN = "access_token";
	private Facebook facebook;
	private SharedPreferences prefs;
	private final String[] permissions = { "user_about_me","friends_about_me","user_activities",
										   "friends_activities","user_birthday","friends_birthday",
										   "user_checkins","friends_checkins","user_education_history",
										   "friends_education_history","user_events","friends_events","user_groups",
										   "friends_groups","user_hometown","friends_hometown","user_interests",
										   "friends_interests","user_likes","friends_likes","user_location",
										   "friends_location","user_notes","friends_notes","user_photos",
										   "friends_photos","user_questions","friends_questions",
										   "user_relationships","friends_relationships","user_relationship_details",
										   "friends_relationship_details","user_religion_politics","friends_religion_politics",
										   "user_status","friends_status","user_subscriptions","friends_subscriptions",
										   "user_videos","user_website","friends_website","user_work_history",
										   "friends_work_history","email","read_friendlists","friends_about_me",
										   "friends_activities","friends_birthday","friends_checkins",
										   "friends_education_history","friends_events","friends_groups",
										   "friends_hometown","friends_interests","friends_likes",
										   "friends_location","friends_notes","friends_photos","friends_questions",
										   "friends_relationships","friends_relationship_details","friends_religion_politics",
										   "friends_status","friends_subscriptions","friends_videos","friends_website",
										   "friends_work_history" };

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		facebook = ((NfcApp)getApplication()).getFacebook();
		prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		String access_token = prefs.getString(ACCESS_TOKEN, null);
		long expires = prefs.getLong(ACCESS_EXPIRES, 0);
		if (access_token != null && expires != 0) {
			facebook.setAccessToken(access_token);
			facebook.setAccessExpires(expires);
		}
		
		if (!facebook.isSessionValid()) {
			facebook.authorize(this, permissions, new DialogListener() {
				
				@Override
				public void onFacebookError(final FacebookError e) {
					Log.e("ERROR", "onFacebookError" + e.getMessage());
				}
				
				@Override
				public void onError(final DialogError e) {
					Log.e("ERROR", "onError" + e.getMessage());
				}
				
				@Override
				public void onComplete(final Bundle values) {
					SharedPreferences.Editor editor = prefs.edit();
					editor.putString(ACCESS_TOKEN, facebook.getAccessToken());
					editor.putLong(ACCESS_EXPIRES, facebook.getAccessExpires());
					editor.commit();
				}
				@Override
				public void onCancel() { finish(); }
			});
		}else {
			finishThisActivity();
		}
	}
	
	private void finishThisActivity() {
		finish();
		startService(new Intent(this, GetMyInfoService.class));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		facebook.authorizeCallback(requestCode, resultCode, data);
		finishThisActivity();
	}
	
}
