package com.nfcfriend.service;

import com.nfcfriend.jsonhandler.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxim Galushka
 * @since DouHack 2012
 */
public final class Matches {

    private List<Friend> commonFriends = new ArrayList<Friend>();
    private List<Like> commonLikes = new ArrayList<Like>();
    private List<Activity> commonActivities = new ArrayList<Activity>();
    private List<Photo> commonPhotos = new ArrayList<Photo>();

    // token -> list-mine, list-yours of posts containing same token
    private Map<String, MatchedResult<Post>> matchedPosts = new HashMap<String, MatchedResult<Post>>();

    // token -> list-mine, list-yours of feeds containing same token
    private Map<String, MatchedResult<Feed>> matchedFeeds = new HashMap<String, MatchedResult<Feed>>();

    // list-mine photos where I marked you
    // list-yours photos where you marked me
    private MatchedResult<Photo> matchedPhotos = new MatchedResult<Photo>();

    public List<Friend> getCommonFriends() {
        return commonFriends;
    }

    public void setCommonFriends(List<Friend> commonFriends) {
        this.commonFriends = commonFriends;
    }

    public List<Like> getCommonLikes() {
        return commonLikes;
    }

    public void setCommonLikes(List<Like> commonLikes) {
        this.commonLikes = commonLikes;
    }

    public List<Activity> getCommonActivities() {
        return commonActivities;
    }

    public void setCommonActivities(List<Activity> commonActivities) {
        this.commonActivities = commonActivities;
    }

    public List<Photo> getCommonPhotos() {
        return commonPhotos;
    }

    public void setCommonPhotos(List<Photo> commonPhotos) {
        this.commonPhotos = commonPhotos;
    }

    public Map<String, MatchedResult<Post>> getMatchedPosts() {
        return matchedPosts;
    }

    public void setMatchedPosts(Map<String, MatchedResult<Post>> matchedPosts) {
        this.matchedPosts = matchedPosts;
    }

    public Map<String, MatchedResult<Feed>> getMatchedFeeds() {
        return matchedFeeds;
    }

    public void setMatchedFeeds(Map<String, MatchedResult<Feed>> matchedFeeds) {
        this.matchedFeeds = matchedFeeds;
    }

    public MatchedResult<Photo> getMatchedPhotos() {
        return matchedPhotos;
    }

    public void setMatchedPhotos(MatchedResult<Photo> matchedPhotos) {
        this.matchedPhotos = matchedPhotos;
    }
}
