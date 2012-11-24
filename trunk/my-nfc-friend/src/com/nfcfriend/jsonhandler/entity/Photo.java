package com.nfcfriend.jsonhandler.entity;

import java.util.ArrayList;

public class Photo implements FacebookIdentifiable{
	
	private String id;

	private ArrayList<Long> tag;

    @Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public ArrayList<Long> getTags() {
//		return new ArrayList<Long>(tags);
//	}

}
