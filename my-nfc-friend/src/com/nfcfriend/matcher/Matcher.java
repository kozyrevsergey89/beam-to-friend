package com.nfcfriend.matcher;

import java.util.List;


public interface Matcher <T> {
	
	
	public List<T> findMatches(
			List<T> first,
			List<T> second);

}
