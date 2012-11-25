package com.nfcfriend.jsonhandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class TestDrive {

	public static void main(String[] args) throws IOException, JSONException {

		System.out.println(readAll());
		
		JSONObject jsonObject = getJSON();
		System.out.println("json object");
		System.out.println(jsonObject);
		
		FacebookJSONObject facebookJSONObject = new FacebookJSONObject(jsonObject);
		
		System.out.println("friends");
		System.out.println(facebookJSONObject.getFriends());
		System.out.println("likes");
		System.out.println(facebookJSONObject.getLikes());
		System.out.println("posts");
		System.out.println(facebookJSONObject.getPosts());
		System.out.println("feed");
		System.out.println(facebookJSONObject.getFeeds());
		System.out.println("activities");
		System.out.println(facebookJSONObject.getActivities());
		System.out.println("photos");
		System.out.println(facebookJSONObject.getPhotos());
		System.out.println("groups");
		System.out.println(facebookJSONObject.getGroups());
	}

	public static JSONObject getJSON() throws IOException, JSONException {

		final String jsonText = readAll();
		final JSONObject json = new JSONObject(jsonText);
		return json;
	}

	private static String readAll() throws IOException {
		File file = new File("c:/file");

		final StringBuilder sb = new StringBuilder();

		String thisLine;
		BufferedReader fin = new BufferedReader(new FileReader(file));

		while ((thisLine = fin.readLine()) != null) {
			sb.append(thisLine);
		}

		fin.close();
		return sb.toString();
	}

}
