package com.nfcfriend.jsonhandler.entity;

import java.util.ArrayList;

public class Photo {
	private Long id;

	private ArrayList<Long> tags;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<Long> getTags() {
		return new ArrayList<Long>(tags);
	}

}
