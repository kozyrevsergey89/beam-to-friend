package com.nfcfriend.jsonhandler;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.nfcfriend.jsonhandler.entity.Activity;
import com.nfcfriend.jsonhandler.entity.Feed;
import com.nfcfriend.jsonhandler.entity.Friend;
import com.nfcfriend.jsonhandler.entity.Group;
import com.nfcfriend.jsonhandler.entity.Like;
import com.nfcfriend.jsonhandler.entity.Photo;
import com.nfcfriend.jsonhandler.entity.Post;
import com.nfcfriend.jsonhandler.entity.Tag;

public class FacebookJSONObject {

	private JSONObject jsonObject;

	public FacebookJSONObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public String getId() {

		String id = "";
		try {
			id = jsonObject.getString("id");
		} catch (JSONException e) {
			Log.e("ERROR", e.toString());
		}
		return id;
	}

	public List<Friend> getFriends() {
		return this.<Friend> getList(new Friend(), "friends");
	}

	public List<Like> getLikes() {
		return this.<Like> getList(new Like(), "likes");
	}

	public List<Post> getPosts() {
		return this.<Post> getList(new Post(), "posts");
	}

	public List<Feed> getFeeds() {
		return this.<Feed> getList(new Feed(), "feed");
	}

	public List<Activity> getActivities() {
		return this.<Activity> getList(new Activity(), "activities");
	}

	public List<Group> getGroups() {
		return this.<Group> getList(new Group(), "groups");
	}

	public List<Photo> getPhotos() {
		List<Photo> photosList = new ArrayList<Photo>();
		List<Tag> tagList = new ArrayList<Tag>();

		photosList = this.<Photo> getList(new Photo(), "photos");

		try {

			String authorId = jsonObject.getString("id");
			Photo photo;

			JSONObject photosJSONObject = jsonObject.getJSONObject("photos");
			JSONArray jsonArray = photosJSONObject.getJSONArray("data");
			int jsonArrayLength = jsonArray.length();
			JSONObject currentJSONObject;

			for (int i = 0; i < jsonArrayLength; i++) {
				currentJSONObject = jsonArray.getJSONObject(i);
				tagList = this.<Tag> getList(currentJSONObject, new Tag(),
						"tags");
				photo = photosList.get(i);

				photo.setTagz(getStringList(tagList));
				photo.setAuthorId(authorId);
			}
		} catch (JSONException je) {
		}
		return photosList;
	}

	private <T> List<T> getList(T t, String jsonName) {
		return getList(jsonObject, t, jsonName);
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> getList(JSONObject jsonObject, T t, String jsonName) {

		Gson gson = new Gson();
		ArrayList<T> objectList = new ArrayList<T>();
		T object;
		try {
			JSONObject currentJSONObject = jsonObject.getJSONObject(jsonName);
			JSONArray currentJSONArray = currentJSONObject.getJSONArray("data");
			int friendsJSONArraysize = currentJSONArray.length();
			for (int i = 0; i < friendsJSONArraysize; i++) {
				object = (T) gson.fromJson(currentJSONArray.getJSONObject(i)
						.toString(), t.getClass());
				objectList.add(object);
			}
		} catch (JSONException je) {
		}
		return objectList;
	}

	private ArrayList<String> getStringList(List<Tag> list) {

		ArrayList<String> stringTagList = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			stringTagList.add(list.get(i).getId());
		}
		return stringTagList;
	}

}
