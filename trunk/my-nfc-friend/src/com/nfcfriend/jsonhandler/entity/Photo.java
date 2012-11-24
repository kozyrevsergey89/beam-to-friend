package com.nfcfriend.jsonhandler.entity;

import java.util.ArrayList;

public class Photo implements FacebookIdentifiable{
	
	private String id;
	private ArrayList<String> tagz;
    private String authorId;

	public Photo(){
		tagz = new ArrayList<String>();
	}
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<String> getTagz() {
		return tagz;
	}

	public void setTagz(ArrayList<String> tagz) {
		this.tagz = tagz;
	}

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

	@Override
	public String toString() {
		return "Photo [authorId=" + authorId + ", id=" + id + ", tagz=" + tagz + "]";
	}

    
}
