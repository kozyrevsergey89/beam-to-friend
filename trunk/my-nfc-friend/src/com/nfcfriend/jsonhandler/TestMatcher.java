package com.nfcfriend.jsonhandler;

import com.nfcfriend.jsonhandler.entity.FacebookIdentifiable;
import com.nfcfriend.jsonhandler.entity.Friend;
import com.nfcfriend.jsonhandler.entity.MatchedResult;
import com.nfcfriend.matcher.IdMatcher;
import com.nfcfriend.matcher.Matcher;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TestMatcher {

	public static void main(String[] args) throws IOException, JSONException {

		JSONObject jsonMine = getJSON("D:\\max\\projects\\beam-to-friend\\my-nfc-friend\\test\\resources\\mine.txt.txt");
        JSONObject jsonYours = getJSON("D:\\max\\projects\\beam-to-friend\\my-nfc-friend\\test\\resources\\yours.txt.txt");
		System.out.println("json object");
		System.out.println(jsonMine);
		
		FacebookJSONObject faceMine = new FacebookJSONObject(jsonMine);
        FacebookJSONObject faceYours = new FacebookJSONObject(jsonYours);
		
		System.out.println("friends");

        Matcher matcher = new IdMatcher();
        List<Friend> matched = (List<Friend>) matcher.findMatches(faceMine.getFriends(), faceYours.getFriends());


		System.out.println(matched);
		//System.out.println("likes");
		//System.out.println(facebookJSONObject.getLikes());
		//System.out.println("posts");
		//System.out.println(facebookJSONObject.getPosts());
		//System.out.println("feed");
		//System.out.println(facebookJSONObject.getFeeds());
		//System.out.println("activities");
		//System.out.println(facebookJSONObject.getActivities());
		//System.out.println("photos");
		//System.out.println(facebookJSONObject.getPhotos());
	}

	public static JSONObject getJSON(String path) throws IOException, JSONException {

		final String jsonText = readFile(path);
		final JSONObject json = new JSONObject(jsonText);
		return json;
	}

	private static String readFile(String path) throws IOException {
		File file = new File(path);

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
