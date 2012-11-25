package com.nfcfriend.service;

import com.nfcfriend.jsonhandler.entity.*;
import com.nfcfriend.matcher.*;
import org.json.JSONException;
import org.json.JSONObject;

import com.nfcfriend.jsonhandler.FacebookJSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.Map;

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

    @SuppressWarnings("unchecked")
	private Matches findMatches(final FacebookJSONObject mineJson, final FacebookJSONObject yourJson) {
		Matches matches = new Matches();

        Matcher matcher = new IdMatcher();
        matches.setCommonFriends((List<Friend>) matcher.findMatches(mineJson.getFriends(), yourJson.getFriends()));
        matches.setCommonLikes((List<Like>) matcher.findMatches(mineJson.getLikes(), yourJson.getLikes()));
        matches.setCommonPhotos ((List<Photo>) matcher.findMatches(mineJson.getPhotos(), yourJson.getPhotos()));
        matches.setCommonActivities((List<Activity>) matcher.findMatches(mineJson.getActivities(), yourJson.getActivities()));

        Matcher textMatcher = new TextMatcher();
        ((TextMatcher)textMatcher).setMatcherUtil(new TokensMatcherUtil());
        matches.setMatchedPosts((Map<String, MatchedResult<Post>>)
                                        textMatcher.findMatches(mineJson.getPosts(), yourJson.getPosts()));

        matches.setMatchedFeeds ((Map<String, MatchedResult<Feed>>)
                                        textMatcher.findMatches(mineJson.getFeeds(), yourJson.getFeeds()));


        PhotoMatcher photoMatcher = new PhotoMatcher();
        matches.setMatchedPhotos(photoMatcher.findMatches(mineJson.getPhotos(), yourJson.getPhotos()));

        return matches;
	}
	
	
	

}
