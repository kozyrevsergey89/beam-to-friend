package com.nfcfriend.matcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nfcfriend.matcher.model.FacebookIdentifiable;

public class IdMatcher implements Matcher<FacebookIdentifiable> {
	
	public List<FacebookIdentifiable> findMatches(
			List<FacebookIdentifiable> first,
			List<FacebookIdentifiable> second){		
			
		List<FacebookIdentifiable> copy = new ArrayList<FacebookIdentifiable>();
		copy.retainAll(second);
		return Collections.unmodifiableList(copy);
		
	}
}
