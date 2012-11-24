package com.nfcfriend.jsonhandler;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.nfcfriend.jsonhandler.entity.Activity;
import com.nfcfriend.jsonhandler.entity.Feed;
import com.nfcfriend.jsonhandler.entity.Friend;
import com.nfcfriend.jsonhandler.entity.Like;
import com.nfcfriend.jsonhandler.entity.Photo;
import com.nfcfriend.jsonhandler.entity.Post;

public class FacebookJSONObject {

	private JSONObject jsonObject;

	public FacebookJSONObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public List<Friend> getFriends() throws JSONException {
		return this.<Friend>getList(new Friend(), "friends");
	}
	
	public List<Like> getLikes() throws JSONException {
		return this.<Like>getList(new Like(), "likes");
	}
	
	public List<Post> getPosts() throws JSONException {
		return this.<Post>getList(new Post(), "posts");
	}

	public List<Feed> getFeeds() throws JSONException {
		return this.<Feed>getList(new Feed(), "feed");
	}
	
	public List<Activity> getActivities() throws JSONException {
		return this.<Activity>getList(new Activity(), "activities");
	}
	
	public List<Photo> getPhotos() throws JSONException {
		List<Photo> photosList = new ArrayList<Photo>(); 
		photosList = this.<Photo>getList(new Photo(), "photos");
		
		return photosList;
	}
	
	@SuppressWarnings("unchecked")
	private <T> List<T> getList(T t, String jsonName) throws JSONException{
		
		Gson gson = new Gson();
		ArrayList<T> objectList = new ArrayList<T>();
		T object;

		JSONObject friendJSONObject = jsonObject.getJSONObject(jsonName);
		JSONArray friendsJSONArray = friendJSONObject.getJSONArray("data");
		int friendsJSONArraysize = friendsJSONArray.length();
		for (int i = 0; i < friendsJSONArraysize; i++) {
			object = (T) gson.fromJson(
					friendsJSONArray.getJSONObject(i).toString(), t.getClass());
			objectList.add(object);
		}
		return objectList;		
	}
	
}

