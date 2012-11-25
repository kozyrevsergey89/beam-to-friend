package com.nfcfriend.jsonhandler;

import com.nfcfriend.jsonhandler.entity.*;
import com.nfcfriend.matcher.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestMatcher {

	public static void main(String[] args) throws IOException, JSONException {

		JSONObject jsonMine = getJSON("D:\\max\\projects\\beam-to-friend\\my-nfc-friend\\test\\resources\\mine.txt.txt");
        JSONObject jsonYours = getJSON("D:\\max\\projects\\beam-to-friend\\my-nfc-friend\\test\\resources\\yours.txt.txt");
		System.out.println("json object");
		//System.out.println(jsonMine);
		
		FacebookJSONObject faceMine = new FacebookJSONObject(jsonMine);
        FacebookJSONObject faceYours = new FacebookJSONObject(jsonYours);
		
		System.out.println("friends");

        Matcher matcher = new IdMatcher();
        List<Friend> matched = (List<Friend>) matcher.findMatches(faceMine.getFriends(), faceYours.getFriends());
		System.out.println(matched);

        List<Like> matchedLikes = (List<Like>) matcher.findMatches(faceMine.getLikes(), faceYours.getLikes());
        System.out.println(matchedLikes);

        List<Photo> samePhotos = (List<Photo>) matcher.findMatches(faceMine.getPhotos(), faceYours.getPhotos());
        System.out.println(samePhotos);

        List<Activity> matchedActivities = (List<Activity>) matcher.findMatches(faceMine.getActivities(), faceYours.getActivities());
        System.out.println(matchedActivities);

        Matcher textMatcher = new TextMatcher();
        ((TextMatcher)textMatcher).setMatcherUtil(new TokensMatcherUtil());
        Map<String, MatchedResult<FacebookStory>> matchedPosts = (Map<String, MatchedResult<FacebookStory>>)
                                                        textMatcher.findMatches(faceMine.getPosts(), faceYours.getPosts());
        System.out.println(matchedPosts);

        Map<String, MatchedResult<FacebookStory>> matchedFeeds = (Map<String, MatchedResult<FacebookStory>>)
                                                        textMatcher.findMatches(faceMine.getFeeds(), faceYours.getFeeds());
        System.out.println(matchedFeeds);


        PhotoMatcher photoMatcher = new PhotoMatcher();
        MatchedResult<Photo> matchedPhotos = photoMatcher.findMatches(faceMine.getPhotos(), faceYours.getPhotos());
        System.out.println(matchedPhotos);
	}

	public static JSONObject getJSON(String path) throws IOException, JSONException {

		final String jsonText = readFile(path);
		final JSONObject json = new JSONObject(jsonText);
		return json;
	}

    public static JSONObject buildJSON(String text) throws IOException, JSONException {

        return new JSONObject(text);
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
