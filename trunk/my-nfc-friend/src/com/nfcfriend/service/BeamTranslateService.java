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

	public void parseWithAsyncTask(final String response) {
		new ParseTask().execute(response);
	}

	private class ParseTask extends AsyncTask<String, Void, Void> {
		@Override
		protected Void doInBackground(String... params) {
			parseToModel(params[0]);
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

}
