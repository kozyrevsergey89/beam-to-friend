package com.nfcfriend.matcher;

import com.nfcfriend.matcher.model.MatchedResult;

import java.util.List;


public interface Matcher <T> {
	
	public MatchedResult<T> findMatches(
			List<T> mine,
			List<T> yours);

}
