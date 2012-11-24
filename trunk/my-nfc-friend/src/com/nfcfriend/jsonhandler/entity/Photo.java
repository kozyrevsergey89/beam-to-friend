package com.nfcfriend.jsonhandler.entity;

import java.util.ArrayList;

public class Photo implements FacebookIdentifiable{
	
	private String id;
	private ArrayList<String> tagz;

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
	
	@Override
	public String toString() {
		return "Photo [id=" + id + ", tagz=" + tagz + "]";
	}

}