package com.nfcfriend.service;

import org.json.JSONException;
import org.json.JSONObject;

import com.nfcfriend.jsonhandler.FacebookJSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BeamTranslateService extends Service {
	
	private final IBinder binder = new NfcBinder();

	@Override
	public IBinder onBind(final Intent intent) {
		return binder;
	}

	public class NfcBinder extends Binder {
		public BeamTranslateService getService() {
			return BeamTranslateService.this;
		}
	}

	public FacebookJSONObject parseWithAsyncTask(final String response) {
		try {
			return new ParseTask().execute(response).get();
		} catch (Exception e) {
			Log.e("ERROR", "Unable to get facebook object");
			return null;
		}
	}
	
	public FacebookJSONObject parseToModel(final String response) {
		JSONObject json = new JSONObject();
		try {
			json = new JSONObject(response);
		} catch (JSONException e) {
			Log.e("ERROR", e.toString());
		}
		return new FacebookJSONObject(json);
	}

	private class ParseTask extends AsyncTask<String, Void, FacebookJSONObject> {
		@Override
		protected FacebookJSONObject doInBackground(String... params) {
			return parseToModel(params[0]);
		}
	}

	private void findMatches(final FacebookJSONObject jsonObject) {
		//match method
	}
	
	
	

}
